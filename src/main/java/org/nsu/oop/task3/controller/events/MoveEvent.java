package org.nsu.oop.task3.controller.events;

import org.nsu.oop.task3.util.Position;

public class MoveEvent extends GameEvent {
    public final Position position;

    public MoveEvent(Position position) {
        this.position = position;
    }
}
