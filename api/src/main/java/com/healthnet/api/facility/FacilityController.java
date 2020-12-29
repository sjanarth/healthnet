package com.healthnet.api.facility;

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
public class FacilityController
{
    @Autowired
    private FacilityService facilityService;

    @PostMapping("/facility")
    public ResponseEntity<Facility> create(@RequestBody Facility facility) throws URISyntaxException {
        log.info("Create Facility : {}", facility);
        Facility result = facilityService.saveFacility(facility);
        return ResponseEntity
                .created(new URI("/api/facility/" +result.getId()))
                .body(result);
    }

    @PutMapping("/facility/{id}")
    public ResponseEntity<Facility> update(@RequestBody Facility facility) {
        log.info("Update Facility : {}", facility);
        Facility result = facilityService.saveFacility(facility);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/facility/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("Delete Facility : {}", id);
        facilityService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/facility/{id}")
    public ResponseEntity<Facility> getById(@PathVariable Integer id) {
        Optional<Facility> facility = facilityService.findById(id);
        return facility
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/facility/{email}")
    public ResponseEntity<Facility> getByEmail(@PathVariable String email) {
        Optional<Facility> facility = facilityService.findByEmail(email);
        return facility
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/facilities")
    public Collection<Facility> list()    {
        return facilityService.findAll();
    }
}