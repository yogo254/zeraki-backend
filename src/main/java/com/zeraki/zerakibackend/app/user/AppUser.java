package com.zeraki.zerakibackend.app.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zeraki.zerakibackend.app.dto.AccountType;

import lombok.Data;

/**
 * AppUser
 */
@Entity
@Data
public class AppUser {
    @Id
    private String id = UUID.randomUUID().toString();
    private String firstname;
    private String authUsername;
    private String email;
    private String password;
    private String lastname;

    @Enumerated
    private AccountType accountType;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
}