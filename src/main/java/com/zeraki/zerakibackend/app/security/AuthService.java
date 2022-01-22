package com.zeraki.zerakibackend.app.security;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zeraki.zerakibackend.app.auth.Token;
import com.zeraki.zerakibackend.app.auth.TokenService;
import com.zeraki.zerakibackend.app.user.AppUser;
import com.zeraki.zerakibackend.app.user.UserRepository;
import com.zeraki.zerakibackend.app.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final TokenService tokenService;

    public Token login(String username) {

        Optional<AppUser> user = userRepository.findOneByAuthUsername(username);

        if (user.isPresent()) {

            Token token = new Token();
            token.setId(user.get().getId());
            token.setToken(this.createUserToken(username));
            token = tokenService.addToken(token);
            return token;

        }

        return null;

    }

    public AppUser getUser(Principal principal) {
        Optional<AppUser> user = userRepository.findOneByAuthUsername(principal.getName());
        if (user.isPresent()) {
            return user.get();
        }

        return null;

    }

    public ResponseEntity<?> getToken(String refreshToken) {
        Optional<Token> token = tokenService.findOneByRefeshToken(refreshToken);
        if (token.isPresent()) {

            Token userToken = token.get();
            userToken
                    .setToken(this.createUserToken(userRepository.findById(userToken.getId()).get().getAuthUsername()));
            userToken = tokenService.addToken(userToken);
            return ResponseEntity.status(HttpStatus.OK).body(userToken);

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    public String hashPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    private String createUserToken(String authname) {
        String token = JWT.create().withSubject(authname)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));

        return token;
    }

    public Optional<AppUser> getUser(String token) {
        try {
            String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes())).build().verify(token)
                    .getSubject();

            return userRepository.findOneByAuthUsername(user);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return Optional.empty();

    }

}
