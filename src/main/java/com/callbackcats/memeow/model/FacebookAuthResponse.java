package com.callbackcats.memeow.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth1.OAuthToken;

@Setter
@Getter
public class FacebookAuthResponse {
    String accessToken;
}
