package com.healthnet.api.tenancy.tenancygroup;

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
public class TenancyGroupController
{
    @Autowired
    private TenancyGroupService tenancyGroupService;

    @PostMapping("/tenancyGroup")
    public ResponseEntity<TenancyGroup> create(@RequestBody TenancyGroup tenancyGroup) throws URISyntaxException {
        log.info("Create TenancyGroup : {}", tenancyGroup);
        TenancyGroup result = tenancyGroupService.saveTenancyGroup(tenancyGroup);
        return ResponseEntity
                .created(new URI("/api/tenancyGroup/" +result.getId()))
                .body(result);
    }

    @PutMapping("/tenancyGroup/{id}")
    public ResponseEntity<TenancyGroup> update(@RequestBody TenancyGroup tenancyGroup) {
        log.info("Update TenancyGroup : {}", tenancyGroup);
        TenancyGroup result = tenancyGroupService.saveTenancyGroup(tenancyGroup);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/tenancyGroup/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("Delete TenancyGroup : {}", id);
        tenancyGroupService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tenancyGroup/{id}")
    public ResponseEntity<TenancyGroup> getById(@PathVariable Integer id) {
        Optional<TenancyGroup> tenancyGroup = tenancyGroupService.findById(id);
        return tenancyGroup
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tenancyGroup/{name}")
    public ResponseEntity<TenancyGroup> getByName(@PathVariable String name) {
        Optional<TenancyGroup> tenancyGroup = tenancyGroupService.findByShortName(name);
        return tenancyGroup
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tenancyGroups")
    public Collection<TenancyGroup> list()    {
        return tenancyGroupService.findAll();
    }
}