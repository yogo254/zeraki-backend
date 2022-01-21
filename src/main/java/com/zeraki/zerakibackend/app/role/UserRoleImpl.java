package com.zeraki.zerakibackend.app.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserRoleImpl implements RoleService {
    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public List<UserRole> findAllByUserId(String userId) {
        return userRoleRepo.findAllByUserId(userId);
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

}
