package junjie.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. Contains the data of the task list.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing the specified tasks.
     *
     * @param tasks the list of tasks to initialise the task list with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task at the given index as complete.
     *
     * @param index the index of the task in the task list.
     * @return the task that was marked as complete.
     */
    public Task markTaskDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the given index as incomplete.
     *
     * @param index the index of the task in the task list.
     * @return the task that was mark as incomplete.
     */
    public Task markTaskUndone(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index the index of the task in the task list.
     * @return the task that was deleted.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Finds and returns a list of tasks whose description contains the specified keyword.
     *
     * @param keyword the keyword to search for.
     * @return a list of tasks containing the keyword in their description.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> listOfTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                listOfTasks.add(task);
            }
        }

        return listOfTasks;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}