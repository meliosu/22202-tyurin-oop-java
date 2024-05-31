package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.util.Player;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JPanel {
    private final JLabel firstPlayerCount;
    private final JLabel secondPlayerCount;

    public GameInfo() {
        setLayout(new GridLayout(2, 1));
        setBackground(Color.gray);

        firstPlayerCount = createWallCountLabel();
        secondPlayerCount = createWallCountLabel();

        add(secondPlayerCount);
        add(firstPlayerCount);
    }

    public void setWallCount(Player player, int count) {
        switch (player) {
            case First: {
                firstPlayerCount.setText(String.valueOf(count));
                break;
            }

            case Second: {
                secondPlayerCount.setText(String.valueOf(count));
                break;
            }
        }
    }

    private JLabel createWallCountLabel() {
        JLabel label = new JLabel("10", SwingConstants.CENTER);
        Font font = new Font("Verdana", Font.BOLD, 80);
        label.setFont(font);
        label.setForeground(Color.orange);
        label.setBorder(BorderFactory.createLineBorder(Color.pink, 6, false));
        return label;
    }
}
