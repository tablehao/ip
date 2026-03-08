package junjie.ui;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import junjie.task.Task;

/**
 * Handles user inputs and displays messages back to user.
 */
public class Ui {
    /**
     * An indentation added to messages, to align it with the first message
     * which has "JunJie: " at the front.
     */
    private static final String INDENT = " ".repeat(8);

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Shows a string to the user as a speech bubble from JunJie.
     *
     * @param message a string to be shown to the user.
     */
    private void say(String message) {
        out.println("JunJie: " + message);
    }

    /**
     * Prompts the user for command and reads the string inputted.
     * Ignores leading and trailing whitespaces.
     *
     * @return a string inputted by the user.
     */
    public String getUserInput() {
        out.print("   You: ");
        return in.nextLine().strip();
    }

    public void showWelcome() {
        showMessage("Hello! I'm JunJie. What can I do for you?");
    }

    public void showError(String error) {
        showMessage("Woi see what you've done", error);
    }

    /**
     * Shows message(s) to the user.
     * Only the first message is prefixed with "JunJie: ",
     * the subsequent messages are indented accordingly.
     *
     * @param messages a list of string to be shown to the user.
     */
    public void showMessage(String... messages) {
        say(messages[0]);
        for (int i = 1; i < messages.length; i++) {
            out.println(INDENT + messages[i]);
        }
    }

    /**
     * Shows a message to the user and a formatted list of tasks.
     *
     * @param message     a string to be shown to the user.
     * @param listOfTasks a list of tasks to be shown.
     */
    public void showMessage(String message, List<Task> listOfTasks) {
        say(message);
        for (int i = 0; i < listOfTasks.size(); i++) {
            out.println(INDENT + (i + 1) + "." + listOfTasks.get(i));
        }
    }
}