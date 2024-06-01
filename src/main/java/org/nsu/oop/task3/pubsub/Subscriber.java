package org.nsu.oop.task3.pubsub;

public interface Subscriber <T> {
    void handleEvent(T event);
}
