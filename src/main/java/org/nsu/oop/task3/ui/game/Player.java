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
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        System.out.println(getHeight());
//
//        int x = position.x * (getWidth() / 9);
//        int y = position.y * (getHeight() / 9);
//
//        g.setColor(color);
//        g.fillOval(x, y, getWidth() / 9, getHeight() / 9);

        super.paintComponent(g);

        g.setColor(color);
        g.fillOval(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
    }
}
