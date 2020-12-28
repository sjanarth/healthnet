package com.healthnet.api.identity.role;

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
@RequestMapping("/api")
public class RoleController
{
    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping("/privilege")
    public ResponseEntity<Role> create(@RequestBody Role role) throws URISyntaxException {
        log.info("Create Privilege : {}", role);
        Role result = privilegeService.savePrivilege(role);
        return ResponseEntity
                .created(new URI("/api/privilege/" +result.getId()))
                .body(result);
    }

    @PutMapping("/privilege/{id}")
    public ResponseEntity<Role> update(@RequestBody Role role) {
        log.info("Update Privilege : {}", role);
        Role result = privilegeService.savePrivilege(role);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/privilege/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("Delete Privilege : {}", id);
        privilegeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/privilege/{id}")
    public ResponseEntity<Role> getById(@PathVariable Integer id) {
        Optional<Role> privilege = privilegeService.findById(id);
        return privilege
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/privilege/{name}")
    public ResponseEntity<Role> getByName(@PathVariable String name) {
        Optional<Role> privilege = privilegeService.findByName(name);
        return privilege
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/privileges")
    public Collection<Role> list()    {
        return privilegeService.findAll();
    }
}