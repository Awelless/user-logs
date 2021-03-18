package com.innowisegroup.uistrategy;

import com.innowisegroup.data.ConsoleDataReader;
import com.innowisegroup.data.DataReader;
import com.innowisegroup.entity.User;
import com.innowisegroup.entity.UserRole;
import com.innowisegroup.parser.PhoneNumbersParser;
import com.innowisegroup.parser.RolesParser;
import com.innowisegroup.validator.*;

import java.util.List;

public abstract class AbstractSetFieldsStrategy implements UiStrategy {

    protected static final String NAME_MESSAGE = "Enter user's name:";
    protected static final String SURNAME_MESSAGE = "Enter user's surname:";
    protected static final String EMAIL_MESSAGE = "Enter user's email:";
    protected static final String ROLES_MESSAGE =
            "Available roles:\n" +
                    "USER - rank 1\n" +
                    "CUSTOMER - rank 1\n" +
                    "ADMIN - rank 2\n" +
                    "PROVIDER - rank 2\n" +
                    "SUPER_ADMIN - rank 3\n" +
                    "Enter user's roles (one by one, separated by space character):";
    protected static final String PHONE_NUMBERS_MESSAGE =
            "Enter phone numbers (one by one, separated by space character):";

    protected abstract String getNameMessage();
    protected abstract String getSurnameMessage();
    protected abstract String getEmailMessage();
    protected abstract String getRolesMessage();
    protected abstract String getPhoneNumbersMessage();

    protected final DataReader dataReader = ConsoleDataReader.getInstance();

    protected User user = new User();

    protected void processNameCreation() {

        Validator validator = new NameValidator();

        while (true) {
            System.out.println(getNameMessage());
            String name = dataReader.readLine();

            try {
                validator.test(name);
                user.setName(name);
                break;

            } catch (ValidationException e) {
                System.out.println(e.getMessage() + ". Try again");
            }
        }
    }

    protected void processSurnameCreation() {

        Validator validator = new NameValidator();

        while (true) {
            System.out.println(getSurnameMessage());
            String surname = dataReader.readLine();

            try {
                validator.test(surname);
                user.setSurname(surname);
                break;

            } catch (ValidationException e) {
                System.out.println("Surname is invalid. Try again");
            }
        }
    }

    protected void processEmailCreation() {

        Validator validator = new EmailValidator();

        while (true) {
            System.out.println(getEmailMessage());
            String email = dataReader.readLine();

            try {
                validator.test(email);
                user.setEmail(email);
                break;

            } catch (ValidationException e) {
                System.out.println(e.getMessage() + ". Try again");
            }
        }
    }

    protected void processRolesCreation() {

        Validator validator = new RolesValidator();
        RolesParser parser = new RolesParser();

        while (true) {
            System.out.println(getRolesMessage());
            String roles = dataReader.readLine();

            try {
                validator.test(roles);
                List<UserRole> userRoles = parser.parse(roles);
                user.setRoles(userRoles);
                break;

            } catch (ValidationException e) {
                System.out.println(e.getMessage() + ". Try again");
            }
        }
    }

    protected void processPhoneNumbersCreation() {

        Validator validator = new PhoneNumbersValidator();
        PhoneNumbersParser parser = new PhoneNumbersParser();

        while (true) {
            System.out.println(getPhoneNumbersMessage());
            String phoneNumbers = dataReader.readLine();

            try {
                validator.test(phoneNumbers);
                List<String> phones = parser.parse(phoneNumbers);
                user.setPhones(phones);
                break;

            } catch (ValidationException e) {
                System.out.println(e.getMessage() + ". Try again");
            }
        }
    }

}
