package com.callbackcats.memeow.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.callbackcats.memeow.model.CustomUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Slf4j
public class JwtTokenGenerator {

    public String generateTokenWithPrefix(CustomUserPrincipal customUserPrincipal) {
        return JwtConstants.TOKEN_PREFIX + generateToken(customUserPrincipal);
    }

    public String generateToken(CustomUserPrincipal customUserPrincipal) {
        return JWT.create()
                .withSubject(customUserPrincipal.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                //.withExpiresAt(new Date(System.currentTimeMillis() + JwtConstants.TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtConstants.SECRET.getBytes()));
    }

    public String getUsername(String token){
        return JWT.require(Algorithm.HMAC512(JwtConstants.SECRET.getBytes()))
                .build()
                .verify(token.replace(JwtConstants.TOKEN_PREFIX,""))
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(JwtConstants.SECRET.getBytes())).build().verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            log.error(ex.getMessage());
        }

        return false;
    }
}
