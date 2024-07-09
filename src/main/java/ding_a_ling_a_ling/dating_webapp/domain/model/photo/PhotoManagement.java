package ding_a_ling_a_ling.dating_webapp.domain.model.photo;

import ding_a_ling_a_ling.dating_webapp.domain.common.file.FileStorage;
import ding_a_ling_a_ling.dating_webapp.domain.common.file.FileStorageResolver;
import ding_a_ling_a_ling.dating_webapp.domain.common.file.TempFile;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserId;
import ding_a_ling_a_ling.dating_webapp.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class PhotoManagement {

  private final static Logger log = LoggerFactory.getLogger(PhotoManagement.class);

  private FileStorageResolver fileStorageResolver;
  private ThumbnailCreator thumbnailCreator;
  private PhotoRepository photoRepository;

  public PhotoManagement(FileStorageResolver fileStorageResolver,
                              ThumbnailCreator thumbnailCreator,
                              PhotoRepository photoRepository) {
    this.fileStorageResolver = fileStorageResolver;
    this.thumbnailCreator = thumbnailCreator;
    this.photoRepository = photoRepository;
  }

  public Photo save(ProfileId profileId, MultipartFile file, UserId userId) {
    FileStorage fileStorage = fileStorageResolver.resolve();

    String filePath;
    String folder = "photos";
    boolean thumbnailCreated = false;
    if (ImageUtils.isImage(file.getContentType())) {
      filePath = saveImage(fileStorage, folder, file);
      thumbnailCreated = true;
    } else {
      filePath = fileStorage.saveUploaded(folder, file);
    }

    Photo photo = Photo.create(profileId, userId, file.getOriginalFilename(), filePath, thumbnailCreated);
    photoRepository.save(photo);
    return photo;
  }

  private String saveImage(FileStorage fileStorage, String folder, MultipartFile file) {
    // Save the file as a local temp file
    TempFile tempImageFile = fileStorage.saveAsTempFile(folder, file);

    // Save the temp image file to its target location
    fileStorage.saveTempFile(tempImageFile);

    // Create a thumbnail of the image file
    thumbnailCreator.create(fileStorage, tempImageFile);

    try {
      Files.delete(tempImageFile.getFile().toPath());
    } catch (IOException e) {
      log.error("Failed to delete temp file `" + tempImageFile.getFile().getAbsolutePath() + "`", e);
    }
    return tempImageFile.getFileRelativePath();
  }
}
