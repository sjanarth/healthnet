package com.healthnet.api.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Collection<User> findAll()   {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id)  {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email)    {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteById(Integer id)   {
        log.info("Deleting user("+id+")");
        userRepository.deleteById(id);
    }

    @Override
    public User saveUser(User user)     {
        log.info("Saving user("+user.getEmail()+")");
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        userRepository.save(user);
        return user;
    }

    @Override
    public UserPrincipal loadUserByUsername(String email) throws AuthenticationException {
        log.info("loadUserByUsername user="+email+", userRepository="+userRepository);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new BadCredentialsException(email);
        }
        return new UserPrincipal(user);
    }
}