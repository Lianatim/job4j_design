package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder txt = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                txt.append((char) read);
            }
            String[] lines = txt.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + " number is even");
                } else {
                    System.out.println(line + " number is odd");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}