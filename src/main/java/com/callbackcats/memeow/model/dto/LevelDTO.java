package com.callbackcats.memeow.model.dto;

import lombok.Data;

@Data
public class LevelDTO {
    private Integer currentLevel;
    private Integer currentXp;
    private Integer lastCurrentXp;
    private Integer lastCurrentLevel;
}
