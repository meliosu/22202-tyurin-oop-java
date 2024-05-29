package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.State;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JPanel {
    private final JLabel firstPlayerCount;
    private final JLabel secondPlayerCount;
    private final WallIcon verticalWall;
    private final WallIcon horizontalWall;

    private Subscriber<GameEvent> subscriber;

    public GameInfo() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.gray);

        firstPlayerCount = createWallCountLabel();
        secondPlayerCount = createWallCountLabel();

        verticalWall = new WallIcon(State.Wall.Vertical);
        horizontalWall = new WallIcon(State.Wall.Horizontal);
        horizontalWall.select();

        add(secondPlayerCount);
        add(horizontalWall);
        add(verticalWall);
        add(firstPlayerCount);
    }

    private void selectWall(State.Wall wall) {
        switch (wall) {
            case Horizontal: {
                if (!horizontalWall.isSelected()) {
                    verticalWall.deselect();
                    horizontalWall.select();
                }

                break;
            }

            case Vertical: {
                if (!verticalWall.isSelected()) {
                    horizontalWall.deselect();
                    verticalWall.select();
                }

                break;
            }
        }
    }

    private void setWallCount(State.Player player, int count) {
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
        JLabel label = new JLabel("10");
        Font font = new Font("Verdana", Font.BOLD, 40);
        label.setFont(font);
        label.setForeground(Color.orange);
        label.setAlignmentX(CENTER_ALIGNMENT);

        return label;
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
    }
}
