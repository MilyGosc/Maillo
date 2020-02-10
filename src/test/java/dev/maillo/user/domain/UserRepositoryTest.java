package dev.maillo.user.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Set;

@DataMongoTest
public class UserRepositoryTest {

    // todo: delete this class; local dev purpose only

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        User user = new User();
        user.setUsername("admin");
        user.setCreated(new Date());
        user.setRoles(Set.of("ROLE_ADMIN"));
        user = userRepository.save(user);
        Assert.notNull(userRepository.findById(user.getId()), "User has not been saved");
    }
}
