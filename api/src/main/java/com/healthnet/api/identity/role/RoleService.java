package com.healthnet.api.identity.role;

import java.util.Collection;
import java.util.Optional;

public interface PrivilegeService
{
    Collection<Role> findAll();
    Optional<Role> findById(Integer id);
    Optional<Role> findByName(String name);
    Role savePrivilege(Role user);
    void deleteById(Integer id);
}
