package ru.Illarionov.UsersService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Illarionov.UsersService.controller.data.ChangedUserStatus;
import ru.Illarionov.UsersService.controller.data.CreatedUser;
import ru.Illarionov.UsersService.exceptions.UserNotFoundException;
import ru.Illarionov.UsersService.model.StatusesWithId;
import ru.Illarionov.UsersService.model.Users;
import ru.Illarionov.UsersService.repository.UsersRepository;
import ru.Illarionov.UsersService.service.MainService;

@Service
public class UserService implements MainService {
    private final UsersRepository repository;

    @Autowired
    public UserService(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public Users findById(Long id) {
        return repository.findUserById(id);
    }

    @Transactional
    @Override
    public Long addUser(CreatedUser createdUser) {
        Users user = new Users();
        user.setUserName(createdUser.getUserName());
        user.setEmail(createdUser.getEmail());
        user.setPhoneNumber(createdUser.getPhoneNumber());
        user.setBankName(createdUser.getBankName());
        user.setSex(createdUser.getSex());
        user.setIsAdult(createdUser.getIsAdult());
        user.setUserStatus(createdUser.getUserStatus());
        Users saveUser = repository.save(user);
        return saveUser.getId();
    }

    @Override
    public StatusesWithId changeStatus(Long id, ChangedUserStatus changedUserStatus) throws UserNotFoundException {
        Users existingUsers = repository.findUserById(id);
        if (existingUsers != null){
            String previousStatus = existingUsers.getUserStatus();
            String newStatus = changedUserStatus.getUserStatus();
        existingUsers.setUserStatus(newStatus);
        repository.updateUserStatus(id, newStatus);
            StatusesWithId statusesWithId = new StatusesWithId();
            statusesWithId.setPreviousStatus(previousStatus);
            statusesWithId.setNewStatus(newStatus);
            statusesWithId.setUserId(id);
            return statusesWithId;
        }
        else throw new UserNotFoundException("User not found");
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void checkLastOnline(){
        repository.checkLastOnline();
    }

}

