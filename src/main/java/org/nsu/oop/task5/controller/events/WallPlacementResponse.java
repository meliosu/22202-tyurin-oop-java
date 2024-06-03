package org.nsu.oop.task5.controller.events;

import org.nsu.oop.task5.pubsub.Event;

public class WallPlacementResponse extends GameEvent {
    public final boolean isLegal;
    public final int wallCount;
    public final WallPlacementEventRequest request;

    public WallPlacementResponse(WallPlacementEventRequest request, int wallCount, boolean isLegal) {
        this.wallCount = wallCount;
        this.request = request;
        this.isLegal = isLegal;
    }
}
