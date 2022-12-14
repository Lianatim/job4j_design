package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    private boolean validate(String line) {
        boolean rsl = !line.startsWith("=") && line.matches("(.*)=(.*)");
        if (!rsl) {
            throw new IllegalArgumentException("pattern violation");
        }
        return rsl;
    }


    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines()
                    .filter(s -> !s.isEmpty())
                    .filter(s -> !s.startsWith("#"))
                    .filter(this::validate)
                    .map(s -> s.split("=", 2))
                    .forEach(key -> values.put(key[0], key[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}