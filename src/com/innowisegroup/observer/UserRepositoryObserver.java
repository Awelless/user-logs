package com.innowisegroup.observer;

import com.innowisegroup.data.UserFileDataWriter;
import com.innowisegroup.entity.User;
import com.innowisegroup.repository.UserRepository;

import java.io.IOException;
import java.util.List;

/**
 * Used to save all users from {@link UserRepository} to file
 */
public class UserRepositoryObserver implements Observer {
    @Override
    public void update() throws ObserverException {

        UserRepository repository = UserRepository.getInstance();
        List<User> users = repository.findAll();

        try {
            UserFileDataWriter.write(users);
        } catch (IOException e) {
            throw new ObserverException(e);
        }
    }
}
