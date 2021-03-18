package com.innowisegroup.uistrategy;

import com.innowisegroup.repository.UserRepository;

public class CreateUserStrategy extends AbstractSetFieldsStrategy {

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
