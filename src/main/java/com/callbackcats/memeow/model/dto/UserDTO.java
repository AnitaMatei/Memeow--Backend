package com.callbackcats.memeow.model.dto;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
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
    private Long xp;
    private String profileUuid;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

