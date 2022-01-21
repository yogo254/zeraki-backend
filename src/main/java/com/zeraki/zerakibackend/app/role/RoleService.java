package com.zeraki.zerakibackend.app.role;

import java.util.List;

public interface RoleService {

    List<UserRole> findAllByUserId(String userId);

    UserRole addUserRole(UserRole userRole);

}
