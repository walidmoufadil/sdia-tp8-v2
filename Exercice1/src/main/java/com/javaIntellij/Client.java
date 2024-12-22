package com.javaIntellij;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer un nombre entre 0 et 100");
        int guessedNumber = scanner.nextInt();
        socket.getOutputStream().write(guessedNumber);
        int response = socket.getInputStream().read();
        while (response != 0) {
            if (response == 1) {
                System.out.println("plus grand ...");
            } else {
                System.out.println("plus petit ...");
            }
            guessedNumber = scanner.nextInt();
            socket.getOutputStream().write(guessedNumber);
            response = socket.getInputStream().read();
        }
        System.out.println("Felicitation ! ");
    }
}