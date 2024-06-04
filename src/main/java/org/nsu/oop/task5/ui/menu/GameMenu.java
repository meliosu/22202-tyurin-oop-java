package org.nsu.oop.task5.ui.menu;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.events.ui.StartGameEvent;
import org.nsu.oop.task5.pubsub.Publisher;
import org.nsu.oop.task5.pubsub.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMenu extends JPanel implements Publisher<GameEvent> {
    private Subscriber<GameEvent> subscriber;

    public GameMenu() {
        setBackground(Color.red);
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 240));

        Button multi = new Button("Multiplayer");
        multi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                publishEvent(new StartGameEvent());
            }
        });

        Button exit = new ExitButton();

        add(multi);
        add(exit);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void publishEvent(GameEvent event) {
        subscriber.onEvent(event);
    }
}
