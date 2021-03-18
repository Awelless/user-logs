package com.innowisegroup.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumbersValidator implements Validator {

    private static final String SPLITERATOR = " +";

    private static final String PHONE_NUMBER_REGEX = "^375\\d{9}$";
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    @Override
    public void test(String input) throws ValidationException {

        String[] phoneNumbers = input.split(SPLITERATOR);

        if (phoneNumbers.length < 1 || phoneNumbers.length > 3) {
            throw new ValidationException("Invalid number of phones: " + phoneNumbers.length);
        }

        testNumbers(phoneNumbers);
    }

    private void testNumbers(String[] phoneNumbers) throws ValidationException {

        List<String> invalidNumbers = new ArrayList<>();

        for (String number : phoneNumbers) {

            Matcher matcher = PHONE_NUMBER_PATTERN.matcher(number);
            if (!matcher.matches()) {
                invalidNumbers.add(number);
            }
        }

        if (invalidNumbers.size() > 0) {

            StringBuilder builder = new StringBuilder();
            invalidNumbers.forEach(number -> builder.append(number).append(' '));

            throw new ValidationException("Invalid phone numbers: " + builder.toString());
        }
    }

}
