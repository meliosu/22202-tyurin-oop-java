package org.nsu.oop.task5.controller.events;

public class MoveEventResponse extends GameEvent {
    public final boolean isLegal;
    public final MoveEventRequest request;

    public MoveEventResponse(MoveEventRequest request, boolean isLegal) {
        this.request = request;
        this.isLegal = isLegal;
    }
}
