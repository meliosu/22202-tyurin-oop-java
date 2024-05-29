package org.nsu.oop.task3.ui;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.events.StartGameEvent;
import org.nsu.oop.task3.controller.pubsub.Publisher;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.Position;
import org.nsu.oop.task3.game.State;
import org.nsu.oop.task3.ui.game.MainView;
import org.nsu.oop.task3.ui.game.Wall;
import org.nsu.oop.task3.ui.menu.GameMenu;
import org.nsu.oop.task3.ui.menu.GameOver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame implements Subscriber<GameEvent>, Publisher<GameEvent> {
    private final MainView mainView;
    private final GameOver gameOver;
    private final GameMenu menu;

    private Subscriber<GameEvent> subscriber;

    public View() {
        super();
        setTitle("Quoridor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 720));
        setResizable(false);

        mainView = new MainView();
        gameOver = new GameOver();
        menu = new GameMenu();

        menu.addSubscriber(this);

        setLayout(new CardLayout());

        add(menu);
        add(gameOver);
        add(mainView);

        setVisible(true);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        this.subscriber = subscriber;
        mainView.addSubscriber(subscriber);
        gameOver.addSubscriber(subscriber);


        // maybe need to add others
    }

    public void gameOver(State.Player winningPlayer) {
        mainView.setVisible(false);
        gameOver.setWinningPlayer(winningPlayer);
        gameOver.setVisible(true);
    }

    public void placeWall(Wall wall) {
        mainView.placeWall(wall);
    }

    public void movePlayer(Position position, State.Player player) {
        mainView.movePlayer(position, player);
    }

    public void highlightMoves(ArrayList<Position> moves) {
        mainView.cells.highlightCells(moves);
    }


    @Override
    public void handleEvent(GameEvent event) {
        if (event instanceof StartGameEvent) {
            menu.setVisible(false);
            mainView.setVisible(true);
            publishEvent(event);
        }
    }

    @Override
    public void publishEvent(GameEvent event) {
        subscriber.handleEvent(event);
    }
}
