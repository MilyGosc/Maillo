package dev.maillo.user.domain;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private Set<String> roles;
    private Date created;
}
