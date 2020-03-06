package dev.maillo.mailinglist.web;

import dev.maillo.mailinglist.application.MailingListApplicationService;
import dev.maillo.mailinglist.application.command.CreateMailingListCommand;
import dev.maillo.mailinglist.application.dto.CreateMailingListCommandDto;
import dev.maillo.mailinglist.domain.MailingList;
import dev.maillo.user.infrastrucutre.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    public String createMailingListPage() {
        return "mailinglist/createMailingList";
    }

    @PostMapping("/mailing-list/create")
    public String createMailingList(@Valid @ModelAttribute CreateMailingListCommandDto commandDto, Authentication authentication) throws UserNotFoundException {
        CreateMailingListCommand command = new CreateMailingListCommand(commandDto.getName(), commandDto.getDescription(), authentication.getName());
        mailingListApplicationService.create(command);
        return "redirect:/mailing-list";
    }
}
