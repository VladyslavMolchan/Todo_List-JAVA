package com.todoList;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index);
        }
        return null;
    }

    public void removeTask(int index) {
        if (isValidIndex(index)) {
            tasks.remove(index);
        }
    }

    public void markTaskAsCompleted(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsCompleted();
        }
    }

    public void editTask(int index, String newTitle, String newDescription) {
        Task task = getTask(index);
        if (task != null) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
        }
    }

    public void sortTasksByCompletion() {
        tasks = tasks.stream()
                .sorted(Comparator.comparing(Task::isCompleted))
                .collect(Collectors.toList());
    }

    public void saveTasksToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        }
    }

    public void loadTasksFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) ois.readObject();
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
