package com.healthnet.api.tenancy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenancyRepository extends JpaRepository<Tenancy, Integer>
{
    Optional<Tenancy> findByName(String name);
    Optional<Tenancy> findByEmail(String email);
}