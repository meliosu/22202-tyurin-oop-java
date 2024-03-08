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
    public GuessResponse receiveGuessResponse(int[] guess) {
        int numberCows = countCows(guess);
        int numberBulls = countBulls(guess);

        if (numberBulls == guess.length) {
            hasLost = true;
        }

        return new GuessResponse(numberCows, numberBulls);
    }

    public static VirtualOpponent withSecretSequence(int[] sequence) {
        VirtualOpponent opponent = new VirtualOpponent();
        opponent.secretDigits = sequence;
        return opponent;
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
