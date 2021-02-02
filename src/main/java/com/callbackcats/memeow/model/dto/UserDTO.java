package com.callbackcats.memeow.model.dto;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String iconUrl;
    private String userRole;
    private String facebookLink;
    private String profileUuid;
    private MemeDTO lastMeme;
    private LevelDTO level;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

