package org.nsu.oop.task1;

import org.nsu.oop.task1.io.ConsoleIOStream;

public class BullsAndCows {
    public static void main(String[] args) {
        InteractiveBullsAndCows bullsAndCowsSession
                = new InteractiveBullsAndCows(new ConsoleIOStream(), new Config(), new VirtualOpponent());
        bullsAndCowsSession.run();
    }
}
