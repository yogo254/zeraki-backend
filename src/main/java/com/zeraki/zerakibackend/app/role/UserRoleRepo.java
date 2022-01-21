package com.zeraki.zerakibackend.app.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRole, String> {

    List<UserRole> findAllByUserId(String userId);

}
