package org.nsu.oop.task3.ui.menu;

import org.nsu.oop.task3.controller.events.BackToMenuEvent;
import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.pubsub.Subscriber;
import org.nsu.oop.task3.util.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOver extends JPanel {
    private final JLabel winLabel;

    private Subscriber<GameEvent> subscriber;

    public GameOver() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        setBackground(Color.red);

        Button menuButton = new Button("Back to Menu");
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                subscriber.handleEvent(new BackToMenuEvent());
            }
        });

        ExitButton exitButton = new ExitButton();

        Font font = new Font("Verdana", Font.BOLD, 24);
        winLabel = new JLabel();
        winLabel.setFont(font);
        winLabel.setForeground(Color.white);

        add(winLabel);
        add(menuButton);
        add(exitButton);
    }

    public void setWinningPlayer(Player winningPlayer) {
        winLabel.setText(winningPlayer.toString() + " player has won!");
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }
}
