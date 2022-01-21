package com.zeraki.zerakibackend.app.role;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserRole {
    @Id
    private String id;
    private String role;
    private String userId;
    private Date timestamp;

}
