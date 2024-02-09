package org.nsu.oop.task1.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements Input {
    BufferedReader reader;

    public ConsoleInput() {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        reader = new BufferedReader(streamReader);
    }


    @Override
    public String getInput() {
        try {
            return reader.readLine();
        } catch (IOException exception) {
            System.out.println("looooooooool");
        }

        return "";
    }
}
