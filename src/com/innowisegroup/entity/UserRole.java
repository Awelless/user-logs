package com.innowisegroup.entity;

public enum UserRole {
    USER(1),
    CUSTOMER(1),
    ADMIN(2),
    PROVIDER(2),
    SUPER_ADMIN(3);

    private final int rank;

    public int getRank() {
        return rank;
    }

    UserRole(int rank) {
        this.rank = rank;
    }
}
