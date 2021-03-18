package com.innowisegroup.validator;

import com.innowisegroup.entity.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RolesValidator implements Validator {

    private static final String SPLITERATOR = " +";

    private static final String ROLE_REGEX = "^((USER)|(CUSTOMER)|(ADMIN)|(PROVIDER)|(SUPER_ADMIN))$";
    private static final Pattern ROLE_PATTERN = Pattern.compile(ROLE_REGEX);

    @Override
    public void test(String input) throws ValidationException {

        String[] roles = input.split(SPLITERATOR);

        if (roles.length < 1) {
            throw new ValidationException("No roles applied");
        }

        List<UserRole> userRoles = getUserRoles(roles);

        if (userRoles.contains(UserRole.SUPER_ADMIN) && userRoles.size() > 1) {
            throw new ValidationException("Applied roles are incompatible");
        }

        long firstRankRolesCount  = countRolesByRank(userRoles, 1);
        long secondRankRolesCount = countRolesByRank(userRoles, 2);

        if (firstRankRolesCount > 1 || secondRankRolesCount > 1) {
            throw new ValidationException("Applied roles are incompatible");
        }
    }

    private long countRolesByRank(List<UserRole> userRoles, int rank) {
        return userRoles.stream()
                .filter(userRole -> userRole.getRank() == rank)
                .count();
    }

    private List<UserRole> getUserRoles(String[] roles) throws ValidationException {

        List<UserRole> userRoles = new ArrayList<>();
        List<String> invalidRoles = new ArrayList<>();

        for (String role : roles) {

            Matcher matcher = ROLE_PATTERN.matcher(role);
            if (!matcher.matches()) {
                invalidRoles.add(role);
            } else {
                userRoles.add(UserRole.valueOf(role));
            }
        }

        if (invalidRoles.size() > 0) {

            StringBuilder builder = new StringBuilder();
            invalidRoles.forEach(role -> builder.append(role).append(' '));

            throw new ValidationException("Invalid roles: " + builder.toString());
        }

        return userRoles;
    }
}
