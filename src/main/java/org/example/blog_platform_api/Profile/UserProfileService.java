package org.example.blog_platform_api.Profile;

import org.example.blog_platform_api.user.User;
import org.example.blog_platform_api.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public UserProfile createOrUpdateProfile(Long userId, UserProfile profileData) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Optional<UserProfile> existingProfile = profileRepository.findByUserId(userId);

        UserProfile profile = existingProfile.orElse(new UserProfile());
        profile.setUser(user);
        profile.setBio(profileData.getBio());
        profile.setDob(profileData.getDob());
        profile.setFullNames(profileData.getFullNames());

        return profileRepository.save(profile);
    }

    public UserProfile getUserProfile(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user with ID: " + userId));
    }
}
