package org.nsu.oop.task2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private final Map<String, Double> variables;
    private final Stack<Double> stack;

    public Context() {
        this.variables = new HashMap<>();
        this.stack = new Stack<>();
    }

    public Double pop() {
        return stack.pop();
    }

    public void push(Double value) {
        stack.push(value);
    }

    public Double peek() {
        return stack.peek();
    }

    public void insertVariable(String variable, Double value) {
        variables.put(variable, value);
    }

    public Double getVariable(String variable) {
        return variables.get(variable);
    }

    public boolean hasVariable(String variable) {
        return variables.containsKey(variable);
    }
}
