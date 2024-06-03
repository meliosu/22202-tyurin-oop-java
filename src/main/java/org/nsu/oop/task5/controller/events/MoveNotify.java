package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.util.Player;
import org.nsu.oop.task5.util.Position;

public class MoveNotify extends GameEvent {
    public final Position position;
    public final Player player;

    public MoveNotify(Position position, Player player) {
        this.position = position;
        this.player = player;
    }
}
