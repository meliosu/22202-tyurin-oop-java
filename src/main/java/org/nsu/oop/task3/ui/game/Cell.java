package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.events.MoveEvent;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.Position;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Cell extends JPanel {
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
                if (subscriber != null) subscriber.handleEvent(new MoveEvent(position));

            }
        });

        if (position.x == 0) {
            setBackground(Color.yellow);
        }

        if (position.y == 0) {
            setBackground(Color.blue);
        }
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
}
