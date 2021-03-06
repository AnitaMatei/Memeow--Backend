package com.callbackcats.memeow.service;

import com.callbackcats.memeow.model.CustomUserPrincipal;
import com.callbackcats.memeow.model.FacebookAuthResponse;
import com.callbackcats.memeow.model.FacebookUser;
import com.callbackcats.memeow.model.LoginResponse;
import com.callbackcats.memeow.model.dto.UserDTO;
import com.callbackcats.memeow.model.entity.Level;
import com.callbackcats.memeow.model.entity.User;
import com.callbackcats.memeow.repository.LevelRepository;
import com.callbackcats.memeow.repository.UserRepository;
import com.callbackcats.memeow.security.JwtConstants;
import com.callbackcats.memeow.security.JwtTokenGenerator;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;
    LevelRepository levelRepository;
    ModelMapper modelMapper;
    JwtTokenGenerator jwtTokenGenerator;

    public AuthenticationService(UserRepository userRepository, LevelRepository levelRepository, ModelMapper modelMapper, JwtTokenGenerator jwtTokenGenerator) {
        this.userRepository = userRepository;
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public ResponseEntity<?> facebook(FacebookAuthResponse facebookAuthResponse) {
        FacebookUser facebookUser;
        try {
            facebookUser = verifyTokenValidity(facebookAuthResponse);
        } catch (ResponseStatusException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        Optional<User> userOptional = userRepository.findByEmail(facebookUser.getEmail());

        String jwtToken;
        if (userOptional.isPresent()) {
            try {
                jwtToken = facebookLoginUser(userOptional.get());
                updateProfilePicture(userOptional.get(),facebookUser.getPictureUrl());
            } catch (ResponseStatusException ex) {
                log.error(ex.getMessage());
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        } else {
            jwtToken = facebookRegisterUser(facebookUser);
        }
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }

    private FacebookUser verifyTokenValidity(FacebookAuthResponse facebookAuthResponse) {
        String fbUrl = JwtConstants.FACEBOOK_AUTH_URL + facebookAuthResponse.getAccessToken();
        String jsonData = WebClient.create().get().uri(fbUrl).retrieve()

                .onStatus(HttpStatus::isError, clientResponse -> {
                    throw new ResponseStatusException(clientResponse.statusCode(), "Invalid token");
                })
                .bodyToMono(String.class)
                .block();

        System.out.println(jsonData);
        return new Gson().fromJson(jsonData, FacebookUser.class);
    }

    private String facebookLoginUser(User user) {
        if (user.getHasFacebook() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Existing email account");
        }

        CustomUserPrincipal customUserPrincipal = new CustomUserPrincipal(modelMapper.map(user, UserDTO.class));

        return jwtTokenGenerator.generateTokenWithPrefix(customUserPrincipal);
    }

    private String facebookRegisterUser(FacebookUser facebookUser) {
        Level newLevel = new Level();
        levelRepository.save(newLevel);
        User newUser = new User(facebookUser.getEmail(), facebookUser.getFirst_name(), facebookUser.getLast_name(), UUID.randomUUID().toString().replace("-", ""), newLevel);
        newUser.setUserRole("ROLE_USER");
        newUser.setHasFacebook((byte) 1);
        newUser.setFacebookRegistrationDateUtc(new Timestamp(new Date().getTime()));
        newUser.setIconUrl(facebookUser.getPictureUrl());


        userRepository.save(newUser);
        CustomUserPrincipal customUserPrincipal = new CustomUserPrincipal(modelMapper.map(newUser, UserDTO.class));

        return jwtTokenGenerator.generateTokenWithPrefix(customUserPrincipal);
    }

    private void updateProfilePicture(User user, String pictureUrl){
        user.setIconUrl(pictureUrl);
        userRepository.save(user);
    }

}
