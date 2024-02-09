package org.nsu.oop.task1;

public interface Opponent {
    boolean hasLost();
    void establish(Config config);
    String receiveGuessResponse();
    void sendGuess(int[] guess);
}
