package ding_a_ling_a_ling.dating_webapp.domain.model.photo.events;

import ding_a_ling_a_ling.dating_webapp.domain.common.event.TriggeredBy;
import ding_a_ling_a_ling.dating_webapp.domain.model.photo.PhotoId;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.events.ProfileDomainEvent;

public class ProfilePhotoAddedEvent extends ProfileDomainEvent {

  private static final long serialVersionUID = -7962885726212050836L;

  private String profileTitle;
  private PhotoId photoId;
  private String fileName;

  public ProfilePhotoAddedEvent(Profile profile, ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo photo, TriggeredBy triggeredBy) {
    super(profile.getId(), profile.getTitle(), triggeredBy);
    this.profileTitle = profile.getTitle();
    this.photoId = photo.getId();
    this.fileName = photo.getFileName();
  }

  public String getProfileTitle() {
    return profileTitle;
  }

  public PhotoId getPhotoId() {
    return photoId;
  }

  public String getFileName() {
    return fileName;
  }

  @Override
  public String toString() {
    return "ProfilePhotoAddedEvent{" +
      "profileId=" + getProfileId() +
      ", profileTitle='" + profileTitle + '\'' +
      ", photoId=" + photoId +
      ", fileName='" + fileName + '\'' +
      '}';
  }
}
