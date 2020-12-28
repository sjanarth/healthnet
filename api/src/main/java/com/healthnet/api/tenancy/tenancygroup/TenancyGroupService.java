package com.healthnet.api.accounts.tenancygroup;

import java.util.Collection;
import java.util.Optional;

public interface TenancyGroupService
{
    Collection<TenancyGroup> findAll();
    Optional<TenancyGroup> findById(Integer id);
    Optional<TenancyGroup> findByName(String name);
    TenancyGroup saveTenancyGroup(TenancyGroup tenancyGroup);
    void deleteById(Integer id);
}
