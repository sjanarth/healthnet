package com.healthnet.api.runner;

import com.healthnet.api.identity.privilege.HealthnetSeededPrivileges;
import com.sugarsaas.api.identity.privilege.Privilege;
import com.sugarsaas.api.identity.privilege.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Order(101)
@Component
public class HealthnetSeededPrivilegesLoader implements CommandLineRunner
{
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public void run (String ... args) throws Exception {
        Map<String, Privilege> currentPrivileges = privilegeRepository.findAll().stream().collect(toMap(p -> p.getName(), p -> p));
        for (HealthnetSeededPrivileges sp : HealthnetSeededPrivileges.values()) {
            Privilege p = currentPrivileges.get(sp.name);
            if (p == null)  {
                p = new Privilege();
                p.setName(sp.name);
                p.setDescription(sp.description);
                p.setSeeded(true);
                System.out.println("Loading Healthnet Seeded Privilege "+sp.name);
                privilegeRepository.saveAndFlush(p);
            } else if (!p.getDescription().equals(sp.description))  {
                p.setDescription(sp.description);
                System.out.println("Syncing Healthnet Seeded Privilege "+sp.name);
                privilegeRepository.saveAndFlush(p);
            }
        }
    }
}
