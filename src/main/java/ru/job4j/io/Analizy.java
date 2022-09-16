package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        String on = "";
        String of = "";
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] arr = line.split(" ");
                if (on.isEmpty() && (arr[0].equals("400") || arr[0].equals("500"))) {
                    on = arr[1];
                    of = "";
                } else if (of.isEmpty() && !on.isEmpty() && (arr[0].equals("200") || arr[0].equals("300"))) {
                    of = arr[1];
                    out.write(on + ";" + of + ";");
                    on = "";
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String source = "logServer.txt";
        String target = "logServerResult.txt";
        Analizy test = new Analizy();
        test.unavailable(source, target);
    }
}