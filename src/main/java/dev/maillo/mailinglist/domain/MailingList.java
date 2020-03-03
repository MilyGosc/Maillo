package dev.maillo.mailinglist.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MailingList {
    private String id;
    private String name;
    private String description;
    private List<String> users = new ArrayList<>();

    public void grantAccess(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User id can not be empty");
        }

        if (users.contains(userId)) {
            throw new IllegalArgumentException("Access to this mailing list has been already granted for this user");
    }

        users.add(userId);
    }

    public void revokeAccess(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User id can not be empty");
        }

        if (!users.contains(userId)) {
            throw new IllegalArgumentException("User does not have access to this mailing list");
        }

        if (users.size() == 1) {
            throw new IllegalArgumentException("Access to mailing list must be granted for at least one user");
        }

        users.remove(userId);
    }
}
