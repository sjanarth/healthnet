package com.healthnet.api.identity.user;

import com.healthnet.api.identity.privilege.Privilege;
import com.healthnet.api.identity.privilege.PrivilegeService;
import com.healthnet.api.identity.role.Role;
import com.healthnet.api.identity.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody User user) throws URISyntaxException {
        log.info("Create User : {}", user);
        User result = userService.saveUser(user);
        return ResponseEntity
                .created(new URI("/api/user/" +result.getId()))
                .body(result);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@RequestBody User user) {
        log.info("Update User : {}", user);
        User result = userService.saveUser(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("Delete User : {}", id);
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        Optional<User> user = userService.findById(id);
        return user
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users")
    public Collection<User> list()    {
        return userService.findAll();
    }

    @GetMapping("/users/privilege/{name}")
    public Collection<User> listWithPrivilege(@PathVariable String privilegeName) {
        Collection<User> users = new ArrayList<User>();
        Optional<Privilege> privilege = privilegeService.findByName(privilegeName);
        if (privilege.isPresent()) {
            users.addAll(userService.findAllWithPrivilege(privilege.get().getName()));
            Collection<Role> roles = roleService.findAllWithPrivilege(privilege.get().getName());
            roles.forEach(r -> users.addAll(userService.findAllWithRole(r.getName())));
        }
        return users;
    }

    @GetMapping("/users/role/{name}")
    public Collection<User> listWithRole(@PathVariable String roleName) {
        Optional<Role> role = roleService.findByName(roleName);
        if (role.isPresent()) {
            return userService.findAllWithRole(role.get().getName());
        } else {
            return new ArrayList<User>();
        }
    }

    @GetMapping("/user/current")
    public ResponseEntity<?> getCurrent(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        } else {
            return ResponseEntity.ok().body(userPrincipal.getUser());
        }
    }
}