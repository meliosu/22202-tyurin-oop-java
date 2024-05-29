package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.game.Position;

import java.awt.*;

public class Player {
    private final Color color;
    private Position position;

    public Player(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}
