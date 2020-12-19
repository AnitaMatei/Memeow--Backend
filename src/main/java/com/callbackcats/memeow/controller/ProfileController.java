package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.exception.ProfileNotFoundException;
import com.callbackcats.memeow.model.CustomUserPrincipal;
import com.callbackcats.memeow.model.dto.UserDTO;
import com.callbackcats.memeow.service.MemeService;
import com.callbackcats.memeow.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    UserService userService;
    MemeService memeService;

    public ProfileController(UserService userService, MemeService memeService) {
        this.userService = userService;
        this.memeService = memeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String id) throws ProfileNotFoundException {
        return ResponseEntity.ok(userService.findProfileByProfileUuid(id));
    }

    @GetMapping("/own")
    public ResponseEntity<UserDTO> getOwnProfile() throws ProfileNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(userService.findProfileByProfileUuid(customUserPrincipal.getUser().getProfileUuid()));
    }
}
