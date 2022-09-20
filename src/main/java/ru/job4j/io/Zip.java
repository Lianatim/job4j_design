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
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    private void validate(ArgsName argsName) {
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format("Not directory %s", argsName.get("d")));
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Invalid file extension %s", argsName.get("e")));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Invalid name %s", argsName.get("o")));
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Arguments is null");
        }

        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        zip.validate(argsName);
        List<Path> sources = new ArrayList<>();
        try {
            sources = Search.search(Paths.get(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        zip.packFiles(sources, Paths.get(argsName.get("o")).toFile());
    }
}