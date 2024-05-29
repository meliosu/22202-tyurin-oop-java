package org.nsu.oop.task3.controller.events;

import org.nsu.oop.task3.game.Position;
import org.nsu.oop.task3.game.State;

public class WallPlacementEvent extends GameEvent {
    public final Position position;
    public final State.Wall type;

    public WallPlacementEvent(Position position, State.Wall type) {
        this.position = position;
        this.type = type;
    }
}
