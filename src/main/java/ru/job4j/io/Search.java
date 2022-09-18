package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (validate(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    public static boolean validate(String[] args) {
        boolean rsl = true;
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder or file extension is null");
        }
        if (!Paths.get(args[0]).toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s", Paths.get(args[0]).toFile().getAbsoluteFile()));
        }
        if (!Paths.get(args[0]).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", Paths.get(args[0]).toFile().getAbsoluteFile()));
        }
        return rsl;
    }
}