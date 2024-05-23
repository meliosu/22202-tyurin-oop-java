package org.nsu.oop.task4.pubsub;

public interface Subscriber <T> {
    void onEvent(T event);
}
