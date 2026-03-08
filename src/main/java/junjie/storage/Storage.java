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

/**
 * Handles saving and loading of tasks to and from a local file.
 */
public class Storage {
    /**
     * Default file path used for saving tasks.
     */
    public static final String DEFAULT_STORAGE_PATH = "data/tasks.txt";

    public final Path path;

    /**
     * Creates a Storage object with the default file path.
     */
    public Storage() {
        path = Paths.get(DEFAULT_STORAGE_PATH);
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @param taskList TaskList containing tasks to be saved.
     * @throws StorageException if an I/O error occurs while writing to file.
     */
    public void save(TaskList taskList) throws StorageException {
        try {
            List<String> lines = new ArrayList<>();

            for (Task task : taskList.getAllTasks()) {
                lines.add(task.toFileFormat());
            }

            Files.createDirectories(Paths.get("data"));
            Files.write(path, lines);
        } catch (IOException ioe) {
            throw new StorageException("Error saving file: " + ioe);
        }
    }

    /**
     * Loads the {@code TaskList} data from storage file, and then returns it.
     * An empty {@code TaskList} is returned if the storage file does not exist.
     *
     * @return TaskList loaded with tasks from the storage file.
     * @throws StorageException if an I/O error occurs while reading the file.
     */
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
            throw new StorageException("Error reading saved file: " + ioe);
        }
    }

    /**
     * Converts a task that is saved as a string to a Task object.
     *
     * @param line a string that represents the task in file format.
     * @return a Task object converted from the string.
     * @throws StorageException if the task cannot be parsed properly.
     */
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