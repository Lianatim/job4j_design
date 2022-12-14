package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {

    public static final int ADD_PATH = 1;
    public static final int GET_CACHE = 2;

    public static final String SELECT = "Выберите меню";
    public static final String DIR_PATH = "Укажите директорию";
    public static final String FILE = "Укажите имя файла для кэширования";
    public static final String RESULT = "Введите имя файла для просмотра содержимого";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1, если необходимо кэшировать файл из директории.
                Введите 2, чтобы получить содержимое файла.
                Введите любое другое число для выхода из программы.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIR_PATH);
        String dir = scanner.nextLine();
        if (!Files.exists(Path.of(dir))) {
            throw new IllegalArgumentException(String.format("Directory %s doesn't exist", dir));
        }
        DirFileCache dirFileCache = new DirFileCache(dir);
        boolean run = true;
        while (run) {
            System.out.println(SELECT);
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_PATH == userChoice) {
                System.out.println(FILE);
                String key = scanner.nextLine();
                if (!Files.exists(Path.of(dir, key))) {
                    throw new IllegalArgumentException(String.format("File %s doesn't exist in this directory", key));
                }
                dirFileCache.put(key, dirFileCache.get(key));
            } else if (GET_CACHE == userChoice) {
                System.out.println(RESULT);
                String key = scanner.nextLine();
                System.out.println(dirFileCache.get(key));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

}