package org.nsu.oop.task1.io;

public class IOStream {
    Input input;
    Output output;

    public IOStream(Input in, Output out) {
        input = in;
        output = out;
    }

    public String receive() {
        return input.getInput();
    }
    public void send(String message) {
        output.sendOutput(message);
    }
}
