package com.healthnet.service;

import com.healthnet.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService
{
    public Collection<User> findAll();
    public Optional<User> findById(Long id);
    public User findUserByEmail(String email);
    public User saveUser(User user);
    public void deleteById(Long id);
}