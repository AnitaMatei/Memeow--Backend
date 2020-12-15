package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.model.FacebookAuthResponse;
import com.callbackcats.memeow.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationController {
    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> facebookLogin(@RequestBody FacebookAuthResponse facebookAuthResponse){
        return authenticationService.facebook(facebookAuthResponse);
    }

}
