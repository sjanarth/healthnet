package com.healthnet.api.service;

import com.healthnet.api.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService
{
    public Collection<User> findAll();
    public Optional<User> findById(Integer id);
    public User findUserByEmail(String email);
    public User saveUser(User user);
    public void deleteById(Integer id);
}