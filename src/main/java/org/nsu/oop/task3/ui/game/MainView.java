package org.nsu.oop.task3.ui.game;

import org.nsu.oop.task3.controller.events.GameEvent;
import org.nsu.oop.task3.controller.pubsub.Subscriber;
import org.nsu.oop.task3.game.Position;
import org.nsu.oop.task3.game.State;

import javax.swing.*;

public class MainView extends JPanel {
    public static final int height = 720;
    public static final int width = 720;

    private final Cells cells;
    private final GameInfo info;

    public MainView() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        cells = new Cells();
        info = new GameInfo();

        add(cells);
        add(info);
    }

    public void placeWall(Wall wall) {
        cells.addWall(wall);
    }

    public void addSubscriber(Subscriber<GameEvent> subscriber) {
        cells.addSubscriber(subscriber);
        info.addSubscriber(subscriber);
    }
}
