package org.nsu.oop.task5.ui;

import org.nsu.oop.task5.controller.events.GameEvent;
import org.nsu.oop.task5.controller.events.StartGameEvent;
import org.nsu.oop.task5.pubsub.Publisher;
import org.nsu.oop.task5.pubsub.Subscriber;
import org.nsu.oop.task5.util.Player;
import org.nsu.oop.task5.util.Position;
import org.nsu.oop.task5.ui.game.MainView;
import org.nsu.oop.task5.ui.game.Wall;
import org.nsu.oop.task5.ui.menu.GameMenu;
import org.nsu.oop.task5.ui.menu.GameOver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame implements Subscriber<GameEvent>, Publisher<GameEvent> {
    private static final Dimension viewSize = new Dimension(1000, 720);

    private final MainView mainView;
    private final GameOver gameOver;
    private final GameMenu menu;

    private Subscriber<GameEvent> subscriber;

    public View() {
        setTitle("Quoridor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(viewSize);
        setResizable(false);
        setLayout(new CardLayout());

        mainView = new MainView();
        gameOver = new GameOver();
        menu = new GameMenu();
        menu.addSubscriber(this);

        add(menu);
        add(gameOver);
        add(mainView);
    }

    public void start() {
        setVisible(true);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
        mainView.addSubscriber(subscriber);
        gameOver.addSubscriber(subscriber);
    }

    public void gameOver(Player winningPlayer) {
        mainView.setVisible(false);
        gameOver.setWinningPlayer(winningPlayer);
        gameOver.setVisible(true);
    }

    public void placeWall(Wall wall) {
        mainView.placeWall(wall);
    }

    public void movePlayer(Position position, Player player) {
        mainView.movePlayer(position, player);
    }

    public void highlightMoves(ArrayList<Position> moves) {
        mainView.field.highlightCells(moves);
    }

    public void showMainView() {
        mainView.setVisible(true);
        gameOver.setVisible(false);
        menu.setVisible(false);
    }

    public void showMenu() {
        mainView.setVisible(false);
        gameOver.setVisible(false);
        menu.setVisible(true);
    }

    public void reset() {
        mainView.reset();
    }

    public void updateWallCount(Player player, int count) {
        mainView.updateWallCount(player, count);
    }

    @Override
    public void onEvent(GameEvent event) {
        if (event instanceof StartGameEvent) {
//            menu.setVisible(false);
//            mainView.setVisible(true);
            publishEvent(event);
        }
    }

    @Override
    public void publishEvent(GameEvent event) {
        subscriber.onEvent(event);
    }
}
