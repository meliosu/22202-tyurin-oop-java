package org.nsu.oop.task5.events.online;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.util.Position;

public class MoveRequest extends GameEvent {
    public final Position position;

    public MoveRequest(Position position) {
        this.position = position;
    }
}
