package com.synergisticit.controller;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository1;
import com.synergisticit.service.RoleService;
import com.synergisticit.validator.RoleValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository1 roleRepository1;

    @Autowired
    RoleValidator roleValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(roleValidator);
    }

    @GetMapping("/roleForm")
    public String getRoles(Model model){
        Role role = new Role();

        Long maxId = roleRepository1.findMaxRoleId();
        role.setRoleId(maxId != null ? maxId + 1 : 1L);

        model.addAttribute("role", role);
        model.addAttribute("roles", roleService.findAllRoles());
        return "roleForm";
    }

    @PostMapping("/saveRole")
    public String saveRole(@Valid @ModelAttribute Role role, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("roles", roleService.findAllRoles());
            return "roleForm";
        } else {
            roleService.saveRole(role);
            return "redirect:/roleForm";
        }
    }

    @GetMapping("/updateRole/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) throws RoleNotFoundException{
        Role updatedRole = roleService.findRoleById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        if(updatedRole != null){
            model.addAttribute("role", updatedRole);
            model.addAttribute("roles", roleService.findAllRoles());
            return "roleForm";
        }
        return "redirect:/roleForm";
    }

    @GetMapping("deleteRole/{id}")
    public String deleteRole(@PathVariable Long id){
        roleService.deleteRoleById(id);
        return "redirect:/roleForm";
    }
}
