package com.healthnet.api.facilitytype;

import java.util.Collection;
import java.util.Optional;

public interface FacilityTypeService
{
    Collection<FacilityType> findAll();
    Optional<FacilityType> findById(Integer id);
    Optional<FacilityType> findByName(String name);
    FacilityType saveFacilityType(FacilityType user);
    void deleteById(Integer id);
}
