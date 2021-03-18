package com.innowisegroup.strategy;

import com.innowisegroup.data.ConsoleDataReader;
import com.innowisegroup.data.DataReader;
import com.innowisegroup.entity.User;
import com.innowisegroup.repository.UserRepository;

import java.util.Optional;

public class DeleteUserStrategy implements Strategy {

    private static final String GET_MESSAGE = "Enter deleted user's id:";

    private final DataReader dataReader = ConsoleDataReader.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    public void execute() {

        User user;
        try {
            user = findUser();
        } catch (StrategyException e) {
            System.out.println(e.getMessage());
            return;
        }

        userRepository.delete(user);
        System.out.println("User is deleted");
    }

    private User findUser() throws StrategyException {

        while (true) {
            System.out.println(GET_MESSAGE);
            String input = dataReader.readLine();

            int id;
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Id is invalid. Try again");
                continue;
            }

            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isEmpty()) {
                throw new StrategyException("There is no user with given id");
            }

            return optionalUser.get();
        }
    }
}
