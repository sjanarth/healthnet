package com.healthnet.api.tenancy;

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
public class TenancyController
{
    @Autowired
    private TenancyService tenancyService;

    @PostMapping("/tenancy")
    public ResponseEntity<Tenancy> create(@RequestBody Tenancy tenancy) throws URISyntaxException {
        log.info("Create Tenancy : {}", tenancy);
        Tenancy result = tenancyService.saveTenancy(tenancy);
        return ResponseEntity
                .created(new URI("/api/tenancy/" +result.getId()))
                .body(result);
    }

    @PutMapping("/tenancy/{id}")
    public ResponseEntity<Tenancy> update(@RequestBody Tenancy tenancy) {
        log.info("Update Tenancy : {}", tenancy);
        Tenancy result = tenancyService.saveTenancy(tenancy);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/tenancy/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("Delete Tenancy : {}", id);
        tenancyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tenancy/{id}")
    public ResponseEntity<Tenancy> getById(@PathVariable Integer id) {
        Optional<Tenancy> tenancy = tenancyService.findById(id);
        return tenancy
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tenancy/{name}")
    public ResponseEntity<Tenancy> getByName(@PathVariable String name) {
        Optional<Tenancy> tenancy = tenancyService.findByShortName(name);
        return tenancy
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tenancies")
    public Collection<Tenancy> list()    {
        return tenancyService.findAll();
    }

    @GetMapping("/tenancies/tenancygroup/{name}")
    public Collection<Tenancy> listWithTenancyGroup(@PathVariable String tenancyGroupName) {
        return tenancyService.findAllWithTenancyGroup(tenancyGroupName);
    }
}