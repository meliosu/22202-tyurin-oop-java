package org.nsu.oop.task1.io;

public class ConsoleIOStream extends IOStream {
    public ConsoleIOStream() {
        super(new ConsoleInput(), new ConsoleOutput());
    }
}
