package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final int PORT = 8989;
    private static final String LOCALHOST = "127.0.0.1";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(LOCALHOST, PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

//            writer.println("{ \"type\": \"REMOVE\", \"task\": \"Первая\" }");
//            writer.println("{ \"type\": \"ADD\", \"task\": \"Третья\" }");
//            writer.println("{ \"type\": \"RESTORE\" }");

            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

