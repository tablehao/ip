package junjie.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import junjie.exceptions.StorageException;
import junjie.task.Deadline;
import junjie.task.Event;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.task.Todo;

public class Storage {
    public static final String DEFAULT_STORAGE_PATH = "data/tasks.txt";

    public final Path path;

    public Storage() {
        path = Paths.get(DEFAULT_STORAGE_PATH);
    }

    public void save(TaskList taskList) throws StorageException {
        try {
            List<String> lines = new ArrayList<>();

            for (Task task : taskList.getAllTasks()) {
                lines.add(task.toFileFormat());
            }

            Files.createDirectories(Paths.get("data"));
            Files.write(path, lines);
        } catch (IOException ioe) {
            throw new StorageException("Error saving file: " + ioe.getMessage());
        }
    }

    public TaskList load() throws StorageException {
        if (!Files.exists(path)) {
            return new TaskList();
        }

        try {
            List<String> lines = Files.readAllLines(path);
            List<Task> tasks = new ArrayList<>();

            for (String line : lines) {
                tasks.add(convertLineToTask(line));
            }

            return new TaskList(tasks);

        } catch (IOException ioe) {
            throw new StorageException("Error reading saved file: " + ioe.getMessage());
        }
    }

    private Task convertLineToTask(String line) throws StorageException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
            break;
        default:
            throw new StorageException("Error reading saved file: Task type is invalid.");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}