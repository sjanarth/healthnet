package com.healthnet.api.facility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer>
{
    Optional<Facility> findByPhone(String phone);
    Optional<Facility> findByEmail(String email);
}