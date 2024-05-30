package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.game.Position;

import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    private final Color color;
    private Cell cell;

    public Player(Cell cell, Color color) {
        super();

        setOpaque(false);

        this.cell = cell;
        this.color = color;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setPaintMode();
        g.setColor(color);
        g.fillOval(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
    }
}
