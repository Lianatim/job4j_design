package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<String> rsl = new ArrayList<>();
        List<List<String>> tmp = readCSV(argsName);
        StringBuilder sb = new StringBuilder();
        String[] filter = argsName.get("filter").split(",");
        int[] filterIndex = new int[filter.length];
        for (int i = 0; i < tmp.get(0).size(); i++) {
            for (int j = 0; j < filter.length; j++) {
                if (tmp.get(0).get(i).equals(filter[j])) {
                    filterIndex[j] = i;
                }
            }
        }
        for (List<String> strings : tmp) {
            for (int index : filterIndex) {
                sb.append(strings.get(index)).append(argsName.get("delimiter"));
            }
            sb.deleteCharAt(sb.length() - 1).append(System.lineSeparator());
        }
        rsl.add(String.valueOf(sb.deleteCharAt(sb.length() - 1)));
        saveCSV(rsl, argsName);
    }

    private static List<List<String>> readCSV(ArgsName argsName) {
        List<String> phrases = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            scanner.useDelimiter(System.lineSeparator());
            while (scanner.hasNext()) {
                phrases.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return phrases.stream()
                .map(phrase -> List.of(phrase.split(";")))
                .collect(Collectors.toList());
    }

    private static void saveCSV(List<String> rsl, ArgsName argsName) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            rsl.forEach(out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments is null");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }

    private static void validate(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException(String.format("Not directory %s", argsName.get("path")));
        }
        if (!argsName.get("delimiter").equals(";")) {
            throw new IllegalArgumentException(String.format("Invalid CSV delimiter %s", argsName.get("delimiter")));
        }
        if (argsName.get("out").isEmpty()) {
            throw new IllegalArgumentException(String.format("Not directory %s", argsName.get("filter")));
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException(String.format("Invalid arguments filter %s", argsName.get("filter")));
        }
    }
}
