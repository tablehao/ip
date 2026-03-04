package junjie.ui;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import junjie.task.Task;

public class Ui {
    private static final String INDENT = " ".repeat(8);

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    private void say(String message) {
        out.println("JunJie: " + message);
    }

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

    public void showMessage(String... messages) {
        say(messages[0]);
        for (int i = 1; i < messages.length; i++) {
            out.println(INDENT + messages[i]);
        }
    }

    public void showMessage(String message, List<Task> listOfTasks) {
        say(message);
        for (int i = 0; i < listOfTasks.size(); i++) {
            out.println(INDENT + (i + 1) + "." + listOfTasks.get(i));
        }
    }
}