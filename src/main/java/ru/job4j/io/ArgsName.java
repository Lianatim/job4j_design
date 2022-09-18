package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The key doesn't exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            validate(arg);
            String[] pair = arg.split("=", 2);
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments are empty");
        }
        names.parse(args);
        return names;
    }

    public static void validate(String arg) {
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("The key - value pair " + arg + " must contain the character \"=\"");
        }
        if (arg.startsWith("-=") || arg.startsWith("=") || arg.indexOf("=") == arg.length() - 1) {
            throw new IllegalArgumentException("The key - value pair " + arg + " must contain the key");
        }
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("The key - value pair " + arg + " must start with the character \"-\"");
        }

    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}