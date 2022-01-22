package com.zeraki.zerakibackend.app.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeraki.zerakibackend.app.auth.Token;
import com.zeraki.zerakibackend.app.dto.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private AuthService authService;
    private final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            Login creds = new ObjectMapper().readValue(req.getInputStream(), Login.class);
            String username = creds.getUsername();
            String password = creds.getPassword();

            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
        } catch (IOException e) {
            log.warn("Authentication failed for hostname" + req.getLocalName() + " Ip address: " + req.getLocalAddr());

        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        log.warn("Authentication Successful User " + ((User) auth.getPrincipal()).getUsername()
                + " Ip address: " + req.getRemoteAddr());

        Token token = authService.login(((User) auth.getPrincipal()).getUsername());
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        out.print(new ObjectMapper().writeValueAsString(token));
        out.flush();

    }
}
