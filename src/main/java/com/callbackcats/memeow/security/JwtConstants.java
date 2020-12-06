package com.callbackcats.memeow.security;

public class JwtConstants {
    public static final String FACEBOOK_AUTH_URL = "https://graph.facebook.com/me?fields=email,first_name,last_name&access_token=";
    public static final long TOKEN_EXPIRATION_TIME=1_800_000;
    public static final String SECRET = "ANA ARE MERE";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
