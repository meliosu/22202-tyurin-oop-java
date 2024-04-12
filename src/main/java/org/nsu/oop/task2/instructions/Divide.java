package org.nsu.oop.task2.instructions;

public class Divide extends BinaryOperation {
    @Override
    protected Double performOperation(Double lhs, Double rhs) throws ArithmeticException {
        return lhs / rhs;
    }
}
