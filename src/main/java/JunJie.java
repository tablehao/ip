import java.util.Scanner;

public class JunJie {
    public static final String INDENT = " ".repeat(8);

    public static void say(String message) {
        System.out.println("JunJie: " + message);
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

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

            if (input.startsWith("todo")) {
                String description = input.split(" ", 2)[1];

                Task task = new Todo(description);
                taskManager.addTask(task);

                say("Okay bro I add this to your list liao!");
                System.out.println(INDENT + task);
                continue;
            }

            if (input.startsWith("deadline")) {
                String arguments = input.split(" ", 2)[1];
                String description = arguments.split(" /by ")[0];
                String by = arguments.split(" /by ")[1];

                Task task = new Deadline(description, by);
                taskManager.addTask(task);

                say("Okay bro I add this to your list liao!");
                System.out.println(INDENT + task);
                continue;
            }

            if (input.startsWith("event")) {
                String arguments = input.split(" ", 2)[1];
                String description = arguments.split(" /from ")[0];
                String from = arguments.split(" /from ")[1].split(" /to ")[0];
                String to = arguments.split(" /from ")[1].split(" /to ")[1];

                Task task = new Event(description, from, to);
                taskManager.addTask(task);

                say("Okay bro I add this to your list liao!");
                System.out.println(INDENT + task);
                continue;
            }

            say("What talking you?");
        }
    }
}