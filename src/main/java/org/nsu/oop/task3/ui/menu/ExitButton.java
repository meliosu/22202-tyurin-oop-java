package org.nsu.oop.task3.ui.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitButton extends Button {
    public ExitButton() {
        super("Quit");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
    }
}
