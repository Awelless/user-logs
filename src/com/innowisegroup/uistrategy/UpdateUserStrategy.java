package com.innowisegroup.uistrategy;

import com.innowisegroup.entity.User;
import com.innowisegroup.repository.UserRepository;

import java.util.Optional;

public class UpdateUserStrategy extends AbstractSetFieldsStrategy {

    private static final String GET_MESSAGE = "Enter new user's id:";
    private static final String NAME_MESSAGE = "Enter new user's name:";
    private static final String SURNAME_MESSAGE = "Enter new user's surname:";
    private static final String EMAIL_MESSAGE = "Enter new user's email:";
    private static final String ROLES_MESSAGE =
            "Available roles:" +
            "USER - rank 1" +
            "CUSTOMER - rank 1" +
            "ADMIN - rank 2" +
            "PROVIDER - rank 2" +
            "SUPER_ADMIN - rank 3" +
            "Enter new user's roles (one by one, separated by space character):";
    private static final String PHONE_NUMBERS_MESSAGE =
            "Enter new phone numbers (one by one, separated by space character):";

    @Override
    public void execute() {

        try {
            findUser();
        } catch (UiStrategyException e) {
            System.out.println(e.getMessage());
            return;
        }

        processNameCreation();
        processSurnameCreation();
        processEmailCreation();
        processRolesCreation();
        processPhoneNumbersCreation();
        saveUser();

        System.out.println("User is updated");
    }

    private void findUser() throws UiStrategyException {

        UserRepository userRepository = UserRepository.getInstance();

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
                throw new UiStrategyException("There is no user with given id");
            }

            user = optionalUser.get();
            break;
        }
    }

    private void saveUser() {
        UserRepository userRepository = UserRepository.getInstance();
        userRepository.save(user);
    }

    @Override
    protected String getNameMessage() {
        return "(Current name is: " + user.getName() + ")\n" + NAME_MESSAGE;
    }

    @Override
    protected String getSurnameMessage() {
        return "(Current surname is: " + user.getSurname() + ")\n" + SURNAME_MESSAGE;
    }

    @Override
    protected String getEmailMessage() {
        return "(Current email is: " + user.getEmail() + ")\n" + EMAIL_MESSAGE;
    }

    @Override
    protected String getRolesMessage() {
        return "(Current roles are: " + user.getRoles() + ")\n" + ROLES_MESSAGE;
    }

    @Override
    protected String getPhoneNumbersMessage() {
        return "(Current phone numbers are: " + user.getPhones() + ")\n" + PHONE_NUMBERS_MESSAGE;
    }
}
