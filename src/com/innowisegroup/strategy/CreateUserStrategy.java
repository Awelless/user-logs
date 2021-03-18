package com.innowisegroup.strategy;

import com.innowisegroup.repository.UserRepository;

public class CreateUserStrategy extends AbstractSetFieldsStrategy {

    private static final String NAME_MESSAGE = "Enter user's name:";
    private static final String SURNAME_MESSAGE = "Enter user's surname:";
    private static final String EMAIL_MESSAGE = "Enter user's email:";
    private static final String ROLES_MESSAGE = """
                    Available roles:
                    USER - rank 1
                    CUSTOMER - rank 1
                    ADMIN - rank 2
                    PROVIDER - rank 2
                    SUPER_ADMIN - rank 3
                    Enter user's roles (one by one, separated by space character):""";
    private static final String PHONE_NUMBERS_MESSAGE =
            "Enter phone numbers (one by one, separated by space character):";

    @Override
    public void execute() {
        processNameCreation();
        processSurnameCreation();
        processEmailCreation();
        processRolesCreation();
        processPhoneNumbersCreation();
        saveUser();

        System.out.println("User is created");
    }

    private void saveUser() {
        UserRepository userRepository = UserRepository.getInstance();
        userRepository.save(user);
    }

    @Override
    protected String getNameMessage() {
        return NAME_MESSAGE;
    }

    @Override
    protected String getSurnameMessage() {
        return SURNAME_MESSAGE;
    }

    @Override
    protected String getEmailMessage() {
        return EMAIL_MESSAGE;
    }

    @Override
    protected String getRolesMessage() {
        return ROLES_MESSAGE;
    }

    @Override
    protected String getPhoneNumbersMessage() {
        return PHONE_NUMBERS_MESSAGE;
    }
}
