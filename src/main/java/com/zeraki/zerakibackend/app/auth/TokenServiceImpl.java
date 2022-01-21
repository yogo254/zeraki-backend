package com.zeraki.zerakibackend.app.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepo tokenRepo;

    @Override
    public Token addToken(Token token) {
        token.setRefreshToken(RefreshTokenGenerator.generateToken());
        return tokenRepo.save(token);
    }

    @Override
    public Optional<Token> findOneByRefeshToken(String refreshToken) {

        return tokenRepo.findOneByRefreshToken(refreshToken);
    }

}
