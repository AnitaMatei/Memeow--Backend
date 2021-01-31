package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.exception.ProfileNotFoundException;
import com.callbackcats.memeow.model.CustomUserPrincipal;
import com.callbackcats.memeow.model.PrivateProfileResponse;
import com.callbackcats.memeow.model.PublicProfileResponse;
import com.callbackcats.memeow.model.dto.MemeDTO;
import com.callbackcats.memeow.service.MemeService;
import com.callbackcats.memeow.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PublicProfileResponse> getUserProfile(@PathVariable String id) throws ProfileNotFoundException {
        return ResponseEntity.ok(userService.findGenericProfileByProfileUuid(id));
    }

    @GetMapping("/{id}/memehistory")
    public ResponseEntity<List<MemeDTO>> getMemeHistoryOfUser(@PathVariable String id, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return ResponseEntity.ok(memeService.findMemeHistoryOfUser(id,pageNumber,pageSize));
    }

    @GetMapping("/own")
    public ResponseEntity<PrivateProfileResponse> getOwnProfile() throws ProfileNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(userService.findOwnProfile(customUserPrincipal.getUser().getProfileUuid()));
    }
}
