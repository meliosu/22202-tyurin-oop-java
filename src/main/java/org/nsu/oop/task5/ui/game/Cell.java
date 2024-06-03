package org.nsu.oop.task5.ui.game;

import org.nsu.oop.task5.controller.events.ClickEvent;
import org.nsu.oop.task5.controller.events.GameEvent;
import org.nsu.oop.task5.pubsub.Publisher;
import org.nsu.oop.task5.pubsub.Subscriber;
import org.nsu.oop.task5.util.Position;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell extends JPanel implements Publisher<GameEvent> {
    private static final Color normalColor = Color.darkGray;
    private static final Color highlightColor = Color.green;

    private Subscriber<GameEvent> subscriber;

    public Cell(Position position) {
        setBorder(new LineBorder(Color.black, 2));
        setBackground(Color.darkGray);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                publishEvent(new ClickEvent(position, e));
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
