package com.healthnet.api.tenancy.facilitytype;

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
public class FacilityTypeController
{
    @Autowired
    private FacilityTypeService privilegeService;

    @PostMapping("/privilege")
    public ResponseEntity<FacilityType> create(@RequestBody FacilityType facilityType) throws URISyntaxException {
        log.info("Create Privilege : {}", facilityType);
        FacilityType result = privilegeService.savePrivilege(facilityType);
        return ResponseEntity
                .created(new URI("/api/privilege/" +result.getId()))
                .body(result);
    }

    @PutMapping("/privilege/{id}")
    public ResponseEntity<FacilityType> update(@RequestBody FacilityType facilityType) {
        log.info("Update Privilege : {}", facilityType);
        FacilityType result = privilegeService.savePrivilege(facilityType);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/privilege/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("Delete Privilege : {}", id);
        privilegeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/privilege/{id}")
    public ResponseEntity<FacilityType> getById(@PathVariable Integer id) {
        Optional<FacilityType> privilege = privilegeService.findById(id);
        return privilege
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/privilege/{name}")
    public ResponseEntity<FacilityType> getByName(@PathVariable String name) {
        Optional<FacilityType> privilege = privilegeService.findByName(name);
        return privilege
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/privileges")
    public Collection<FacilityType> list()    {
        return privilegeService.findAll();
    }
}