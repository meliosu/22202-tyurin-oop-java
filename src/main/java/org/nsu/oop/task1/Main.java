package org.nsu.oop.task1;

import org.nsu.oop.task1.io.ConsoleIOStream;

public class Main {
    public static void main(String[] args) {
        BullsAndCows bullsAndCowsSession
                = new BullsAndCows(new ConsoleIOStream(), new Config(), new VirtualOpponent());
        bullsAndCowsSession.run();
    }
}
