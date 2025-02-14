package com.synergisticit.controller;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import com.synergisticit.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/signup")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(userValidator);
    }

    @GetMapping("/userForm")
    public String showUserForm(Model model){
        User user = new User();
        if(userRepository.findMaxUserId() != null){
            user.setUserId(userRepository.findMaxUserId() + 1);
        } else {
            user.setUserId(1L);
        }
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("roles", roleService.findAllRoles());
        return "userForm";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("users", userService.findAllUsers());
            model.addAttribute("roles", roleService.findAllRoles());
            return "userForm";
        }
        userService.saveUser(user);
        return "redirect:/signup/userForm";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, Model model){
        Optional<User> foundUser = userService.findUserById(id);
        if(foundUser.isPresent()){
            model.addAttribute("user", foundUser.get());
            model.addAttribute("users", userService.findAllUsers());
            model.addAttribute("roles", roleService.findAllRoles());
            return "userForm";
        } else {
            return "redirect:/signup/userForm";
        }
    }



    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/signup/userForm";
    }
}
