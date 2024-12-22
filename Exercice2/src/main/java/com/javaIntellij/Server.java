package com.javaIntellij;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private final String SERVER_FILES_PATH = "src/main/java/com/javaIntellij/Files";
    private final Socket clientSocket;

    public Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1324)) {
            System.out.println("en attente ...  " + 1324);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté : " + clientSocket.getInetAddress());
                new Thread(new Server(clientSocket)).start();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try (
                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true)
        ) {
            writer.print("Entrer le nom du fichier : ");

            String fileName = reader.readLine();
            File file = new File(SERVER_FILES_PATH + fileName);

            if (file.exists() && file.isFile()) {
                writer.println("Fichier valide...");
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        writer.println(line);
                    }
                }
                writer.println("EOF"); // end of file
            } else {
                writer.println("Erreur: Fichier non trouvé");
            }
        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Erreur en fermeture : " + e.getMessage());
            }
        }
    }
}

