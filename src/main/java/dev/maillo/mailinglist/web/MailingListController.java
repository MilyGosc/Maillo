package dev.maillo.mailinglist.web;

import dev.maillo.mailinglist.application.MailingListApplicationService;
import dev.maillo.mailinglist.domain.MailingList;
import dev.maillo.user.infrastrucutre.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MailingListController {

    @Autowired
    private MailingListApplicationService mailingListApplicationService;

    @GetMapping("/mailing-list")
    public String getMailingLists(Authentication authentication, Model model) throws UserNotFoundException {
        List<MailingList> mailingLists = mailingListApplicationService.getUserMailingLists(authentication.getName());
        model.addAttribute("mailingLists", mailingLists);
        return "mailinglist/getMailingLists";
    }

    @GetMapping("/mailing-list/create")
    public String createMailingList() {
        return "mailinglist/createMailingList";
    }
}
