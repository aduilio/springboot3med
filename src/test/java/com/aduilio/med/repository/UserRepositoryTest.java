package com.aduilio.med.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.aduilio.med.entity.User;

/**
 * Unit tests for the class {@link UserRepository}.
 */
@DataJpaTest
public class UserRepositoryTest {

    private static final String USERNAME = "username";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByUsername_withValue_shouldReturnUser() {
        createUser();
        var result = userRepository.findByUsername(USERNAME);

        assertThat(result.getUsername()).isEqualTo(USERNAME);
    }

    private void createUser() {
        entityManager.persist(User.builder().username(USERNAME).password("password").build());
    }
}
