package org.nsu.oop.task5.network.pubsub2;

import org.nsu.oop.task5.controller.events.GameEvent;

import java.util.HashMap;
import java.util.Map;

public abstract class Subscriber {
    private final Map<Class<?>, Handler> handlers = new HashMap<>();

    public void handleEvent(Event event) {
        handlers.get(event.getClass()).handle(event);
    }

    public void addHandler(Class<?> clazz, Handler handler) {
        handlers.put(clazz, handler);
    }
}
