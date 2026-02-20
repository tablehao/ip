package junjie.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import junjie.exceptions.EmptyDescriptionException;

public class TaskManager {
    private static final String INDENT = " ".repeat(8);
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(String input) throws EmptyDescriptionException {
        if (input.split(" ", 2).length < 2) {
            throw new EmptyDescriptionException("must write description lah");
        }

        String taskType = input.split(" ", 2)[0];
        String argument = input.split(" ", 2)[1];

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

    public Task deleteTask(int index) {
        return tasks.remove(index);

    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "empty like my soul");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
        }
    }

    public void importTasksFromFile() throws IOException, EmptyDescriptionException {
        File f = new File("./data/junjie.txt");
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
            System.out.println("no save file detected, making one now...");
        } else {
            Scanner s = new Scanner(f);
            int index = 0;
            while (s.hasNext()) {
                String line = s.nextLine();
                String input = line.split("\\|", 2)[0];
                String done = line.split("\\|", 2)[1];

                addTask(input);
                if (done.equals("1")) {
                    markTaskDone(index);
                }
                index++;
            }
        }
    }

    public void exportTasksToFile() throws IOException {
        FileWriter fw = new FileWriter("./data/junjie.txt");

        for (Task task : tasks) {
            String line = "";
            if (task instanceof Todo) {
                line = "todo " + task.getDescription()
                        + "|" + task.getStatus();
            } else if (task instanceof Deadline) {
                line = "deadline " + task.getDescription()
                        + " /by " + ((Deadline) task).getBy()
                        + "|" + task.getStatus();
            } else if (task instanceof Event) {
                line = "event " + task.getDescription()
                        + " /from " + ((Event) task).getFrom()
                        + " /to " + ((Event) task).getTo()
                        + "|" + task.getStatus();
            }
            line += System.lineSeparator();
            fw.write(line);
        }

        fw.close();
    }
}