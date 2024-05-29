package org.nsu.oop.task3.controller.pubsub;

public interface Subscriber <T> {
    void handleEvent(T event);
}
