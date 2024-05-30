package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.Position;
import org.nsu.oop.task3.game.State;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cells extends JPanel {
    private final Cell[][] cells;
    private final ArrayList<Wall> walls = new ArrayList<>();
    private final Player firstPlayer;
    private final Player secondPlayer;

    public Cells() {
        super();

        OverlayLayout layout = new OverlayLayout(this);
        setLayout(layout);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridLayout(9, 9));

        // need to remove
        setMaximumSize(new Dimension(720, 720));
        setMinimumSize(new Dimension(720, 720));
        setPreferredSize(new Dimension(720, 720));

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
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells.length; y++) {
                cells[x][y].addSubscriber(subscriber);
            }
        }
    }

    private void clearHighlights() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells.length; y++) {
                cells[x][y].restore();
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
            System.out.println("drawing wall!");

            drawWall(wall, g);
        }

        firstPlayer.paint(g);
        secondPlayer.paint(g);
    }

    private void drawWall(Wall wall, Graphics g) {
        int x = (getWidth() / 9) * (wall.getPosition().y + 1);
        int y = (getHeight() / 9) * (wall.getPosition().x + 1);
        int dim1 = 10;
        int dim2 = 2 * getWidth() / 9;

        g.setPaintMode();
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

    public void movePlayer(Position position, State.Player player) {
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
}
