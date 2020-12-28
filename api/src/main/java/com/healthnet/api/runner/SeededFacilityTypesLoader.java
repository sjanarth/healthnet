package com.healthnet.api.runner;

import com.healthnet.api.identity.privilege.Privilege;
import com.healthnet.api.identity.privilege.PrivilegeRepository;
import com.healthnet.api.identity.privilege.SeededPrivileges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Order(1)
@Component
public class SeededPrivilegesLoader implements CommandLineRunner
{
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public void run (String ... args) throws Exception {
        Map<String, Privilege> currentPrivileges = privilegeRepository.findAll().stream().collect(toMap(p -> p.getName(), p -> p));
        for (SeededPrivileges sp : SeededPrivileges.values()) {
            Privilege p = currentPrivileges.get(sp.name);
            if (p == null)  {
                p = new Privilege();
                p.setName(sp.name);
                p.setDescription(sp.description);
                p.setSeeded(true);
                System.out.println("Loading Seeded Privilege "+sp.name);
                privilegeRepository.saveAndFlush(p);
            } else if (!p.getDescription().equals(sp.description))  {
                p.setDescription(sp.description);
                System.out.println("Syncing Seeded Privilege "+sp.name);
                privilegeRepository.saveAndFlush(p);
            }
        }
    }
}
