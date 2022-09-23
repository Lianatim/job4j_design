package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("Hello")) {
                        server.close();
                        out.write("Hello, dear friend.".getBytes());
                    } else if (str.contains("Exit")) {
                        server.close();
                        System.out.println("Successful closing of the server");
                        break;
                    } else {
                        out.write("What?".getBytes());
                    }
                    System.out.println(str);

                    out.flush();
                }
            }
        }
    }
}