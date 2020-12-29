package com.healthnet.api.facility;

import java.util.Collection;
import java.util.Optional;

public interface FacilityService
{
    Collection<Facility> findAll();
    Optional<Facility> findById(Integer id);
    Optional<Facility> findByEmail(String email);
    Optional<Facility> findByPhone(String phone);
    Facility saveFacility(Facility tenancy);
    void deleteById(Integer id);
}
