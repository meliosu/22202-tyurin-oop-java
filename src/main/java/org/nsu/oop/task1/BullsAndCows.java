package org.nsu.oop.task1;

import org.nsu.oop.task1.io.IOStream;

import java.util.HashSet;
import java.util.Set;

public class BullsAndCows {
    private final IOStream ioStream;
    private final Config gameConfig;
    private final Opponent gameOpponent;

    public BullsAndCows(IOStream stream, Config config, Opponent opponent) {
        ioStream = stream;
        gameConfig = config;
        gameOpponent = opponent;
    }

    public void run() {
        gameOpponent.establish(gameConfig);
        ioStream.send(greetingMessage());
        while (!gameOpponent.hasLost()) {
            int[] guess;
            try {
                guess = getGuess();
            } catch (RuntimeException exception) {
                ioStream.send(invalidGuessMessage());
                continue;
            }

            GuessResponse response = gameOpponent.receiveGuessResponse(guess);
            ioStream.send(responseMessage(response));
        }
    }

    private int[] getGuess() throws RuntimeException {
        String userInput = ioStream.receive();
        char[] guessChars = userInput.toCharArray();

        Set<Character> chars = new HashSet<>();
        for (Character c : guessChars) {
            if (!chars.add(c)) {
                throw new RuntimeException();
            }
        }

        int secretNumberLen = gameConfig.getSequenceLen();
        if (guessChars.length != secretNumberLen) {
            throw new RuntimeException();
        }

        int[] currentGuess = new int[secretNumberLen];
        for (int i = 0; i < secretNumberLen; i++) {
            int d = Character.getNumericValue(guessChars[i]);
            currentGuess[i] = d;
        }

        return currentGuess;
    }

    private String invalidGuessMessage() {
        return "invalid guess format, please try again";
    }

    private String greetingMessage() {
        return "welcome to a game of bulls and cows! please enter your guess";
    }

    private String responseMessage(GuessResponse response) {
        return String.format("bulls: %d, cows: %d", response.getNumberBulls(), response.getNumberCows());
    }
}
