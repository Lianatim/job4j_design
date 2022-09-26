package ru.job4j.search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchFilesPattern {

    public static Predicate<Path> searchMask(ArgsName argsName) {
        Predicate<Path> regex = p -> p.toFile().getName()
                .matches(argsName.get("n"));
        Predicate<Path> name = p -> p.toFile().getName()
                .equals(argsName.get("n"));
        Predicate<Path> mask = p -> p.toFile().getName()
                .matches(argsName.get("n").replace(".", "[.]")
                        .replace("*", ".+")
                        .replace("?", "."));

        return "name".equals(argsName.get("t")) ? name : "regex".equals(argsName.get("t")) ? regex : mask;
    }

    private void saveLog(List<Path> log, ArgsName argsName) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("o"))))) {
            log.forEach(out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Argument(s) is null");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        Path start = Paths.get(argsName.get("d"));
        SearchFilesPattern searchFilesPattern = new SearchFilesPattern();
        searchFilesPattern.saveLog(search(start, searchMask(argsName)), argsName);

    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(ArgsName argsName) {
        if (!Paths.get(argsName.get("d")).toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s", Paths.get(argsName.get("d")).toFile().getAbsoluteFile()));
        }
        if (!Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", Paths.get(argsName.get("d")).toFile().getAbsoluteFile()));
        }
        if (argsName.get("n").isEmpty()) {
            throw new IllegalArgumentException("The pattern is empty");
        }
        if (argsName.get("t").isEmpty()) {
            throw new IllegalArgumentException("The type of pattern is empty");
        }
        if (argsName.get("o").isEmpty()) {
            throw new IllegalArgumentException("The save file is empty");
        }
    }
}
