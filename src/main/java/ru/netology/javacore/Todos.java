package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {

    private List<String> tasks = new ArrayList<>();

    // для тестов
    public List<String> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        if (tasks.size() < 7) {
            if (!tasks.contains(task)) {
                tasks.add(task);
            }
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        return tasks.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public enum TaskType {
        ADD, REMOVE, RESTORE
    }

}
