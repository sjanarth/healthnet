package com.healthnet.api.runner;

import com.healthnet.api.facilitytype.FacilityType;
import com.healthnet.api.facilitytype.FacilityTypeRepository;
import com.healthnet.api.facilitytype.SeededFacilityTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Order(103)
@Component
public class HealthnetSeededFacilityTypesLoader implements CommandLineRunner
{
    @Autowired
    private FacilityTypeRepository facilityTypeRepository;

    @Override
    public void run (String ... args) throws Exception {
        Map<String, FacilityType> currentFacilityTypes = facilityTypeRepository.findAll().stream().collect(toMap(ft -> ft.getName(), ft -> ft));
        for (SeededFacilityTypes sft : SeededFacilityTypes.values()) {
            FacilityType ft = currentFacilityTypes.get(sft.name);
            if (ft == null)  {
                ft = new FacilityType();
                ft.setName(sft.name);
                ft.setDescription(sft.description);
                ft.setSeeded(true);
                System.out.println("Loading Healthnet Seeded FacilityType "+sft.name);
                facilityTypeRepository.saveAndFlush(ft);
            } else if (!ft.getDescription().equals(sft.description))  {
                ft.setDescription(sft.description);
                System.out.println("Syncing Healthnet Seeded FacilityType "+sft.name);
                facilityTypeRepository.saveAndFlush(ft);
            }
        }
    }
}
