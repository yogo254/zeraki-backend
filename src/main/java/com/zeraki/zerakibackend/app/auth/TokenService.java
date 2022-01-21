package com.zeraki.zerakibackend.app.auth;

import java.util.Optional;

public interface TokenService {
    Optional<Token> findOneByRefeshToken(String refreshToken);

    Token addToken(Token token);

}
