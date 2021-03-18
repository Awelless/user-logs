package com.innowisegroup.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements Validator {

    private static final String NAME_REGEX = "^[a-zA-Z]+$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    @Override
    public void test(String input) throws ValidationException {

        Matcher matcher = NAME_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new ValidationException("Name is invalid");
        }
    }
}
