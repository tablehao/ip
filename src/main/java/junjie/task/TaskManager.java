package junjie.task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String INDENT = " ".repeat(8);
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(String taskType, String argument) {
        Task task = null;
        String description = "";

        switch (taskType) {
        case "todo":
            description = argument;

            task = new Todo(description);
            break;
        case "deadline":
            description = argument.split(" /by ")[0];
            String by = argument.split(" /by ")[1];
            ;

            task = new Deadline(description, by);
            break;
        case "event":
            description = argument.split(" /from ")[0];
            String from = argument.split(" /from ")[1].split(" /to ")[0];
            String to = argument.split(" /from ")[1].split(" /to ")[1];

            task = new Event(description, from, to);
            break;
        }

        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task getLatestTask() {
        return tasks.get(tasks.size() - 1);
    }

    public void markTaskDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markTaskUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "empty like my soul");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
        }
    }
}