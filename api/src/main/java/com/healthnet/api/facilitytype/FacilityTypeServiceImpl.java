package com.healthnet.api.facilitytype;

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
    private FacilityTypeRepository facilityTypeRepository;

    @Override
    public Collection<FacilityType> findAll()   {
        return facilityTypeRepository.findAll();
    }

    @Override
    public Optional<FacilityType> findById(Integer id)  {
        return facilityTypeRepository.findById(id);
    }

    @Override
    public Optional<FacilityType> findByName(String name)    {
        return facilityTypeRepository.findByName(name);
    }

    @Override
    public void deleteById(Integer id)   {
        log.info("Deleting facility type("+id+")");
        facilityTypeRepository.deleteById(id);
    }

    @Override
    public FacilityType saveFacilityType(FacilityType facilityType)     {
        log.info("Saving facility type("+ facilityType.getName()+")");
        facilityTypeRepository.save(facilityType);
        return facilityType;
    }
}
