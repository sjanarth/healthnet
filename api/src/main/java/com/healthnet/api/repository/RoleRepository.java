package com.healthnet.api.repository;

import com.healthnet.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Role findByRole(String role);
}