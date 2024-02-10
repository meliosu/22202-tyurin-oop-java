package org.nsu.oop.task1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class VirtualOpponent implements Opponent {
    private int[] secretDigits;
    private boolean hasLost = false;

    @Override
    public boolean hasLost() {
        return hasLost;
    }

    @Override
    public void establish(Config config) {
        secretDigits = generateSecretNumber(config.getSequenceLen());
    }

    @Override
    public String receiveGuessResponse(int[] guess) {
        int numberCows = countCows(guess, secretDigits);
        int numberBulls = countBulls(guess, secretDigits);

        if (numberBulls == guess.length) {
            hasLost = true;
            return winMessage();
        } else {
            return guessResponseMessage(numberCows, numberBulls);
        }
    }

    public int[] generateSecretNumber(int numberLen) {
        int[] digits = new int[numberLen];
        Set<Integer> digitSet = new HashSet<>();

        Random random = new Random();
        for (int i = 0; i < numberLen; i++) {
            int randomDigit;
            do {
                randomDigit = random.nextInt(10);
            } while (digitSet.contains(randomDigit));

            digitSet.add(randomDigit);
            digits[i] = randomDigit;
        }

        return digits;
    }

    private String guessResponseMessage(int numberCows, int numberBulls) {
        return String.format("cows: %d, bulls: %d", numberCows, numberBulls);
    }

    private String winMessage() {
        return "congrats! you've won.";
    }

    public int countCows(int[] guess, int[] actual) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            for (int j = 0; j < guess.length; j++) {
                if (i == j) {
                    continue;
                }

                if (guess[j] == actual[i]) {
                    count++;
                }
            }
        }

        return count;
    }

    public int countBulls(int[] guess, int[] actual) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == actual[i]) {
                count++;
            }
        }

        return count;
    }
}
