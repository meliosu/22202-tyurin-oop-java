package org.nsu.oop.task4.pubsub;

public interface Publisher <T> {
    void publish(T event);
}
