import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JunJie {
    public static void say(String message) {
        System.out.println("JunJie: " + message);
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String line;
        Scanner in = new Scanner(System.in);

        say("Hello! I'm JunJie. What can I do for you?");

        while (true) {
            System.out.print("   You: ");
            line = in.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                say("ORD loh!");
                break;
            }

            if (line.equalsIgnoreCase("list")) {
                say("Here is your list <3");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" ".repeat(8) + (i + 1) + "." + tasks.get(i));
                }
            } else if (line.startsWith("mark")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks.get(index).markAsDone();
                say("Steady lah this task is mark as done.");
                System.out.println(" ".repeat(8) + tasks.get(index));
            } else if (line.startsWith("unmark")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks.get(index).markAsUndone();
                say("Leopard never changes its spots...");
                System.out.println(" ".repeat(8) + tasks.get(index));
            } else {
                tasks.add(new Task(line));
                say("Okay bro I add \"" + line + "\" to your list liao!");
            }
        }
    }
}