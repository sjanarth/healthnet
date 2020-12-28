package com.healthnet.api.tenancy.facilitytype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class FacilityTypeServiceImpl implements FacilityTypeService
{
    @Autowired
    private FacilityTypeRepository privilegeRepository;

    @Override
    public Collection<FacilityType> findAll()   {
        return privilegeRepository.findAll();
    }

    @Override
    public Optional<FacilityType> findById(Integer id)  {
        return privilegeRepository.findById(id);
    }

    @Override
    public Optional<FacilityType> findByName(String name)    {
        return privilegeRepository.findByName(name);
    }

    @Override
    public void deleteById(Integer id)   {
        log.info("Deleting privilege("+id+")");
        privilegeRepository.deleteById(id);
    }

    @Override
    public FacilityType savePrivilege(FacilityType facilityType)     {
        log.info("Saving privilege("+ facilityType.getName()+")");
        privilegeRepository.save(facilityType);
        return facilityType;
    }
}
