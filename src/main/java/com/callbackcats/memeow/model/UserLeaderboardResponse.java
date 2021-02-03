package com.callbackcats.memeow.model;

import lombok.Data;

@Data
public class UserLeaderboardResponse {
    private String firstName;
    private String lastName;
    private String iconUrl;
    private String profileUuid;
}
