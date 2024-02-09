package org.nsu.oop.task1;

import org.nsu.oop.task1.io.IOStream;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class InteractiveBullsAndCows {
    private IOStream ioStream;
    private Config gameConfig;
    private int[] secretDigits;

    public InteractiveBullsAndCows(IOStream stream, Config config) {
        ioStream = stream;
        gameConfig = config;
    }

    public void run() {
        secretDigits = generateSecretNumber();
        while (true) {
            int[] guess;
            try {
                guess = getGuess();
            } catch (RuntimeException exception) {
                System.out.println(invalidGuessMessage());
                continue;
            }

            int numberCows = countCows(guess);
            int numberBulls = countBulls(guess);

            if (numberBulls == gameConfig.getSequenceLen()) {
                System.out.println(winMessage());
                break;
            } else {
                System.out.println(guessResponseMessage(numberCows, numberBulls));
            }
        }
    }

    private int[] getGuess() {
        String userInput = ioStream.receive();

    }

    private String invalidGuessMessage() {
        return "invalid guess format, please try again";
    }

    private String guessResponseMessage(int numberCows, int numberBulls) {
        String message = "";
        if (numberCows != 0) {
            message += numberCows + " cows";
        }

        if (numberBulls != 0) {
            message += ", " + numberBulls + "bulls";
        }

        return message;
    }

    private String winMessage() {
        return "congrats! you've won.";
    }

    private int[] generateSecretNumber() {
        int numberLen = gameConfig.getSequenceLen();
        int[] digitArray = new int[gameConfig.getSequenceLen()];
        Set<Integer> digitSet = new HashSet<Integer>();

        Random random = new Random();
        for (int i = 0; i < numberLen; i++) {
            int randomDigit;
            do {
                randomDigit = random.nextInt(10);
            } while (digitSet.contains(randomDigit));

            digitSet.add(randomDigit);
            digitArray[i] = randomDigit;
        }

        return digitArray;
    }

    private int countCows(int[] guess) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            for (int j = 0; j < guess.length; j++) {
                if (i == j) {
                    continue;
                }

                if (guess[j] == secretDigits[i]) {
                    count++;
                }
            }
        }

        return count;
    }

    private int countBulls(int[] guess) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == secretDigits[i]) {
                count++;
            }
        }

        return count;
    }
}
