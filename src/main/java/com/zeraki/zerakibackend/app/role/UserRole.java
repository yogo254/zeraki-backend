package com.zeraki.zerakibackend.app.role;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserRole {
    @Id
    private String id = UUID.randomUUID().toString();
    private String role;
    private String userId;
    private Date timestamp;

}
