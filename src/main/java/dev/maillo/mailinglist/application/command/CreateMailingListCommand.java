package dev.maillo.mailinglist.application.command;

import lombok.Data;

@Data
public class CreateMailingListCommand {

    private String name;

    private String description;

    private String issuerUsername;

    public CreateMailingListCommand(String name, String description, String issuerUsername) {
        this.name = name;
        this.description = description;
        this.issuerUsername = issuerUsername;
    }
}
