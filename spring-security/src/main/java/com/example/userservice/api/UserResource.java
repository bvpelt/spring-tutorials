package com.example.userservice.api;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.User;
import com.example.userservice.service.UserService;
import com.example.userservice.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") final Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(user);
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") final Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody final User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") final Long id, @RequestBody(required = true) final User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(userService.updateUser(id, user));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(userService.getRoles());
    }


    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody final Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    private boolean userHasRole(final User currentUser, final String roleName) {
        boolean found = false;


        for (Role currentRole: currentUser.getRoles()) {
            if (!found) {
                found = currentRole.getName().equals(roleName);
            }
        }

        return found;
    }
    @PostMapping("/roles/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody final RoleToUserForm form) {
        User currentUser = userService.getUser(form.getUsername());

        if (currentUser == null) {
            throw new IllegalStateException("user " + form.getUsername() + " not known");
        }

        form.getRolenames().forEach(
                roleName -> {
                    if (!userHasRole(currentUser, roleName)) {
                        userService.addRoleToUser(form.getUsername(), roleName);
                    } else {
                        log.info("user: {} already has role: {}", form.getUsername(), roleName);
                    }
                }
        );
        //userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                TokenUtil tokenUtil = new TokenUtil();
                String access_token = tokenUtil.refreshToken(refresh_token, request.getRequestURL().toString(), userService);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                log.error("Error during refresh token: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            log.error("No Bearer Refresh token is missing");
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> getToken() {
        log.info("Login");
        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToUserForm {
    private String username;
    private Collection<String> rolenames;
}