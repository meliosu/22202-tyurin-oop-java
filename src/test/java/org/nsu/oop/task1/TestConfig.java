package org.nsu.oop.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestConfig {
    @Test
    @DisplayName("Default Config Number Length")
    void defaultConfig() {
        Config config = new Config();
        assertEquals(config.getSequenceLen(), 4);
    }
}
