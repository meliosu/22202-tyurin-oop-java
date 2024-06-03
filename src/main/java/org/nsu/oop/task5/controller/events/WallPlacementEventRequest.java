package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.util.Position;
import org.nsu.oop.task5.util.WallType;

public class WallPlacementEventRequest extends GameEvent {
    public final Position wallPosition;
    public final WallType wallType;

    public WallPlacementEventRequest(Position wallPosition, WallType wallType) {
        this.wallPosition = wallPosition;
        this.wallType = wallType;
    }
}
