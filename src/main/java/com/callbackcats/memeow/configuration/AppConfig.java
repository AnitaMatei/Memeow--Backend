package com.callbackcats.memeow.configuration;

import com.callbackcats.memeow.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    ImageService imageService(){
        return new ImageService();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
