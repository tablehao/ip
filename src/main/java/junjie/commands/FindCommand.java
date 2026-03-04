package junjie.commands;

import java.util.List;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.Task;
import junjie.task.TaskList;
import junjie.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        List<Task> listOfTasks = tasks.findTasks(keyword);

        if (listOfTasks.isEmpty()) {
            ui.showMessage("Got nothing for you to see leh...");
        } else {
            ui.showMessage(
                    "Here is what I found boss",
                    listOfTasks
            );
        }
    }
}