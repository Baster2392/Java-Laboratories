package org.example.client;

import org.example.model.Message;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {
    private static Logger logger = Logger.getGlobal();
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            logger.info("Nawiązano połączenie z serwerem: " + socket);

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // get ready
            String status = bufferedReader.readLine();
            logger.info("Odebrano: " + status);

            // send n
            int n = 3;
            outputStream.write(n);
            logger.info("Wysłano: " + n);

            // get ready for messages
            status = bufferedReader.readLine();
            logger.info("Odebrano: " + status);

            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < n; i++) {
                System.out.print("Wpisz wiadomość " + i + ": ");
                String mes = scanner.nextLine();
                //printWriter.println(mes);
                Message message = new Message(i, mes);
                objectOutputStream.writeObject(message);
            }

            socket.close();
        } catch (IOException e) {
            logger.info("Wyjątek klienta: " + e.getMessage());
        }
    }
}