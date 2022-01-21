package com.zeraki.zerakibackend.app.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<AppUser> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        appUser = userRepository.save(appUser);

        return appUser;
    }

    @Override
    public AppUser update(AppUser appUser) {

        return userRepository.save(appUser);
    }

    @Override
    public boolean deleteById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Optional<AppUser> findOneByAuthUsername(String authUsername) {

        return userRepository.findOneByAuthUsername(authUsername);
    }

    @Override
    public boolean existByAuthUsername(String authUsername) {
        return userRepository.existsByAuthUsername(authUsername);
    }

}
