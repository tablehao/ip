package junjie.commands;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.Deadline;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.ui.Ui;

/**
 * Adds a deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        storage.save(tasks);
        ui.showMessage(
                "Okay bro I add this to your list liao!",
                task.toString()
        );
    }
}