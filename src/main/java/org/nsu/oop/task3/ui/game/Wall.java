package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.game.Position;
import org.nsu.oop.task3.game.State;

public class Wall {
    private final Position position;
    private final State.Wall type;

    public Wall(Position position, State.Wall type) {
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public State.Wall getType() {
        return type;
    }
}
