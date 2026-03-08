package junjie;

import junjie.commands.Command;
import junjie.exceptions.JunJieException;
import junjie.parser.Parser;
import junjie.storage.Storage;
import junjie.task.TaskList;
import junjie.ui.Ui;

/**
 * Main class for the JunJie chatbot application.
 * Initialises the application and handles the main execution loop.
 */
public class JunJie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a JunJie object initialised with UI, storage and task list.
     * Attempts to load existing tasks from storage,
     * else initialises an empty task list when it fails.
     */
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

    /**
     * Main execution loop of the application.
     * Continuously prompts user for input, parses it, then executes the command.
     * Loop terminates when an exit command is given.
     */
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