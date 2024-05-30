package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.events.MoveEvent;
import org.nsu.oop.task3.controller.events.WallPlacementEvent;
import org.nsu.oop.task3.controller.pubsub.Publisher;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.Position;
import org.nsu.oop.task3.game.State;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Cell extends JPanel implements Publisher<GameEvent> {
    private static final Color normalColor = Color.darkGray;
    private static final Color highlightColor = Color.green;

    private Subscriber<GameEvent> subscriber;

    private final Position position;

    public Cell(Position position) {
        super();
        this.position = position;

        setBorder(new LineBorder(Color.black, 2));
        setBackground(Color.darkGray);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() < 15) {
                    publishEvent(new WallPlacementEvent(position, State.Wall.Horizontal));
                } else if (e.getY() < 15) {
                    publishEvent(new WallPlacementEvent(position, State.Wall.Vertical));
                } else {
                    publishEvent(new MoveEvent(position));
                }
            }
        });
    }

    public void highlight() {
        setBackground(highlightColor);
    }

    public void restore() {
        setBackground(normalColor);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void publishEvent(GameEvent event) {
        subscriber.handleEvent(event);
    }
}
