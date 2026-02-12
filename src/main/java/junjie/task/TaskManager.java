package junjie.task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String INDENT = " ".repeat(8);
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
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