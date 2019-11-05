package ru.Illarionov.UsersService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.Illarionov.UsersService.controller.data.ChangedUserStatus;
import ru.Illarionov.UsersService.controller.data.CreatedUser;
import ru.Illarionov.UsersService.exceptions.UserNotFoundException;
import ru.Illarionov.UsersService.model.StatusesWithId;
import ru.Illarionov.UsersService.model.Users;
import ru.Illarionov.UsersService.service.impl.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Users findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long save(@RequestBody CreatedUser createdUser) {
        return userService.addUser(createdUser);
    }

    @RequestMapping(value = "/change/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusesWithId updateStatus (@PathVariable ("id")Long id, @RequestBody ChangedUserStatus changedUserStatus) throws UserNotFoundException {
        return userService.changeStatus(id, changedUserStatus);
    }
}
