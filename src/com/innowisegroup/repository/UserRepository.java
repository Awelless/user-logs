package com.innowisegroup.repository;

import com.innowisegroup.entity.User;
import com.innowisegroup.initializer.InitializationException;
import com.innowisegroup.observer.ObserverException;
import com.innowisegroup.observer.Observable;
import com.innowisegroup.observer.Observer;
import com.innowisegroup.util.IdGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository implements Repository<User, Integer>, Observable {

    private static UserRepository INSTANCE;

    private final IdGenerator generator;
    private final List<User> users = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    private UserRepository(IdGenerator generator) {
        this.generator = generator;
    }

    public static void init(IdGenerator generator) throws InitializationException {
        if (INSTANCE != null) {
            throw new InitializationException("UserRepository is already initialized");
        }

        INSTANCE = new UserRepository(generator);
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("UserRepository isn't initialized yet");
        }

        return INSTANCE;
    }

    @Override
    public List<User> saveAll(Collection<User> users) {

        return users.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {

        User userCopy = new User(user);

        if (userCopy.getId() == null) {
            userCopy.setId(generator.generateNewValue());
        }

        users.remove(userCopy);
        users.add(userCopy);

        notifyObservers();

        return userCopy;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
        notifyObservers();
    }

    @Override
    public Optional<User> findById(Integer id) {

        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public List<User> findAll(Specification<User> specification) {

        return users.stream()
                .filter(specification::specified)
                .collect(Collectors.toList());
    }

    @Override
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            try {
                observer.update();
            } catch (ObserverException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
