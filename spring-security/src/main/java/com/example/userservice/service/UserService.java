package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(final User user);

    User updateUser(final Long id, final User user);

    Role saveRole(final Role role);

    void addRoleToUser(final String username, final String rolename);

    User getUser(final String username);

    List<User> getUsers();  // inefficient with many users -- improvement user paging


    List<Role> getRoles();

    Role getRole(final String rolename);

    User getUserById(final Long id);

    void deleteUserById(final Long id);
}
