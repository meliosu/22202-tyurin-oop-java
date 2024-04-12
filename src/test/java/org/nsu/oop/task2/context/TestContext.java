package org.nsu.oop.task2.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.BadContextException;

public class TestContext {
    Context context;

    @BeforeEach
    void setup() {
        context = new Context();
    }

    @Test
    void push() {
        context.push(10.0);

        Assertions.assertEquals(10.0, context.pop());
    }

    @Test
    void insertAndLookupVariable() {
        context.insertVariable("my_variable", 10.0);

        Assertions.assertEquals(10.0, context.getVariable("my_variable"));
    }
}
