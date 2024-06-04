package org.nsu.oop.task5.ui.game;

import org.nsu.oop.task5.events.GameEvent;
import org.nsu.oop.task5.pubsub.Subscriber;
import org.nsu.oop.task5.util.Player;
import org.nsu.oop.task5.util.Position;

import javax.swing.*;

public class MainView extends JPanel {
    public final GameInfo info;
    public final PlayingField field;

    public MainView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        field = new PlayingField();
        info = new GameInfo();

        add(field);
        add(info);
    }

    public void placeWall(Wall wall) {
        field.addWall(wall);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        field.addSubscriber(subscriber);
    }

    public void movePlayer(Position position, Player player) {
        field.movePlayer(position, player);
    }

    public void updateWallCount(Player player, int count) {
        info.setWallCount(player, count);
    }

    public void reset() {
        field.resetWalls();
        field.resetPlayers();
        info.setWallCount(Player.First, 10);
        info.setWallCount(Player.Second, 10);
    }
}
