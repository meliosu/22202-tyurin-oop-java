package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.ClickEvent;
import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.events.MoveEvent;
import org.nsu.oop.task3.controller.events.WallPlacementEvent;
import org.nsu.oop.task3.pubsub.Publisher;
import org.nsu.oop.task3.pubsub.Subscriber;
import org.nsu.oop.task3.util.Position;
import org.nsu.oop.task3.util.WallType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PlayingField extends JPanel implements Subscriber<GameEvent>, Publisher<GameEvent> {
    private final Grid grid;

    private Position firstPlayerPos;
    private Position secondPlayerPos;
    private ArrayList<Wall> walls = new ArrayList<>();
    private Subscriber<GameEvent> subscriber;

    public PlayingField() {
        setLayout(new OverlayLayout(this));

        grid = new Grid(9);
        grid.addSubscriber(this);
        add(grid);

        resetPlayers();
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

    public void resetWalls() {
        walls = new ArrayList<>();
    }

    public void resetPlayers() {
        firstPlayerPos = new Position(4, 0);
        secondPlayerPos = new Position(4, 8);
    }

    public void movePlayer(Position position, org.nsu.oop.task3.util.Player player) {
        switch (player) {
            case First: {
                firstPlayerPos = position;
                break;
            }

            case Second: {
                secondPlayerPos = position;
                break;
            }
        }

        updateUI();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Wall wall : walls) {
            drawWall(wall, g);
        }

        grid.drawCircle(firstPlayerPos, Color.red, g);
        grid.drawCircle(secondPlayerPos, Color.blue, g);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }

    public void highlightCells(ArrayList<Position> cells) {
        grid.highlightCells(cells);
    }

    @Override
    public void handleEvent(GameEvent e) {
        if (e instanceof ClickEvent) {
            ClickEvent event = (ClickEvent) e;
            MouseEvent mouseEvent = event.mouseEvent;
            Position pos = event.position;
            int clickThreshold = 10;

            GameEvent passedEvent;

            if (mouseEvent.getX() < clickThreshold) {
                passedEvent = new WallPlacementEvent(new Position(pos.x, pos.y - 1), WallType.Horizontal);
            } else if (mouseEvent.getY() < clickThreshold) {
                passedEvent = new WallPlacementEvent(new Position(pos.x - 1, pos.y), WallType.Vertical);
            } else {
                passedEvent = new MoveEvent(pos);
            }

            publishEvent(passedEvent);
        }
    }

    @Override
    public void publishEvent(GameEvent event) {
        subscriber.handleEvent(event);
    }
}
