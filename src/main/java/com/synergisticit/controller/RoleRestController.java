package com.synergisticit.controller;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> findAllRoles(){
        List<Role> roles = roleService.findAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        Role createdRole = roleService.saveRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> findRoleById(@PathVariable Long roleId){
        Optional<Role> optRole = roleService.findRoleById(roleId);
        Role foundRole = optRole.get();
        return ResponseEntity.ok(foundRole);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Long roleId, Role role){
        Optional<Role> optRole = roleService.findRoleById(roleId);
        Role foundRole = optRole.get();

        foundRole.setRoleId(role.getRoleId());
        foundRole.setRoleName(role.getRoleName());
        foundRole.setUsers(role.getUsers());

        Role updateRole = roleService.saveRole(foundRole);

        return ResponseEntity.ok(updateRole);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId){
        roleService.deleteRoleById(roleId);
        return ResponseEntity.noContent().build();
    }
}
