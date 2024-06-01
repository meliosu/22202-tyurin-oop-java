package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.pubsub.Subscriber;
import org.nsu.oop.task3.util.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel {
    private static final Dimension gridDimension = new Dimension(720, 720);

    private final Cell[][] cells;

    public Grid(int size) {
        setMaximumSize(gridDimension);
        setMinimumSize(gridDimension);
        setPreferredSize(gridDimension);

        setLayout(new GridLayout(size, size));

        cells = new Cell[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell cell = new Cell(new Position(x, y));
                cells[x][y] = cell;
                add(cell);
            }
        }
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        for (Cell[] cell : cells) {
            for (int y = 0; y < cells.length; y++) {
                cell[y].addSubscriber(subscriber);
            }
        }
    }

    private void clearHighlights() {
        for (Cell[] cell : cells) {
            for (int y = 0; y < cells.length; y++) {
                cell[y].dehighlight();
            }
        }
    }

    public void highlightCells(ArrayList<Position> positions) {
        clearHighlights();
        for (Position pos : positions) {
            cells[pos.x][pos.y].highlight();
        }
    }

    public void drawCircle(Position position, Color color, Graphics g) {
        Cell cell = cells[position.x][position.y];
        g.setColor(color);
        g.fillOval(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
    }
}
