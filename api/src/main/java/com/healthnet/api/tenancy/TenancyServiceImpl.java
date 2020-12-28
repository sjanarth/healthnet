package com.healthnet.api.accounts.tenancy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class TenancyServiceImpl implements TenancyService
{
    @Autowired
    TenancyRepository tenancyRepository;

    @Override
    public Collection<Tenancy> findAll() {
        return tenancyRepository.findAll();
    }

    @Override
    public Optional<Tenancy> findById(Integer id) {
        return tenancyRepository.findById(id);
    }

    @Override
    public Optional<Tenancy> findByName(String name) {
        return tenancyRepository.findByName(name);
    }

    @Override
    public Tenancy saveTenancy(Tenancy tenancy) {
        log.info("Saving tenancy ("+tenancy.getEmail()+")");
        return tenancyRepository.save(tenancy);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting tenancy ("+id+")");
        tenancyRepository.deleteById(id);
    }
}