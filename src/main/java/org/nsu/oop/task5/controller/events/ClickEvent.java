package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.util.Position;

import java.awt.event.MouseEvent;

public class ClickEvent extends GameEvent {
    public final Position position;
    public final MouseEvent mouseEvent;

    public ClickEvent(Position position, MouseEvent event) {
        this.position = position;
        this.mouseEvent = event;
    }
}
