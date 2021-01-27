package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.User;
import lombok.Data;

@Data
public class LeaderboardDTO {
    private Integer leaderboardPlace;
    private User user;
}
