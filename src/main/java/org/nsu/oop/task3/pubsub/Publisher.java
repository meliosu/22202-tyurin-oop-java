package org.nsu.oop.task3.pubsub;

public interface Publisher <T> {
    void publishEvent(T event);
}
