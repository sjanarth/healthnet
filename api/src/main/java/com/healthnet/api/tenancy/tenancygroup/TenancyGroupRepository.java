package com.healthnet.api.accounts.tenancygroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenancyGroupRepository extends JpaRepository<TenancyGroup, Integer>
{
    Optional<TenancyGroup> findByName(String name);
    Optional<TenancyGroup> findByEmail(String email);
}