package com.javaIntellij;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234);
        System.out.println("en attente des clients ...");

        Socket server = socket.accept();


        int number = (int) (Math.random() * 100 + 1);
        int guessedNumber = server.getInputStream().read();
        while (guessedNumber != number) {
            if (guessedNumber > number) {
                server.getOutputStream().write(1);
            } else {
                server.getOutputStream().write(-1);
            }
            guessedNumber = server.getInputStream().read();
        }
        server.getOutputStream().write(0);
        socket.close();
    }
}
