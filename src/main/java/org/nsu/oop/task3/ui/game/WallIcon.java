package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.pubsub.Publisher;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.State;

import javax.swing.*;
import java.awt.*;

public class WallIcon extends JPanel implements Publisher<GameEvent> {
    private boolean isSelected = false;
    private final State.Wall type;

    private Subscriber<GameEvent> subscriber;

    public WallIcon(State.Wall type) {
        this.type = type;
    }

    public void select() {
        isSelected = true;
    }

    public void deselect() {
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int width = type == State.Wall.Horizontal ? 100 : 10;
        int height = type == State.Wall.Horizontal ? 10 : 100;

        if (isSelected) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.red);
        }

        g.fillRect(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void publishEvent(GameEvent event) {
        if (subscriber != null) subscriber.handleEvent(event);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }
}
