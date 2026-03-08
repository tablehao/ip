package junjie.commands;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.Event;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.ui.Ui;

/**
 * Adds an event to the task list.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.save(tasks);
        ui.showMessage(
                "Okay bro I add this to your list liao!",
                task.toString()
        );
    }
}