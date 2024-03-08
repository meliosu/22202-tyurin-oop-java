package org.nsu.oop.task1;

public class GuessResponse {
    private final int numberCows;
    private final int numberBulls;

    public GuessResponse(int _numberCows, int _numberBulls) {
        numberCows = _numberCows;
        numberBulls = _numberBulls;
    }

    public int getNumberCows() {
        return numberCows;
    }

    public int getNumberBulls() {
        return numberBulls;
    }
}
