package org.nsu.oop.task3.ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {
    private static final Color normalColor = Color.darkGray;
    private static final Color hoverColor = Color.lightGray;

    public Button(String text) {
        super(text);

        setFont(new Font("Verdana", Font.PLAIN, 24));
        setBackground(normalColor);
        setForeground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.black, 4, true));
        setFocusPainted(false);
        setPreferredSize(new Dimension(200, 200));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalColor);
            }
        });
    }
}
