package org.example.server;

import org.example.model.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Logger logger = Logger.getGlobal();

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // send ready
            printWriter.println("Ready");

            // get n
            int n = inputStream.read();
            logger.info("Odebrano: " + n);

            // send ready for messages
            printWriter.println("Ready for messages");

            for (int i = 0; i < n; i++) {
                Message message = (Message) objectInputStream.readObject();
                logger.info("Odebrano: " + message);
            }

            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
