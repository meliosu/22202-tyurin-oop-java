package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.pubsub.Subscriber;
import org.nsu.oop.task3.util.Player;
import org.nsu.oop.task3.util.Position;

import javax.swing.*;

public class MainView extends JPanel {
    public final Grid grid;
    public final GameInfo info;

    public MainView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        grid = new Grid();
        info = new GameInfo();

        add(grid);
        add(info);
    }

    public void placeWall(Wall wall) {
        grid.addWall(wall);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        grid.addSubscriber(subscriber);
    }

    public void movePlayer(Position position, Player player) {
        grid.movePlayer(position, player);
    }

    public void updateWallCount(Player player, int count) {
        info.setWallCount(player, count);
    }

    public void reset() {
        grid.resetWalls();
        grid.resetPlayers();
        info.setWallCount(Player.First, 10);
        info.setWallCount(Player.Second, 10);
    }
}
