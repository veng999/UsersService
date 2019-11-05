package ru.Illarionov.UsersService.service;

import ru.Illarionov.UsersService.controller.data.ChangedUserStatus;
import ru.Illarionov.UsersService.controller.data.CreatedUser;
import ru.Illarionov.UsersService.exceptions.UserNotFoundException;
import ru.Illarionov.UsersService.model.StatusesWithId;
import ru.Illarionov.UsersService.model.Users;

public interface MainService {

    Users findById(Long id) throws UserNotFoundException;
    Long addUser(CreatedUser createdUser);
    StatusesWithId changeStatus(Long id, ChangedUserStatus changedUserStatus) throws UserNotFoundException;
    void checkLastOnline();
}
