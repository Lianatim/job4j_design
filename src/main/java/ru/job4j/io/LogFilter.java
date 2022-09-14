package ru.job4j.io;

import java.io.*;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> filteredLines = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            filteredLines = in.lines()
                    .filter(s -> s.contains(" 404 "))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredLines;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
        save(log, "404.txt");
    }
}