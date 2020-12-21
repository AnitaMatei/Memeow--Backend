package com.callbackcats.memeow.configuration;

import com.callbackcats.memeow.security.JwtAuthorizationFilter;
import com.callbackcats.memeow.security.JwtTokenGenerator;
import com.callbackcats.memeow.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserService userService;
    JwtTokenGenerator jwtTokenGenerator;

    public SecurityConfig(UserService userService, JwtTokenGenerator jwtTokenGenerator){
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception{
        return new JwtAuthorizationFilter(authenticationManager(),userService,jwtTokenGenerator);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/api/profile/own/**").hasRole("USER")
                .antMatchers("/api/memes/create").hasRole("USER")
                .antMatchers("/api/**").permitAll()
                .and()
                .addFilter(jwtAuthorizationFilter());
    }

}
