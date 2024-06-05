package org.nsu.oop.task5.pubsub;

public interface Subscriber <T> {
    void onEvent(T event);
}
