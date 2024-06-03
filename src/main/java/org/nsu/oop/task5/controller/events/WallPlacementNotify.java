package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.util.Player;
import org.nsu.oop.task5.util.Position;
import org.nsu.oop.task5.util.WallType;

public class WallPlacementNotify extends GameEvent {
    public final Position wallPosition;
    public final WallType wallType;
    public final int wallCount;
    public final Player player;

    public WallPlacementNotify(Position wallPosition, WallType wallType, int wallCount, Player player) {
        this.wallPosition = wallPosition;
        this.wallType = wallType;
        this.wallCount = wallCount;
        this.player = player;
    }
}
