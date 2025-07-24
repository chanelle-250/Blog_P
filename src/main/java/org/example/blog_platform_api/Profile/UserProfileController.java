package org.example.blog_platform_api.Profile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService profileService;

    public UserProfileController(UserProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<UserProfile> createOrUpdateProfile(
            @PathVariable Long userId,
            @RequestBody UserProfile profileData
    ) {
        UserProfile savedProfile = profileService.createOrUpdateProfile(userId, profileData);
        return ResponseEntity.status(201).body(savedProfile);
    }

    @GetMapping
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.getUserProfile(userId));
    }
}
