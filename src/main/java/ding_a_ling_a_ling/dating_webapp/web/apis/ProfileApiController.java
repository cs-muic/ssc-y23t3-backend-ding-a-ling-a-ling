package ding_a_ling_a_ling.dating_webapp.web.apis;

import ding_a_ling_a_ling.dating_webapp.domain.application.ProfileService;
import ding_a_ling_a_ling.dating_webapp.domain.application.commands.*;
import ding_a_ling_a_ling.dating_webapp.domain.common.file.FileUrlCreator;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import ding_a_ling_a_ling.dating_webapp.web.payload.*;
import ding_a_ling_a_ling.dating_webapp.web.results.*;
import ding_a_ling_a_ling.dating_webapp.web.updater.ProfileUpdater;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileApiController extends AbstractBaseController {

  private ProfileService profileService;
  private ProfileUpdater profileUpdater;
  private FileUrlCreator fileUrlCreator;

  public ProfileApiController(ProfileService profileService,
                           ProfileUpdater profileUpdater,
                           FileUrlCreator fileUrlCreator) {
    this.profileService = profileService;
    this.profileUpdater = profileUpdater;
    this.fileUrlCreator = fileUrlCreator;
  }

  @PostMapping("/api/profiles")
  public ResponseEntity<ApiResult> addProfile(@RequestBody AddProfilePayload payload,
                                           HttpServletRequest request) {
    AddProfileCommand command = payload.toCommand();
    addTriggeredBy(command, request);

    Profile profile = profileService.addProfile(command);
    profileUpdater.onProfileAdded(profile);
    return AddProfileResult.build(profile);
  }

  @GetMapping("/api/profiles/{profileId}")
  public ResponseEntity<ApiResult> getProfile(@PathVariable long profileId) {
    Profile profile = profileService.findById(new ProfileId(profileId));
    return ProfileResult.build(profile);
  }


  @PutMapping("/api/profiles/{profileId}/title")
  public ResponseEntity<ApiResult> changeTitle(@PathVariable long profileId,
                                               @RequestBody ChangeProfileTitlePayload payload,
                                               HttpServletRequest request) {
    ChangeProfileTitleCommand command = payload.toCommand(profileId);
    addTriggeredBy(command, request);

    profileService.changeProfileTitle(command);
    return Result.ok();
  }

  @PutMapping("/api/profiles/{profileId}/description")
  public ResponseEntity<ApiResult> changeDescription(@PathVariable long profileId,
                                                     @RequestBody ChangeProfileDescriptionPayload payload,
                                                     HttpServletRequest request) {
    ChangeProfileDescriptionCommand command = payload.toCommand(profileId);
    addTriggeredBy(command, request);

    profileService.changeProfileDescription(command);
    return Result.ok();
  }



  @PostMapping("/api/profiles/{profileId}/photos")
  public ResponseEntity<ApiResult> addPhoto(@PathVariable long profileId,
                                                 @RequestParam("file") MultipartFile file,
                                                 HttpServletRequest request) {
    AddProfilePhotoCommand command = new AddProfilePhotoCommand(profileId, file);
    addTriggeredBy(command, request);

    ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo photo = profileService.addPhoto(command);
    return PhotoResult.build(photo, fileUrlCreator);
  }

  @GetMapping("/api/profiles/{profileId}/photos")
  public ResponseEntity<ApiResult> getPhotos(@PathVariable long profileId) {
    List<ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo> photos = profileService.getPhotos(new ProfileId(profileId));
    return PhotoResults.build(photos, fileUrlCreator);
  }
}
