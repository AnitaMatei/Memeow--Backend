package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.UserLeaderboardResponse;
import lombok.Data;

@Data
public class LeaderboardDTO {
    private Integer leaderboardPlace;
    private UserLeaderboardResponse userResponse;
}
