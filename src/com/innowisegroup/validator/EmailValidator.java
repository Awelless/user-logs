package com.innowisegroup.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {

    private static final String EMAIL_REGEX = "^.+@.+\\..+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void test(String input) throws ValidationException {

        Matcher matcher = EMAIL_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new ValidationException("Email is invalid");
        }
    }
}
