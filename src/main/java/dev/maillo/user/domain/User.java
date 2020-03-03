package dev.maillo.user.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private Set<String> roles;
    private Date created;
    private List<String> mailingLists = new ArrayList<>();

    public void addMailingList(String mailingListId) {
        if (mailingListId == null || mailingListId.isBlank()) {
            throw new IllegalArgumentException("Mailing list id can not null or be empty");
        }

        if (mailingLists.contains(mailingListId)) {
            throw new IllegalArgumentException("User already have access to this mailing list");
        }

        mailingLists.add(mailingListId);
    }

    public void removeMailingList(String mailingListId) {
        if (mailingListId == null || mailingListId.isBlank()) {
            throw new IllegalArgumentException("Mailing list id can not be empty");
        }

        if (!mailingLists.contains(mailingListId)) {
            throw new IllegalArgumentException("User does not have access to this mailing list");
        }

        mailingLists.remove(mailingListId);
    }
}
