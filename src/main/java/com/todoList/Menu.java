package com.todoList;


import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private TaskManager taskManager;
    private Scanner scanner;

    public Menu() {
        this.taskManager = new TaskManager();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nTo-Do List Menu:");
            System.out.println("1. Add task");
            System.out.println("2. View all tasks");
            System.out.println("3. Remove task");
            System.out.println("4. Mark task as completed");
            System.out.println("5. Edit task");
            System.out.println("6. Sort tasks by completion");
            System.out.println("7. Save tasks to file");
            System.out.println("8. Load tasks from file");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewAllTasks();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    markTaskAsCompleted();
                    break;
                case 5:
                    editTask();
                    break;
                case 6:
                    sortTasksByCompletion();
                    break;
                case 7:
                    saveTasksToFile();
                    break;
                case 8:
                    loadTasksFromFile();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task task = new Task(title, description);
        taskManager.addTask(task);
        System.out.println("Task added successfully.");
    }

    private void viewAllTasks() {
        System.out.println("\nAll Tasks:");
        int index = 0;
        for (Task task : taskManager.getAllTasks()) {
            System.out.println((index++) + ": " + task);
        }
    }

    private void removeTask() {
        System.out.print("Enter task index to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        taskManager.removeTask(index);
        System.out.println("Task removed successfully.");
    }

    private void markTaskAsCompleted() {
        System.out.print("Enter task index to mark as completed: ");
        int index = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        taskManager.markTaskAsCompleted(index);
        System.out.println("Task marked as completed.");
    }

    private void editTask() {
        System.out.print("Enter task index to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new task title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new task description: ");
        String newDescription = scanner.nextLine();
        taskManager.editTask(index, newTitle, newDescription);
        System.out.println("Task edited successfully.");
    }

    private void sortTasksByCompletion() {
        taskManager.sortTasksByCompletion();
        System.out.println("Tasks sorted by completion status.");
    }

    private void saveTasksToFile() {
        System.out.print("Enter filename to save tasks: ");
        String filename = scanner.nextLine();
        try {
            taskManager.saveTasksToFile(filename);
            System.out.println("Tasks saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        System.out.print("Enter filename to load tasks: ");
        String filename = scanner.nextLine();
        try {
            taskManager.loadTasksFromFile(filename);
            System.out.println("Tasks loaded from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
