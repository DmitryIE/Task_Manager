package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TodosTests {

    @DisplayName("Проверка добавления новых задач")
    @Test
    public void testAddTask() {
        Todos todos = new Todos();
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.addTask("Третья");
        todos.addTask("Первая");
        todos.addTask("Четвертая");
        todos.addTask("Пятая");
        todos.addTask("Шестая");
        todos.addTask("Седьмая");
        todos.addTask("Восьмая");
        List<String> actualResult = todos.getTasks();
        List<String> expectResult = Arrays.asList("Первая", "Вторая", "Третья", "Четвертая", "Пятая", "Шестая", "Седьмая");
        Assertions.assertEquals(expectResult, actualResult);
    }

    @DisplayName("Проверка удаления задач")
    @Test
    public void testRemoveTask() {
        Todos todos = new Todos();
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.addTask("Третья");
        todos.removeTask("Вторая");
        todos.removeTask("Первая");
        todos.removeTask("Первая");

        List<String> actualResult = todos.getTasks();
        List<String> expectResult = Arrays.asList("Третья");
        Assertions.assertEquals(expectResult, actualResult);
    }

    @DisplayName("Проверка вывода всех задач")
    @Test
    public void testGetAllTasks() {
        Todos todos = new Todos();
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.addTask("Третья");
        todos.addTask("Первая");
        todos.addTask("Четвертая");
        todos.addTask("Пятая");
        todos.addTask("Шестая");
        todos.addTask("Седьмая");
        todos.addTask("Восьмая");
        String expectResult = "Вторая Первая Пятая Седьмая Третья Четвертая Шестая";
        String actualResult = todos.getAllTasks();
        Assertions.assertEquals(expectResult, actualResult);
    }

}
