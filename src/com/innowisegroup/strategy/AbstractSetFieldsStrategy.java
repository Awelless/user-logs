package com.innowisegroup.strategy;

import com.innowisegroup.data.ConsoleDataReader;
import com.innowisegroup.data.DataReader;
import com.innowisegroup.entity.User;
import com.innowisegroup.entity.UserRole;
import com.innowisegroup.parser.PhoneNumbersParser;
import com.innowisegroup.parser.RolesParser;
import com.innowisegroup.validator.*;

import java.util.List;

public abstract class AbstractSetFieldsStrategy implements Strategy {

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
