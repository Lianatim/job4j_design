package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResultFile {
    public static void main(String[] args) {
        multiple(10);
    }
    public static int[][] multiple(int size) {
        int[][] arr = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    arr[i][j] = (i + 1) * (j + 1);
                    String rsl = (i + 1) + " * " + (j + 1) + " = " + arr[i][j];
                    out.write(rsl.getBytes(StandardCharsets.UTF_8));
                    out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
}