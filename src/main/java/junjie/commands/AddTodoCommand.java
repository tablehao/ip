package junjie.commands;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.task.Todo;
import junjie.ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks);
        ui.showMessage(
                "Okay bro I add this to your list liao!",
                task.toString()
        );
    }
}