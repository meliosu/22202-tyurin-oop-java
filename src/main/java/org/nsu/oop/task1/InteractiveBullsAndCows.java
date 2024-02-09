package org.nsu.oop.task1;

import org.nsu.oop.task1.io.IOStream;

public class InteractiveBullsAndCows {
    private final IOStream ioStream;
    private final Config gameConfig;
    private final Opponent gameOpponent;

    public InteractiveBullsAndCows(IOStream stream, Config config, Opponent opponent) {
        ioStream = stream;
        gameConfig = config;
        gameOpponent = opponent;
    }

    public void run() {
        gameOpponent.establish(gameConfig);
        while (!gameOpponent.hasLost()) {
            int[] guess;
            try {
                guess = getGuess();
            } catch (RuntimeException exception) {
                ioStream.send(invalidGuessMessage());
                continue;
            }

            gameOpponent.sendGuess(guess);

            String response = gameOpponent.receiveGuessResponse();
            ioStream.send(response);
        }
    }

    private int[] getGuess() throws RuntimeException {
        String userInput = ioStream.receive();
        char[] guessChars = userInput.toCharArray();
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
}
