package org.nsu.oop.task5.events.online;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.util.Position;
import org.nsu.oop.task5.util.WallType;

public class WallPlacementRequest extends GameEvent {
    public final Position wallPosition;
    public final WallType wallType;

    public WallPlacementRequest(Position wallPosition, WallType wallType) {
        this.wallPosition = wallPosition;
        this.wallType = wallType;
    }
}
