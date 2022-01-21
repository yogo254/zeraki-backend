package com.zeraki.zerakibackend.app.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<AppUser> findById(String id);

    public AppUser save(AppUser appUser);

    public AppUser update(AppUser appUser);

    public boolean deleteById(String id);

    public Optional<AppUser> findOneByAuthUsername(String authUsername);

    public boolean existByAuthUsername(String authUsername);
}
