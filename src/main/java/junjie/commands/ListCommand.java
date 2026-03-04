package junjie.commands;

import java.util.List;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        List<Task> listOfTasks = tasks.getAllTasks();

        if (listOfTasks.isEmpty()) {
            ui.showMessage("Here is your list <3", "nothing to see lol");
        } else {
            ui.showMessage(
                    "Here is your list <3",
                    listOfTasks
            );
        }
    }
}