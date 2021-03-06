package com.callbackcats.memeow.security;

import com.callbackcats.memeow.model.CustomUserPrincipal;
import com.callbackcats.memeow.model.dto.UserDTO;
import com.callbackcats.memeow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    JwtTokenGenerator jwtTokenGenerator;
    UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService, JwtTokenGenerator jwtTokenGenerator) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtToken = request.getHeader(JwtConstants.HEADER_STRING);

        if (jwtToken != null && jwtToken.startsWith(JwtConstants.TOKEN_PREFIX)) {
            Authentication authentication = verifyJwtToken(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private Authentication verifyJwtToken(String jwtToken) {
        String email = jwtTokenGenerator.getUsername(jwtToken);

        if (email == null) {
            return null;
        }

        UserDTO userDTO = userService.findByEmail(email);
        if (userDTO == null) {
            return null;
        }

        CustomUserPrincipal customUserPrincipal = new CustomUserPrincipal(userDTO);
        return new UsernamePasswordAuthenticationToken(customUserPrincipal, null, customUserPrincipal.getAuthorities());
    }
}
