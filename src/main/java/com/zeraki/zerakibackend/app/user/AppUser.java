package com.zeraki.zerakibackend.app.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zeraki.zerakibackend.app.dto.AccountType;
import com.zeraki.zerakibackend.app.dto.Gender;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * AppUser
 */
@Entity
@Data
public class AppUser {
    @Id
    private String id;
    private String firstname;
    private String authUsername;
    private String email;
    private String password;
    private String lastname;
    @Enumerated
    private Gender gender;
    @Enumerated
    private AccountType accountType;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;

}