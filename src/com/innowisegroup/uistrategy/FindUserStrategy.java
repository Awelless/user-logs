package com.innowisegroup.uistrategy;

import com.innowisegroup.data.ConsoleDataReader;
import com.innowisegroup.data.DataReader;
import com.innowisegroup.entity.User;
import com.innowisegroup.repository.Specification;
import com.innowisegroup.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FindUserStrategy implements UiStrategy {

    private static final String CHOOSE_OPTION_MESSAGE = """
                    1 - Get by id
                    2 - Get by name
                    3 - Get by surname
                    4 - Get by name and surname
                    5 - Get all existing users
                    Choose search criteria type:""";
    private static final String ID_MESSAGE = "Enter user's id";
    private static final String NAME_MESSAGE = "Enter user's name";
    private static final String SURNAME_MESSAGE = "Enter user's surname";

    private final DataReader dataReader = ConsoleDataReader.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    public void execute() {

        System.out.println("-------------------------------");

        FindUserStrategyType type = getType();

        switch (type) {
            case BY_ID:
                getById();
                break;
            case BY_NAME:
                getByName();
                break;
            case BY_SURNAME:
                getBySurname();
                break;
            case BY_NAME_AND_SURNAME:
                getByNameAndSurname();
                break;
            case ALL:
                getAll();
                break;
            default:
                System.out.println("Unknown criteria type");
        }
    }

    private FindUserStrategyType getType() {

        while (true) {
            System.out.println(CHOOSE_OPTION_MESSAGE);
            String id = dataReader.readLine();

            Optional<FindUserStrategyType> typeOptional = FindUserStrategyType.getById(id);

            if (typeOptional.isEmpty()) {
                System.out.println("Criteria type is invalid. Try again");
                continue;
            }

            return typeOptional.get();
        }
    }

    private void getById() {

        while (true) {
            System.out.println(ID_MESSAGE);
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
                System.out.println("Users found: 0");
            } else {
                System.out.println("Users found: 1");
                System.out.println(optionalUser.get());
            }

            break;
        }
    }

    private void getByName() {

        System.out.println(NAME_MESSAGE);
        String name = dataReader.readLine();

        Specification<User> specification = user -> user.getName().equals(name);

        List<User> users = userRepository.findAll(specification);
        printUsers(users);
    }

    private void getBySurname() {

        System.out.println(SURNAME_MESSAGE);
        String surname = dataReader.readLine();

        Specification<User> specification = user -> user.getSurname().equals(surname);

        List<User> users = userRepository.findAll(specification);
        printUsers(users);
    }

    private void getByNameAndSurname() {

        System.out.println(NAME_MESSAGE);
        String name = dataReader.readLine();

        System.out.println(SURNAME_MESSAGE);
        String surname = dataReader.readLine();

        Specification<User> specification = user ->
                user.getName().equals(name) && user.getSurname().equals(surname);

        List<User> users = userRepository.findAll(specification);
        printUsers(users);
    }

    private void getAll() {
        List<User> users = userRepository.findAll();
        printUsers(users);
    }

    private void printUsers(Collection<User> users) {
        System.out.println("Users found: " + users.size());
        users.forEach(System.out::println);
    }
}
