package org.nsu.oop.task3.controller.events;

import org.nsu.oop.task3.util.Position;

import java.awt.event.MouseEvent;

public class ClickEvent extends GameEvent {
    public final Position position;
    public final MouseEvent mouseEvent;

    public ClickEvent(Position position, MouseEvent event) {
        this.position = position;
        this.mouseEvent = event;
    }
}
