package dev.maillo.mailinglist.domain;

import dev.maillo.mailinglist.application.command.CreateMailingListCommand;
import org.springframework.stereotype.Component;

@Component
public class MailingListFactory {

    public MailingList create(CreateMailingListCommand command) {
        return new MailingList(command.getName(), command.getDescription());
    }
}
