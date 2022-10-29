package ru.netology.javacore;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TodoServer {
    private int port;
    private Todos todos;

    private List<String> history = new ArrayList<>();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader
                             (new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("New connection accepted");

                    JsonElement element = JsonParser.parseString(reader.readLine());
                    JsonObject jsonObject = element.getAsJsonObject();
                    String type = jsonObject.get("type").getAsString();
                    if (type.equals(Todos.TaskType.RESTORE.toString())) {
                        undoOperation();
                        writer.println(todos.getAllTasks());
                        continue;
                    }
                    String task = jsonObject.get("task").getAsString();
                    if (type.equals(Todos.TaskType.ADD.toString())) {
                        todos.addTask(task);
                        history.add(type + " " + task);
                    } else if (type.equals(Todos.TaskType.REMOVE.toString())) {
                        todos.removeTask(task);
                        history.add(type + " " + task);
                    }
                    writer.println(todos.getAllTasks());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void undoOperation() {
        if (!history.isEmpty()) {
            String[] lastOperation = history.get(history.size() - 1).split(" ");
            String type = lastOperation[0];
            String task = lastOperation[1];
            if (type.equals(Todos.TaskType.ADD.toString())) {
                todos.removeTask(task);
            } else if (type.equals(Todos.TaskType.REMOVE.toString())) {
                todos.addTask(task);
            }
            history.remove(history.size() - 1);
        }
    }
}
