package com.healthnet.api.identity.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityTypeRepository extends JpaRepository<Privilege, Integer>
{
    Optional<Privilege> findByName(String name);
}