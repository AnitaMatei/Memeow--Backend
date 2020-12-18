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
    private Long currentXp;
    private Integer currentLevel;
    private String profileUuid;
    private MemeDTO lastMeme;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

