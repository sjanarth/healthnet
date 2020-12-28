package com.healthnet.api.accounts.tenancygroup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class TenancyGroupServiceImpl implements TenancyGroupService
{
    @Autowired
    private TenancyGroupRepository tenancyGroupRepository;

    @Override
    public Collection<TenancyGroup> findAll() {
        return tenancyGroupRepository.findAll();
    }

    @Override
    public Optional<TenancyGroup> findById(Integer id) {
        return tenancyGroupRepository.findById(id);
    }

    @Override
    public Optional<TenancyGroup> findByName(String name) {
        return tenancyGroupRepository.findByName(name);
    }

    @Override
    public TenancyGroup saveTenancyGroup(TenancyGroup tenancyGroup) {
        log.info("Saving Tenancy Group ("+tenancyGroup.getEmail()+")");
        return tenancyGroupRepository.save(tenancyGroup);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting Tenancy Group ("+id+")");
        tenancyGroupRepository.deleteById(id);
    }
}