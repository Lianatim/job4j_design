package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> saveChat;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.saveChat = new ArrayList<>();
    }

    public void run() {
        String greeting = "Welcome to the chat. This chat has three commands: OUT, STOP, CONTINUE. Please enter a message: ";
        saveChat.add(greeting);
        System.out.println(greeting);
        boolean readAnswer = true;
        List<String> tmp = readPhrases();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = "";
            while (!OUT.equals(line)) {
                line = br.readLine();
                saveChat.add(line);
                switch (line) {
                    case STOP -> {
                        System.out.printf("Console chat is stopped, if you want to continue, click %s%n", CONTINUE);
                        readAnswer = false;
                    }
                    case CONTINUE -> {
                        String recover = "Chat resumed ";
                        saveChat.add(recover);
                        System.out.println(recover);
                        readAnswer = true;
                    }
                    default -> {
                        if (readAnswer) {
                            String answer = tmp.get(new Random().nextInt(readPhrases().size()));
                            saveChat.add(answer);
                            System.out.println(answer);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                phrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
            log.forEach(out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/consoleChat/consoleChat.txt", "./data/consoleChat/botWords.txt");
        cc.run();
        cc.saveLog(cc.saveChat);
    }
}