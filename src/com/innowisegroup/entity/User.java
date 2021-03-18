package com.innowisegroup.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private List<UserRole> roles;
    private List<String> phones;

    public User() {
    }

    public User(Integer id, String name, String surname, String email, List<UserRole> roles, List<String> phones) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = new ArrayList<>(roles);
        this.phones = new ArrayList<>(phones);
    }

    public User(User user) {
        this(user.id, user.name, user.surname, user.email, user.roles, user.phones);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getRoles() {
        return new ArrayList<>(roles);
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = new ArrayList<>(roles);
    }

    public List<String> getPhones() {
        return new ArrayList<>(phones);
    }

    public void setPhones(List<String> phones) {
        this.phones = new ArrayList<>(phones);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(id, user.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User: " +
                "id = " + id + "   " +
                "name = '" + name + "'   " +
                "Surname = '" + surname + "'   " +
                "email = '" + email + "'   " +
                "roles = " + roles + "   " +
                "phones = " + phones;
    }
}
