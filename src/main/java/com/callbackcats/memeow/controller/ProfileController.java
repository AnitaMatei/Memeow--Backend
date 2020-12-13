package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.model.CustomUserPrincipal;
import com.callbackcats.memeow.service.UserService;
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

    public ProfileController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public String getUserProfile(@PathVariable String id){
        return userService.findByProfileUuid(id).toString();
    }

    @GetMapping("/own")
    public String getOwnProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal)authentication.getPrincipal();
        return customUserPrincipal.getUser().toString();
    }
}
