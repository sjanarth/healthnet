package com.healthnet.api.facility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class FacilityServiceImpl implements FacilityService
{
    @Autowired
    FacilityRepository facilityRepository;

    @Override
    public Collection<Facility> findAll() {
        return facilityRepository.findAll();
    }

    @Override
    public Optional<Facility> findById(Integer id) {
        return facilityRepository.findById(id);
    }

    @Override
    public Optional<Facility> findByEmail(String email) {
        return facilityRepository.findByEmail(email);
    }

    @Override
    public Optional<Facility> findByPhone(String phone) { return facilityRepository.findByPhone(phone); }

    @Override
    public Facility saveFacility(Facility facility) {
        log.info("Saving facility ("+facility.getEmail()+")");
        return facilityRepository.save(facility);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting facility ("+id+")");
        facilityRepository.deleteById(id);
    }
}