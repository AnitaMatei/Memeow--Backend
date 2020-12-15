package com.callbackcats.memeow.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FacebookAuthResponse {
    String accessToken;
    String userId;
}
