package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {

    private Set<String> tasks = new TreeSet<>();
    private static final int MAX_NUMBER_OF_TASKS = 7;

    // для тестов
    public Set<String> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        if (tasks.size() < MAX_NUMBER_OF_TASKS) {
            if (!tasks.contains(task)) {
                tasks.add(task);
            }
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        return tasks.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

}
