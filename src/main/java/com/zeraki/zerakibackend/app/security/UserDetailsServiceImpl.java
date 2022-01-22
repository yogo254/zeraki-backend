package com.zeraki.zerakibackend.app.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zeraki.zerakibackend.app.role.RoleService;
import com.zeraki.zerakibackend.app.user.AppUser;
import com.zeraki.zerakibackend.app.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AppUser> appUser = userService.findOneByAuthUsername(username);
        if (appUser.isPresent()) {
            List<SimpleGrantedAuthority> grantedAuthorities = roleService.findAllByUserId(appUser.get().getId())
                    .stream().map(r -> r.getRole()).map(r -> new SimpleGrantedAuthority(r))
                    .collect(Collectors.toList());

            return new User(appUser.get().getAuthUsername(), appUser.get().getPassword(), grantedAuthorities);

        } else {

            throw new UsernameNotFoundException("Invalid user");
        }

    }

}
