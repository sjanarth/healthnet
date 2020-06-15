package com.healthnet.api.repository;

import com.healthnet.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository ("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);
}