package com.synergisticit.service;

import com.synergisticit.domain.Role;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAllRoles();
    Role saveRole(Role role);
    Optional<Role> findRoleById(Long roleId) throws IllegalArgumentException;
    Role updateRoleById(Long roleId, Role role);
    void deleteRoleById(Long roleId);
}
