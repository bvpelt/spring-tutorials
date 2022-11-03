package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User updateUser(Long id, User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String rolename);

    User getUser(String username);

    List<User> getUsers();  // inefficient with many users -- improvement user paging


    List<Role> getRoles();

    Role getRole(String rolename);

    User getUserById(Long id);

    void deleteUserById(Long id);
}
