package ding_a_ling_a_ling.dating_webapp.web.results;

import ding_a_ling_a_ling.dating_webapp.domain.common.file.FileUrlCreator;
import ding_a_ling_a_ling.dating_webapp.utils.ImageUtils;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class PhotoResults {

  public static ResponseEntity<ApiResult> build(List<ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo> photos, FileUrlCreator fileUrlCreator) {
    List<ListablePhoto> result = new ArrayList<>();
    for (ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo photo : photos) {
      result.add(new ListablePhoto(photo, fileUrlCreator));
    }
    ApiResult apiResult = ApiResult.blank()
      .add("photos", result);
    return Result.ok(apiResult);
  }

  private static class ListablePhoto {

    private long id;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private String previewUrl;
    private long userId;
    private long createdDate;

    ListablePhoto(ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo photo, FileUrlCreator fileUrlCreator) {
      this.id = photo.getId().value();
      this.fileName = photo.getFileName();
      this.fileType = photo.getFileType();
      this.fileUrl = fileUrlCreator.url(photo.getFilePath());
      if (photo.isThumbnailCreated()) {
        this.previewUrl = ImageUtils.getThumbnailVersion(this.fileUrl);
      } else {
        this.previewUrl = "";
      }
      this.userId = photo.getUserId().value();
      this.createdDate = photo.getCreatedDate().getTime();
    }

    public long getId() {
      return id;
    }

    public String getFileName() {
      return fileName;
    }

    public String getFileType() {
      return fileType;
    }

    public String getFileUrl() {
      return fileUrl;
    }

    public String getPreviewUrl() {
      return previewUrl;
    }

    public long getUserId() {
      return userId;
    }

    public long getCreatedDate() {
      return createdDate;
    }
  }
}
