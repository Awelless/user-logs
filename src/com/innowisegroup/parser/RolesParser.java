package com.innowisegroup.parser;

import com.innowisegroup.entity.UserRole;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RolesParser {

    private static final String SPLITERATOR = " +";

    public List<UserRole> parse(String input) {

        String[] roles = input.split(SPLITERATOR);

        return Arrays.stream(roles)
                .map(UserRole::valueOf)
                .collect(Collectors.toList());
    }
}
