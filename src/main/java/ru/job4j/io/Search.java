package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder or file extension is null");
        }
        if (validate(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean validate(String[] args) {
        boolean rsl = true;
        if (!Paths.get(args[0]).toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s", Paths.get(args[0]).toFile().getAbsoluteFile()));
        }
        if (!Paths.get(args[0]).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", Paths.get(args[0]).toFile().getAbsoluteFile()));
        }
        if (!(args[1]).startsWith(".")) {
            System.out.println(args[1]);
            throw new IllegalArgumentException("The file extension must start with \".\"");
        }
        return rsl;
    }
}
