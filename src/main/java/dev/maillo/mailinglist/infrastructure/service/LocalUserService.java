package dev.maillo.mailinglist.infrastructure.service;

import dev.maillo.mailinglist.domain.service.UserService;
import dev.maillo.user.application.UserApplicationService;
import dev.maillo.user.application.command.AddMailingListCommand;
import dev.maillo.user.domain.User;
import dev.maillo.user.infrastrucutre.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalUserService implements UserService {

    @Autowired
    private UserApplicationService userApplicationService;

    @Override
    public User getByUsername(String username) throws UserNotFoundException {
        return userApplicationService.getByUsername(username);
    }

    @Override
    public void addMailingList(String userId, String mailingListId) throws UserNotFoundException {
        AddMailingListCommand command = new AddMailingListCommand(userId, mailingListId);
        userApplicationService.addMailingList(command);
    }
}
