package ding_a_ling_a_ling.dating_webapp.domain.model.profile;

import ding_a_ling_a_ling.dating_webapp.domain.common.model.AbstractBaseEntity;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserId;
import org.springframework.util.StringUtils;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "profile")
public class Profile extends AbstractBaseEntity {

  private static final long serialVersionUID = 6030626206663838257L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private long userId;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "cover_image")
  private String profilePhoto;


  public static Profile create(UserId userId, String title) {
    Profile profile = new Profile();

    profile.userId = userId.value();
    profile.title = title;
    profile.description = "";

    return profile;
  }

  public void changeTitle(String title) {
    this.title = title;
  }

  public void changeDescription(String description) {
    this.description = description;
  }

  public boolean hasProfilePhoto() {
    return StringUtils.hasText(profilePhoto);
  }

  public void addProfilePhoto(String profilePhoto) {
    this.profilePhoto = profilePhoto;
  }

  public ProfileId getId() {
    return new ProfileId(id);
  }

  public UserId getUserId() {
    return new UserId(userId);
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getProfilePhoto() {
    return profilePhoto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Profile)) return false;
    Profile profile = (Profile) o;
    return userId == profile.userId && Objects.equals(title, profile.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, title);
  }

  @Override
  public String toString() {
    return "Profile{" +
      "id=" + id +
      ", userId=" + userId +
      ", title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", profilePhoto='" + profilePhoto + '\'' +

      '}';
  }
}
