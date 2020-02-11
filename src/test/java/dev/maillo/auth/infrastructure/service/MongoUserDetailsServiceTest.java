package dev.maillo.auth.infrastructure.service;

import dev.maillo.user.domain.User;
import dev.maillo.user.domain.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
public class MongoUserDetailsServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @Test
    void loadUserByUsername_ExistingUser_ShouldFindUser() {
        User user = new User();
        user.setUsername("john");
        userRepository.save(user);

        Assertions.assertNotNull(mongoUserDetailsService.loadUserByUsername("john"), "User does not exists");

        userRepository.delete(user);
    }

    @Test
    void loadUserByUsername_NonExistingUser_ShouldThrowException() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> mongoUserDetailsService.loadUserByUsername("ethan"), "User should not exists");
    }

}
