package org.nsu.oop.task3.game;

public class IllegalWallException extends RuntimeException {
    public IllegalWallException(String message) {
        super(message);
    }

    public IllegalWallException() {
        this("");
    }
}
