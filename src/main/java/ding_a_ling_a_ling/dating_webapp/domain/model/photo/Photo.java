package ding_a_ling_a_ling.dating_webapp.domain.model.photo;

import ding_a_ling_a_ling.dating_webapp.domain.common.model.AbstractBaseEntity;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserId;
import org.apache.commons.io.FilenameUtils;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "photo")
public class Photo extends AbstractBaseEntity {

  private static final long serialVersionUID = 4614318546123429009L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Column(name = "user_id")
  private long userId;

  @Column(name = "profile_id")
  private long profileId;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_path")
  private String filePath;

  @Column(name = "file_type")
  private String fileType;



  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  public static ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo create(ProfileId profileId, UserId userId, String fileName, String filePath, boolean thumbnailCreated) {
    ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo photo = new ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo();
    photo.profileId = profileId.value();
    photo.userId = userId.value();
    photo.fileName = fileName;
    photo.fileType = FilenameUtils.getExtension(fileName);
    photo.filePath = filePath;
    return photo;
  }

  public PhotoId getId() {
    return new PhotoId(id);
  }

  public ProfileId getProfileId() {
    return new ProfileId(profileId);
  }

  public UserId getUserId() {
    return new UserId(userId);
  }

  public String getFileName() {
    return fileName;
  }

  public String getFilePath() {
    return filePath;
  }

  public String getFileType() {
    return fileType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo)) return false;
    ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo that = (ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo) o;
    return profileId == that.profileId &&
      userId == that.userId &&
      Objects.equals(fileType, that.fileType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profileId, userId, fileType);
  }

  @Override
  public String toString() {
    return "Photo{" +
      "id=" + id +
      ", profileId=" + profileId +
      ", userId=" + userId +
      ", fileName='" + fileName + '\'' +
      ", filePath='" + filePath + '\'' +
      ", fileType='" + fileType + '\'' +
      ", createdDate=" + createdDate +
      '}';
  }
}
