import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JunJie {
    public static void say(String message) {
        System.out.println("JunJie: " + message);
    }

    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();

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
                    System.out.println((i + 1) + ": " + tasks.get(i));
                }
            } else {
                tasks.add(line);
                say("Okay bro I add \"" + line + "\" to your list liao!");
            }
        }
    }
}