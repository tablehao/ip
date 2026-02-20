package junjie;

import java.util.Scanner;

import junjie.exceptions.JunJieException;
import junjie.exceptions.UnknownCommandException;
import junjie.task.Task;
import junjie.task.TaskManager;

public class JunJie {
    private static final String INDENT = " ".repeat(8);

    public static void say(String message) {
        System.out.println("JunJie: " + message);
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        try {
            taskManager.importTasksFromFile();

            String input;
            Scanner in = new Scanner(System.in);

            say("Hello! I'm JunJie. What can I do for you?");

            while (true) {
                System.out.print("   You: ");
                input = in.nextLine().strip();

                if (input.equals("bye")) {
                    say("wgt ord lo!");
                    break;
                }

                if (input.equals("list")) {
                    say("Here is your list <3");

                    taskManager.listTasks();
                    continue;
                }

                if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;

                    taskManager.markTaskDone(index);

                    say("Steady lah this task is mark as done.");
                    System.out.println(INDENT + taskManager.getTask(index));
                    continue;
                }

                if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;

                    taskManager.markTaskUndone(index);

                    say("Leopard never changes its spots...");
                    System.out.println(INDENT + taskManager.getTask(index));
                    continue;
                }

                if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    taskManager.addTask(input);

                    say("Okay bro I add this to your list liao!");
                    System.out.println(INDENT + taskManager.getLatestTask());
                    continue;
                }

                if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;

                    Task deletedTask = taskManager.deleteTask(index);

                    say("This task is no more.");
                    System.out.println(INDENT + deletedTask);
                    continue;
                }

                throw new UnknownCommandException("Eh what talking you?");
            }
        } catch (Exception e) {
            say("Woi see what you do:");
            System.out.println(INDENT + e.getMessage());
        }
    }
}