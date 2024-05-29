package org.nsu.oop.task3.ui.menu;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.events.StartGameEvent;
import org.nsu.oop.task3.controller.pubsub.Publisher;
import org.nsu.oop.task3.controller.pubsub.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMenu extends JPanel implements Publisher<GameEvent> {
    private final JButton multi;
    private final JButton exit;

    private Subscriber<GameEvent> subscriber;

    public GameMenu() {
        super();
        setBackground(Color.red);

        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));

        multi = new Button("Multiplayer");

        multi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                publishEvent(new StartGameEvent());
            }
        });

        exit = new ExitButton();

        add(multi);
        add(exit);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void publishEvent(GameEvent event) {
        subscriber.handleEvent(event);
    }
}
