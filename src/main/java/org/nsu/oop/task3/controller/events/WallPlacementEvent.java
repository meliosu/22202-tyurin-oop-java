package org.nsu.oop.task3.controller.events;

import org.nsu.oop.task3.util.Position;
import org.nsu.oop.task3.util.WallType;

public class WallPlacementEvent extends GameEvent {
    public final Position wallPosition;
    public final WallType wallType;

    public WallPlacementEvent(Position wallPosition, WallType wallType) {
        this.wallPosition = wallPosition;
        this.wallType = wallType;
    }
}
