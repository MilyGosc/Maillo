package dev.maillo.mailinglist.domain.service;

import dev.maillo.user.domain.User;
import dev.maillo.user.infrastrucutre.exception.UserNotFoundException;

public interface UserService {
    User getByUsername(String username) throws UserNotFoundException;
    void addMailingList(String userId, String mailingListId) throws UserNotFoundException;
}
