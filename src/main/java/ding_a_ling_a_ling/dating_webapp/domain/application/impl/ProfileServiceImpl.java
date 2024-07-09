package ding_a_ling_a_ling.dating_webapp.domain.application.impl;

import ding_a_ling_a_ling.dating_webapp.domain.application.ProfileService;
import ding_a_ling_a_ling.dating_webapp.domain.application.commands.*;
import ding_a_ling_a_ling.dating_webapp.domain.common.event.DomainEventPublisher;

import ding_a_ling_a_ling.dating_webapp.domain.model.photo.PhotoManagement;
import ding_a_ling_a_ling.dating_webapp.domain.model.photo.PhotoRepository;
import ding_a_ling_a_ling.dating_webapp.domain.model.photo.events.ProfilePhotoAddedEvent;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileRepository;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.events.ProfileAddedEvent;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.events.ProfileDescriptionChangedEvent;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.events.ProfileTitleChangedEvent;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private ProfileRepository profileRepository;
  private PhotoManagement photoManagement;
  private PhotoRepository photoRepository;
  private DomainEventPublisher domainEventPublisher;

  public ProfileServiceImpl(ProfileRepository profileRepository,
                         PhotoRepository photoRepository,
                         PhotoManagement photoManagement,
                         DomainEventPublisher domainEventPublisher) {
    this.profileRepository = profileRepository;
    this.photoManagement = photoManagement;
    this.photoRepository = photoRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public Profile findById(ProfileId profileId) {
    return profileRepository.findById(profileId);
  }



  @Override
  public List<ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo> getPhotos(ProfileId profileId) {
    return photoRepository.findPhotos(profileId);
  }

  @Override
  public Profile addProfile(AddProfileCommand command) {
    Profile profile = Profile.create(command.getUserId(), command.getTitle());
    profileRepository.save(profile);
    domainEventPublisher.publish(new ProfileAddedEvent(profile, command));
    return profile;
  }

   @Override
  public void changeProfileTitle(ChangeProfileTitleCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Profile profile = findProfile(command.getProfileId());
    String oldTitle = profile.getTitle();
    profile.changeTitle(command.getTitle());
    profileRepository.save(profile);
    domainEventPublisher.publish(new ProfileTitleChangedEvent(profile, oldTitle, command));
  }

  @Override
  public void changeProfileDescription(ChangeProfileDescriptionCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Profile profile = findProfile(command.getProfileId());
    String oldDescription = profile.getDescription();
    profile.changeDescription(command.getDescription());
    profileRepository.save(profile);
    domainEventPublisher.publish(new ProfileDescriptionChangedEvent(profile, oldDescription, command));
  }



  @Override
  public ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo addPhoto(AddProfilePhotoCommand command) {
    Assert.notNull(command, "Parameter `command` must not be null");

    Profile profile = findProfile(command.getProfileId());
    ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo photo = photoManagement.save(
      command.getProfileId(), command.getFile(), command.getUserId());

    if (!profile.hasProfilePhoto()) {
      profile.addProfilePhoto(photo.getFilePath());
      profileRepository.save(profile);
    }

    domainEventPublisher.publish(new ProfilePhotoAddedEvent(profile, photo, command));
    return photo;
  }

  private Profile findProfile(ProfileId profileId) {
    Profile profile = profileRepository.findById(profileId);
    Assert.notNull(profile, "Profile of id " + profile + " must exist");
    return profile;
  }
}
