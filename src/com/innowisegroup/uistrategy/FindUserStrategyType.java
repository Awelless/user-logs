package com.innowisegroup.uistrategy;

import java.util.Arrays;
import java.util.Optional;

public enum FindUserStrategyType {
    BY_ID(1),
    BY_NAME(2),
    BY_SURNAME(3),
    BY_NAME_AND_SURNAME(4),
    ALL(5);

    private final int id;

    FindUserStrategyType(int id) {
        this.id = id;
    }

    public static Optional<FindUserStrategyType> getById(String id) {

        FindUserStrategyType[] values = FindUserStrategyType.values();

        return Arrays.stream(values)
                .filter(value -> String.valueOf(value.id).equals(id))
                .findFirst();
    }
}
