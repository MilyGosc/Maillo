package dev.maillo.mailinglist.application.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateMailingListCommandDto {

    @NotBlank
    private String name;

    private String description;
}
