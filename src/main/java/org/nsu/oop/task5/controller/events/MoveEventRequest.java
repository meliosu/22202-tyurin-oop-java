package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.util.Position;

public class MoveEventRequest extends GameEvent {
    public final Position position;

    public MoveEventRequest(Position position) {
        this.position = position;
    }
}
