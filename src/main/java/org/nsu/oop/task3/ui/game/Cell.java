package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.events.MoveEvent;
import org.nsu.oop.task3.controller.events.WallPlacementEvent;
import org.nsu.oop.task3.pubsub.Publisher;
import org.nsu.oop.task3.pubsub.Subscriber;
import org.nsu.oop.task3.util.Position;
import org.nsu.oop.task3.util.WallType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Cell extends JPanel implements Publisher<GameEvent> {
    private static final Color normalColor = Color.darkGray;
    private static final Color highlightColor = Color.green;
    private static final int clickThreshold = 10;

    private Subscriber<GameEvent> subscriber;

    public Cell(Position pos) {
        setBorder(new LineBorder(Color.black, 2));
        setBackground(Color.darkGray);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameEvent event;

                if (e.getX() < clickThreshold) {
                    event = new WallPlacementEvent(new Position(pos.x, pos.y - 1), WallType.Horizontal);
                } else if (e.getY() < clickThreshold) {
                    event = new WallPlacementEvent(new Position(pos.x - 1, pos.y), WallType.Vertical);
                } else if (getHeight() - e.getY() < clickThreshold) {
                    event = new WallPlacementEvent(pos, WallType.Vertical);
                } else if (getWidth() - e.getX() < clickThreshold) {
                    event = new WallPlacementEvent(pos, WallType.Horizontal);
                } else {
                    event = new MoveEvent(pos);
                }

                publishEvent(event);
            }
        });
    }

    public void highlight() {
        setBackground(highlightColor);
    }

    public void dehighlight() {
        setBackground(normalColor);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void publishEvent(GameEvent event) {
        subscriber.handleEvent(event);
    }
}
