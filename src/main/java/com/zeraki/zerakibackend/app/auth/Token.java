package com.zeraki.zerakibackend.app.auth;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data

public class Token {
    @Id
    private String id;
    private String token;
    private String refreshToken;

}
