package dev.maillo.auth.domain;

import dev.maillo.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MailloUserPrincipalTest {

    @Test
    public void getAuthorities_ProperUserGiven_ShouldReturnAuthorities() {
        User user = new User();
        user.setId("5e4148295b0d555cba4089a9");
        user.setUsername("test");
        user.setPassword("$2y$10$pobSnkhz1HZdp2TypkKZMey0SIQP8hURnEdvFy2FODwF0RpuHsUCK"); // testtest
        user.setCreated(new Date());
        user.setRoles(Set.of("ROLE_USER"));

        UserDetails userDetails = new MailloUserPrincipal(user);

        assertTrue(userDetails.getAuthorities() instanceof List, "Authorities are not a List");
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")),
                "Authorities list does not contain ROLE_USER");
    }
}
