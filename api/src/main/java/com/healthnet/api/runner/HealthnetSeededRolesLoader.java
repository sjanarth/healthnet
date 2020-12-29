package com.healthnet.api.runner;

import com.healthnet.api.identity.role.HealthnetSeededRoles;
import com.sugarsaas.api.identity.privilege.Privilege;
import com.sugarsaas.api.identity.privilege.PrivilegeRepository;
import com.sugarsaas.api.identity.privilege.SeededPrivilege;
import com.sugarsaas.api.identity.role.Role;
import com.sugarsaas.api.identity.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Order(102)
@Component
public class HealthnetSeededRolesLoader implements CommandLineRunner
{
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    // TODO: Look at lambdas to simplify this
    public void run (String ... args) throws Exception {
        Map<String, Role> currentRoles = roleRepository.findAll().stream().collect(toMap(r -> r.getName(), r -> r));
        for (HealthnetSeededRoles sr : HealthnetSeededRoles.values()) {
            Role r = currentRoles.get(sr.name);
            if (r == null)  {
                r = new Role();
                r.setName(sr.name);
                r.setDescription(sr.description);
                r.setSeeded(true);
                System.out.println("Loading Healthnet Seeded Role "+sr.name);
                roleRepository.saveAndFlush(r);
            } else if (!r.getDescription().equals(sr.description))  {
                r.setDescription(sr.description);
                System.out.println("Syncing Healthnet Seeded Role "+sr.name);
                roleRepository.saveAndFlush(r);
            } else {
                boolean syncNeeded = false;
                Set<String> rolePrivilegeNames = r.getPrivileges().stream().map(p -> p.getName()).collect(Collectors.toSet());
                // Cheap check
                if (rolePrivilegeNames.size() != sr.privileges.size())  {
                    syncNeeded = true;
                } else {
                    // Compare each privilege
                    for (SeededPrivilege sp : sr.privileges) {
                        if (!rolePrivilegeNames.contains(sp.getName())) {
                            syncNeeded = true;
                            break;
                        }
                    }
                }
                if (syncNeeded) {
                    r.getPrivileges().clear();
                    r.setPrivileges(mapSeededPrivileges2Privileges(sr.getPrivileges()));
                    System.out.println("Syncing Healthnet Seeded Role "+sr.name);
                    roleRepository.saveAndFlush(r);
                }
            }
        }
    }

    private Set<Privilege> mapSeededPrivileges2Privileges(List<SeededPrivilege> seededPrivileges)  {
        Set<Privilege> privileges = new HashSet<>();
        for (SeededPrivilege sp : seededPrivileges)    {
            privileges.add(privilegeRepository.findByName(sp.getName()).get());
        }
        return privileges;
    }
}