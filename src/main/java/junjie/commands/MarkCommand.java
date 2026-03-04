package junjie.commands;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        Task task = tasks.markTaskDone(taskIndex);
        storage.save(tasks);
        ui.showMessage(
                "Steady lah this task is mark as done",
                task.toString()
        );
    }
}