package com.callbackcats.memeow.model.dto;

import com.google.gson.Gson;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private String iconUrl;
    private Byte hasFacebook;
    private Timestamp facebookRegistrationDateUtc;
    private Byte hasTwitter;
    private Timestamp twitterRegistrationDateUtc;
    private Byte hasInstagram;
    private Timestamp instagramRegistrationDateUtc;
    private String userRole;
    private Long currentXp;
    private Integer currentLevel;
    private String profileUuid;
    private MemeDTO lastMeme;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

