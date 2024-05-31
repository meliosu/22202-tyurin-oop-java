package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.pubsub.Subscriber;
import org.nsu.oop.task3.util.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel {
    private static final Dimension gridSize = new Dimension(720, 720);

    private final Cell[][] cells;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private ArrayList<Wall> walls = new ArrayList<>();

    public Grid() {
        setMaximumSize(gridSize);
        setMinimumSize(gridSize);
        setPreferredSize(gridSize);

        setLayout(new OverlayLayout(this));

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridLayout(9, 9));

        cells = new Cell[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Cell cell = new Cell(new Position(x, y));
                cells[x][y] = cell;
                wrapper.add(cell);
            }
        }

        firstPlayer = new Player(cells[4][0], Color.red);
        secondPlayer = new Player(cells[4][8], Color.blue);

        add(secondPlayer);
        add(firstPlayer);
        add(wrapper);
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Wall wall : walls) {
            drawWall(wall, g);
        }
    }

    private void drawWall(Wall wall, Graphics g) {
        int x = (getWidth() / 9) * (wall.getPosition().y + 1);
        int y = (getHeight() / 9) * (wall.getPosition().x + 1);
        int dim1 = 10;
        int dim2 = 2 * getWidth() / 9;

        g.setColor(Color.yellow);

        switch (wall.getType()) {
            case Horizontal: {
                g.fillRect(x - dim1 / 2, y - dim2 / 2, dim1, dim2);
                break;
            }

            case Vertical: {
                g.fillRect(x - dim2 / 2, y - dim1 / 2, dim2, dim1);
                break;
            }
        }
    }

    public void addWall(Wall wall) {
        walls.add(wall);
        updateUI();
    }

    public void movePlayer(Position position, org.nsu.oop.task3.util.Player player) {
        switch (player) {
            case First: {
                firstPlayer.setCell(cells[position.x][position.y]);
                break;
            }

            case Second: {
                secondPlayer.setCell(cells[position.x][position.y]);
                break;
            }
        }

        updateUI();
    }

    public void resetWalls() {
        walls = new ArrayList<>();
    }

    public void resetPlayers() {
        firstPlayer.setCell(cells[4][0]);
        secondPlayer.setCell(cells[4][8]);
    }
}
