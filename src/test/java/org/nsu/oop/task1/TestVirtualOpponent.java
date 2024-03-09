package org.nsu.oop.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestVirtualOpponent {
    VirtualOpponent opponent;
    int[] guess;

    int[] defaultSecret() {
        return new int[]{1, 2, 3, 4};
    }

    @BeforeEach
    void setUpOpponent() {
        opponent = VirtualOpponent.withSecretSequence(defaultSecret());
    }

    @Test
    @DisplayName("No Cows")
    void noCows() {
        guess = new int[]{5, 6, 7, 8};
        assertEquals(0, opponent.receiveGuessResponse(guess).getNumberCows());
    }

    @Test
    @DisplayName("All Cows")
    void allCows() {
        guess = new int[]{4, 3, 2, 1};

        assertEquals(4, opponent.receiveGuessResponse(guess).getNumberCows());
    }

    @Test
    @DisplayName("All Bulls")
    void allBulls() {
        guess = defaultSecret();

        assertEquals(4, opponent.receiveGuessResponse(guess).getNumberBulls());
    }

    @Test
    @DisplayName("Mixed Cows and Bulls")
    void mixedCowsBulls() {
        guess = new int[]{1, 2, 4, 3};

        assertEquals(2, opponent.receiveGuessResponse(guess).getNumberCows());
        assertEquals(2, opponent.receiveGuessResponse(guess).getNumberBulls());
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
