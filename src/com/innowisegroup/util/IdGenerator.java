package com.innowisegroup.util;

import com.innowisegroup.initializer.InitializationException;
import com.innowisegroup.observer.ObserverException;
import com.innowisegroup.observer.Observable;
import com.innowisegroup.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public final class IdGenerator implements Observable {

    private static IdGenerator INSTANCE;

    private Integer value;
    private final static List<Observer> observers = new ArrayList<>();

    private IdGenerator(int value) {
        this.value = value;
    }

    public static void init(int starterValue) throws InitializationException {
        if (INSTANCE != null) {
            throw new InitializationException("IdGenerator is already initialized");
        }

        INSTANCE = new IdGenerator(starterValue);
    }

    public static IdGenerator getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("IdGenerator isn't initialized yet");
        }

        return INSTANCE;
    }

    public int getLastValue() {
        return value;
    }

    public int generateNewValue() {
        ++value;
        notifyObservers();
        return value;
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
