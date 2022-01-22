package com.zeraki.zerakibackend.controler;

import java.security.Principal;

import com.zeraki.zerakibackend.app.security.AuthService;
import com.zeraki.zerakibackend.app.user.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControler {
    @Autowired
    private AuthService authService;

    @GetMapping("/user")
    public AppUser getUser(Principal principal) {
        return authService.getUser(principal);
    }

    @PostMapping("/token")
    public ResponseEntity<?> getUserToken(@RequestBody String refreshToken) {
        return authService.getToken(refreshToken);
    }

}
