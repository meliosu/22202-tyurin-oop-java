package org.nsu.oop.task5.network.observe;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();

    public void publishEvent(Event event) {
        for (Observer observer : observers) {
            observer.handleEvent(event);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}
