package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getGlobal();
    private static final List<Thread> threads = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            logger.info("Serwer:  Uruchomiony, oczekiwanie na połączenia...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Serwer: Nowy klient: " + clientSocket);

                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
                threads.add(thread);
            }
        } catch (IOException e) {
            logger.warning("Wyjątek serwera: " + e.getMessage());
        }
    }
}
