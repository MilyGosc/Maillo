package dev.maillo.user.application.command;

import lombok.Data;

@Data
public class AddMailingListCommand {

    private String userId;

    private String mailingListId;

    public AddMailingListCommand(String userId, String mailingListId) {
        this.userId = userId;
        this.mailingListId = mailingListId;
    }
}
