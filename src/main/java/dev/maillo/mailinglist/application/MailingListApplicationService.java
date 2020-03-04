package dev.maillo.mailinglist.application;

import dev.maillo.mailinglist.domain.MailingList;
import dev.maillo.mailinglist.domain.MailingListRepository;
import dev.maillo.mailinglist.domain.service.UserService;
import dev.maillo.user.domain.User;
import dev.maillo.user.infrastrucutre.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailingListApplicationService {

    @Autowired
    private MailingListRepository mailingListRepository;

    @Autowired
    private UserService userService;

    public List<MailingList> getUserMailingLists(String username) throws UserNotFoundException {
        User user = userService.getByUsername(username);
        return mailingListRepository.findAllById(user.getMailingLists());
    }
}
