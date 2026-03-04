package junjie.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTaskDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task markTaskUndone(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public List<String> getAllTasksAsString() {
        List<String> listOfTasks = new ArrayList<>();

        if (tasks.isEmpty()) {
            listOfTasks.add("empty like my soul");
            return listOfTasks;
        }

        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.add((i + 1) + "." + tasks.get(i));
        }

        return listOfTasks;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}