package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.Meme;
import com.google.gson.Gson;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Collection;

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
    private Collection<MemeDTO> memesByUserId;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

