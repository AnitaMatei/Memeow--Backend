package com.callbackcats.memeow.configuration;

import com.callbackcats.memeow.security.JwtAuthorizationFilter;
import com.callbackcats.memeow.security.JwtTokenGenerator;
import com.callbackcats.memeow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserService userService;
    JwtTokenGenerator jwtTokenGenerator;

    public SecurityConfig(UserService userService, JwtTokenGenerator jwtTokenGenerator){
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/authenticate/*").permitAll()
                .antMatchers("/api/profile").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),userService,jwtTokenGenerator));
    }


}
