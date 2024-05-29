package org.nsu.oop.task3.ui;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.State;
import org.nsu.oop.task3.ui.game.MainView;
import org.nsu.oop.task3.ui.menu.GameMenu;
import org.nsu.oop.task3.ui.menu.GameOver;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private final MainView mainView;
    private final GameOver gameOver;
    private final GameMenu menu;

    public View() {
        super();
        setTitle("Quoridor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 720));
        setResizable(false);

        mainView = new MainView();
        gameOver = new GameOver();
        menu = new GameMenu();

        setLayout(new CardLayout());

//        add(menu);
//        add(gameOver);
        add(mainView);

        setVisible(true);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        mainView.addSubscriber(subscriber);

        // maybe need to add others
    }

    public void gameOver(State.Player winningPlayer) {
        mainView.setVisible(false);
        gameOver.setWinningPlayer(winningPlayer);
        gameOver.setVisible(true);
    }
}
