package com.healthnet.api.user;

import java.util.Collection;
import java.util.Optional;

public interface UserService
{
    Collection<User> findAll();
    Optional<User> findById(Integer id);
    User findByEmail(String email);
    User saveUser(User user);
    void deleteById(Integer id);
}