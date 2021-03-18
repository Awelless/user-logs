package com.innowisegroup.parser;

import java.util.Arrays;
import java.util.List;

public class PhoneNumbersParser {

    private static final String SPLITERATOR = " +";

    public List<String> parse(String input) {

        String[] phoneNumbers = input.split(SPLITERATOR);
        return Arrays.asList(phoneNumbers);
    }
}
