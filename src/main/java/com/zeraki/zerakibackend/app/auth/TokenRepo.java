package com.zeraki.zerakibackend.app.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, String> {
    Optional<Token> findOneByRefreshToken(String refreshToken);

}
