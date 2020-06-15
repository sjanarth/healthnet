package com.healthnet.api.controller;

import com.healthnet.api.entity.User;
import com.healthnet.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Collection<User> users() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        Optional<User> user = userService.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        return user != null
                    ? ResponseEntity.ok().body(user)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        log.info("Create User : {}", user);
        User result = userService.saveUser(user);
        return ResponseEntity.created(new URI("/api/group/" + result.getId()))
                .body(result);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        log.info("Update User : {}", user);
        User result = userService.saveUser(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        log.info("Delete User : {}", id);
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}