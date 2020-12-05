package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.Meme;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;

@Setter
@Getter
@EqualsAndHashCode
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private String registrationPlatform;
    private Timestamp registrationDateUtc;
    private String iconUrl;
    private Long xp;
    private Collection<Meme> memesByUserId;
}
