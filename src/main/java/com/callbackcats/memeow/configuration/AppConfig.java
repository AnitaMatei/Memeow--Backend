package com.callbackcats.memeow.configuration;

import com.callbackcats.memeow.security.JwtAuthorizationFilter;
import com.callbackcats.memeow.security.JwtTokenGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
