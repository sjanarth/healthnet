package com.healthnet.api.facilitytype;

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
    private FacilityTypeService facilityTypeService;

    @PostMapping("/facilityType")
    public ResponseEntity<FacilityType> create(@RequestBody FacilityType facilityType) throws URISyntaxException {
        log.info("Create Privilege : {}", facilityType);
        FacilityType result = facilityTypeService.saveFacilityType(facilityType);
        return ResponseEntity
                .created(new URI("/api/facilityType/" +result.getId()))
                .body(result);
    }

    @PutMapping("/facilityType/{id}")
    public ResponseEntity<FacilityType> update(@RequestBody FacilityType facilityType) {
        log.info("Update Privilege : {}", facilityType);
        FacilityType result = facilityTypeService.saveFacilityType(facilityType);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/facilityType/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("Delete Privilege : {}", id);
        facilityTypeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/facilityType/{id}")
    public ResponseEntity<FacilityType> getById(@PathVariable Integer id) {
        Optional<FacilityType> facilityType = facilityTypeService.findById(id);
        return facilityType
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/facilityType/{name}")
    public ResponseEntity<FacilityType> getByName(@PathVariable String name) {
        Optional<FacilityType> facilityType = facilityTypeService.findByName(name);
        return facilityType
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/facilityTypes")
    public Collection<FacilityType> list()    {
        return facilityTypeService.findAll();
    }
}