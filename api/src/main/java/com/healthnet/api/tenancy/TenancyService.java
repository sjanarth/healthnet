package com.healthnet.api.accounts.tenancy;

import java.util.Collection;
import java.util.Optional;

public interface TenancyService
{
    Collection<Tenancy> findAll();
    Optional<Tenancy> findById(Integer id);
    Optional<Tenancy> findByName(String name);
    Tenancy saveTenancy(Tenancy tenancy);
    void deleteById(Integer id);

}
