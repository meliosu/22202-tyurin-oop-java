package org.nsu.oop.task3.ui.game;

import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    private final Color color;
    private Cell cell;

    public Player(Cell cell, Color color) {
        this.cell = cell;
        this.color = color;

        setOpaque(false);
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(color);
        g.fillOval(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
    }
}
