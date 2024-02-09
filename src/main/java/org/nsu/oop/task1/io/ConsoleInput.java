package org.nsu.oop.task1.io;

import java.util.Scanner;

public class ConsoleInput implements Input {
    Scanner scanner;

    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }


    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
