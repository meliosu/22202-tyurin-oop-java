package org.nsu.oop.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestVirtualOpponent {
    VirtualOpponent opponent;

    int[] defaultGuess() {
        return new int[]{1, 2, 3, 4};
    }

    @BeforeEach
    void setUpOpponent() {
        opponent = new VirtualOpponent();
    }

    @Test
    @DisplayName("No Cows")
    void noCows() {
        int[] guess = defaultGuess();
        int[] secret = {5, 6, 7, 8};
        assertEquals(0, opponent.countCows(guess, secret));

        guess = new int[]{2, 4, 6, 8};
        secret = new int[]{1, 3, 5, 7};

        assertEquals(0, opponent.countCows(guess, secret));
    }

    @Test
    @DisplayName("All Cows")
    void allCows() {
        int[] guess = defaultGuess();
        int[] secret = {4, 3, 2, 1};

        assertEquals(4, opponent.countCows(guess, secret));
    }

    @Test
    @DisplayName("All Bulls")
    void allBulls() {
        int[] guess = defaultGuess();
        int[] secret = defaultGuess();

        assertEquals(4, opponent.countBulls(guess, secret));
    }

    @Test
    @DisplayName("Mixed Cows and Bulls")
    void mixedCowsBulls() {
        int[] guess = defaultGuess();
        int[] secret = {1, 4, 3, 2};

        assertEquals(2, opponent.countCows(guess, secret));
        assertEquals(2, opponent.countBulls(guess, secret));
    }

    @Test
    @DisplayName("Secret Number Generation")
    void numberGeneration() {
        int[] secret = opponent.generateSecretNumber(4);

        assertEquals(4, secret.length);

        int count = 0;
        for (int i = 0; i < secret.length; i++) {
            for (int j = 0; j < secret.length; j++) {
                if (i != j && secret[i] == secret[j]) {
                    count++;
                }
            }
        }

        assertEquals(0, count);
    }
}
