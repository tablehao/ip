package junjie.commands;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        Task task = tasks.markTaskUndone(taskIndex);
        storage.save(tasks);
        ui.showMessage(
                "Leopard never changes its spots...",
                task.toString()
        );
    }
}