package junjie.commands;

import junjie.exceptions.JunJieException;
import junjie.storage.Storage;
import junjie.task.TaskList;
import junjie.ui.Ui;

public abstract class Command {

    public abstract void execute(Storage storage, TaskList tasks, Ui ui) throws JunJieException;

    public boolean isExit() {
        return false;
    }
}