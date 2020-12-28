package com.healthnet.api.tenancy.facilitytype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<FacilityType, Integer>
{
    Optional<FacilityType> findByName(String name);
}