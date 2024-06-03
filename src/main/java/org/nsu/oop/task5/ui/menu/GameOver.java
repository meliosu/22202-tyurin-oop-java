package org.nsu.oop.task5.ui.menu;

import org.nsu.oop.task5.controller.events.BackToMenuEvent;
import org.nsu.oop.task5.controller.events.GameEvent;
import org.nsu.oop.task5.pubsub.Subscriber;
import org.nsu.oop.task5.util.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOver extends JPanel {
    private final JLabel winLabel;

    private Subscriber<GameEvent> subscriber;

    public GameOver() {
        setLayout(new GridLayout(2, 1));
        setBackground(Color.red);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 60));
        wrapper.setBackground(Color.red);

        Button menuButton = new Button("Back to Menu");
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                subscriber.onEvent(new BackToMenuEvent());
            }
        });

        ExitButton exitButton = new ExitButton();

        Font font = new Font("Verdana", Font.BOLD, 32);
        winLabel = new JLabel("", SwingConstants.CENTER);
        winLabel.setFont(font);
        winLabel.setForeground(Color.white);

        add(winLabel);

        wrapper.add(menuButton);
        wrapper.add(exitButton);
        add(wrapper);
    }

    public void setWinningPlayer(Player winningPlayer) {
        winLabel.setText(winningPlayer.toString() + " player has won!");
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }
}
