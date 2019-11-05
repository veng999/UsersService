package ru.Illarionov.UsersService.model;

import lombok.Data;

@Data
public class StatusesWithId {
    private Long userId;
    private String newStatus;
    private String previousStatus;
}
