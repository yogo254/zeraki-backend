package com.zeraki.zerakibackend.app.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {

    public Optional<AppUser> findOneByAuthUsername(String authUsername);

    public boolean existsByAuthUsername(String authUsername);

}
