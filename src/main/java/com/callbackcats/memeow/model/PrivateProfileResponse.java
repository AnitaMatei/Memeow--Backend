package com.callbackcats.memeow.model;

import com.callbackcats.memeow.model.dto.LevelDTO;
import com.callbackcats.memeow.model.dto.MemeDTO;
import lombok.Data;

@Data
public class PrivateProfileResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String iconUrl;
    private String userRole;
    private String profileUuid;
    private MemeDTO lastMeme;
    private LevelDTO level;
}
