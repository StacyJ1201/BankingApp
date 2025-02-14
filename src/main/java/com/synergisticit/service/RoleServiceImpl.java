package com.synergisticit.service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Qualifier("roleRepository")
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findRoleById(Long roleId) throws IllegalArgumentException {
        return roleRepository.findById(roleId);
    }

    @Override
    public Role updateRoleById(Long roleId, Role role){
        Optional<Role> optRole = findRoleById(roleId);
        Role foundRole = findRoleById(roleId).get();

        foundRole.setRoleId(roleId);
        foundRole.setRoleName(role.getRoleName());
        foundRole.setUsers(role.getUsers());

        return roleRepository.save(foundRole);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
