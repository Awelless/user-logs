package com.innowisegroup.validator;

public interface Validator {

    void test(String input) throws ValidationException;
}
