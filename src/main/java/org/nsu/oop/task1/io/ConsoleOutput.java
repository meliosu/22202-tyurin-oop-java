package org.nsu.oop.task1.io;

public class ConsoleOutput implements Output {
    @Override
    public void sendOutput(String message) {
        System.out.println(message);
    }
}
