package com.synergisticit.service;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    User saveUser(User user);
    Optional<User> findUserById(Long userId);
    User updateUserById(Long userId, User user);
    void deleteUserById(Long userId);
}
