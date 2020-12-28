package com.healthnet.api.tenancy.facilitytype;

import java.util.Collection;
import java.util.Optional;

public interface PrivilegeService
{
    Collection<FacilityType> findAll();
    Optional<FacilityType> findById(Integer id);
    Optional<FacilityType> findByName(String name);
    FacilityType savePrivilege(FacilityType user);
    void deleteById(Integer id);
}
