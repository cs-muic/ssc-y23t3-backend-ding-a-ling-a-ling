package ding_a_ling_a_ling.dating_webapp.domain.application.commands;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import org.springframework.web.multipart.MultipartFile;

public class AddProfilePhotoCommand extends UserCommand {

  private ProfileId profileId;
  private MultipartFile file;

  public AddProfilePhotoCommand(long profileId, MultipartFile file) {
    this.profileId = new ProfileId(profileId);
    this.file = file;
  }

  public ProfileId getProfileId() {
    return profileId;
  }

  public MultipartFile getFile() {
    return file;
  }
}
