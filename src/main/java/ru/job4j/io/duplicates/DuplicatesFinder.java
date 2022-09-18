package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        SimpleFileVisitor<Path> simpleFileVisitor = new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Map<FileProperty, List<Path>> duplicates = new HashMap<>();
                FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
                duplicates.putIfAbsent(fileProperty, new ArrayList<>());
                duplicates.get(fileProperty).add(file.toAbsolutePath());
                for (Map.Entry<FileProperty, List<Path>> entry : duplicates.entrySet()) {
                    System.out.printf("File name : \"%s\", file size : \"%d\" ", entry.getKey().getName(), entry.getKey().getSize());
                    System.out.println("Path(s) : ");
                    for (Path path : entry.getValue()) {
                        System.out.println(path.toAbsolutePath());
                    }
                }
                return super.visitFile(file, attrs);
            }
        };
        Files.walkFileTree(Path.of("./"), simpleFileVisitor);
    }
}