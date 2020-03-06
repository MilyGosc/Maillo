package dev.maillo.user.application;

import dev.maillo.user.application.command.AddMailingListCommand;
import dev.maillo.user.domain.User;
import dev.maillo.user.domain.UserRepository;
import dev.maillo.user.infrastrucutre.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    @Autowired
    private UserRepository userRepository;

    public User getByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User identified by username \"" + username + "\" does not exists"));
    }

    public void addMailingList(AddMailingListCommand command) throws UserNotFoundException {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User identified by id \"" + command.getUserId() + "\" does not exists"));
        user.addMailingList(command.getMailingListId());
        userRepository.save(user);
    }
}
