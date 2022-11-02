package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.User;
import com.example.userservice.repo.RoleRepo;
import com.example.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("Adding role {} to user {}", rolename, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(rolename);

        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRole(String rolename) {
        return roleRepo.findByName(rolename);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            log.error("User {} not found", username);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User {} found in the database", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

}
