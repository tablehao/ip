package junjie;

import junjie.commands.Command;
import junjie.exceptions.JunJieException;
import junjie.parser.Parser;
import junjie.storage.Storage;
import junjie.task.TaskList;
import junjie.ui.Ui;

public class JunJie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public JunJie() {
        ui = new Ui();
        storage = new Storage();

        try {
            tasks = storage.load();
        } catch (JunJieException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command c = Parser.parse(userInput);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (JunJieException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new JunJie().run();
    }
}