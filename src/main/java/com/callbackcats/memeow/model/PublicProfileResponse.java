package com.callbackcats.memeow.model;

import com.callbackcats.memeow.model.dto.LevelDTO;
import com.callbackcats.memeow.model.dto.MemeDTO;
import lombok.Data;

@Data
public class PublicProfileResponse {
    private String firstName;
    private String lastName;
    private String iconUrl;
    private String profileUuid;
    private MemeDTO lastMeme;
    private LevelDTO level;
}
