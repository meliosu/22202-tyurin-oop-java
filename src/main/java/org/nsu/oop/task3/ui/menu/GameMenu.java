package org.nsu.oop.task3.ui.menu;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JPanel {
    private final JButton multi;
    private final JButton single;
    private final JButton exit;

    public GameMenu() {
        super();
        setBackground(Color.red);

        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));

        multi = new Button("Multiplayer");
        single = new Button("Singleplayer");
        exit = new Button("Quit");

        add(multi);
        add(single);
        add(exit);
    }
}
