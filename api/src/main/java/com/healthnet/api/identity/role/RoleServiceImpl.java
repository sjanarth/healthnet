package com.healthnet.api.identity.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class PrivilegeServiceImpl implements PrivilegeService
{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Collection<Role> findAll()   {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id)  {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByName(String name)    {
        return roleRepository.findByName(name);
    }

    @Override
    public void deleteById(Integer id)   {
        log.info("Deleting privilege("+id+")");
        roleRepository.deleteById(id);
    }

    @Override
    public Role savePrivilege(Role role)     {
        log.info("Saving privilege("+ role.getName()+")");
        roleRepository.save(role);
        return role;
    }
}
