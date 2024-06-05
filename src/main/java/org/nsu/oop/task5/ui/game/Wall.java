package org.nsu.oop.task5.ui.game;

import org.nsu.oop.task5.util.Position;
import org.nsu.oop.task5.util.WallType;

public class Wall {
    private final Position position;
    private final WallType type;

    public Wall(Position position, WallType type) {
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public WallType getType() {
        return type;
    }
}
