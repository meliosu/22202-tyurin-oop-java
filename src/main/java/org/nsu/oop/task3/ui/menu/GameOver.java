package org.nsu.oop.task3.ui.menu;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.game.State;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    private final JLabel winLabel;
    private final Button menuButton;

    public GameOver() {
        super();

        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        setBackground(Color.red);

        menuButton = new Button("Back to Menu");
        ExitButton exitButton = new ExitButton();

        Font font = new Font("Verdana", Font.BOLD, 24);
        winLabel = new JLabel();
        winLabel.setFont(font);
        winLabel.setForeground(Color.white);

        add(winLabel);
        add(menuButton);
        add(exitButton);
    }

    public void setWinningPlayer(State.Player winningPlayer) {
        winLabel.setText(winningPlayer.toString() + " player has won!");
    }
}
