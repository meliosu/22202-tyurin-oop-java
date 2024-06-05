package org.nsu.oop.task5.pubsub;

public interface Publisher <T> {
    void publishEvent(T event);
}
