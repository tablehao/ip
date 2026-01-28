import java.util.Scanner;

public class JunJie {
    public static void say(String message) {
        System.out.println("JunJie: " + message);
    }
    public static void main(String[] args) {
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

            say(line);
        }
    }
}