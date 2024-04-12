package org.nsu.oop.task2.instructions;

public class Add extends BinaryOperation {
    @Override
    protected Double performOperation(Double lhs, Double rhs) throws ArithmeticException {
        return lhs + rhs;
    }
}
