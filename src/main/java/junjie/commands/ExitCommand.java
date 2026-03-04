package junjie.commands;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.TaskList;
import junjie.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException {
        ui.showMessage("wgt ord lo!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}