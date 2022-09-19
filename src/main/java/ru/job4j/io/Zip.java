package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {

        for (Path source : sources) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                zip.putNextEntry(new ZipEntry(String.valueOf(source.getFileName())));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toAbsolutePath().toString()))) {
                    zip.write(out.readAllBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Arguments is null");
        }

        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format("Not directory %s", Paths.get(argsName.get("d")).toAbsolutePath()));
        }
        List<Path> sources = new ArrayList<>();
        try {
            sources = Search.search(Paths.get(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        zip.packFiles(sources, Paths.get(argsName.get("o")).toFile());
    }
}