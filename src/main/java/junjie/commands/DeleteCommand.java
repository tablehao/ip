package junjie.commands;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.ui.Ui;

/**
 * Deletes a task from the task list using its index in the list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        Task task = tasks.deleteTask(taskIndex);
        storage.save(tasks);
        ui.showMessage(
                "This task is no more",
                task.toString()
        );
    }
}