package com.aduilio.med.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aduilio.med.entity.User;

/**
 * Provides the methods to persist an User.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Returns an user by username.
     * 
     * @param username the username
     * @return User
     */
    public User findByUsername(String username);
}
