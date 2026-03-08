package junjie.parser;

import junjie.commands.AddDeadlineCommand;
import junjie.commands.AddEventCommand;
import junjie.commands.AddTodoCommand;
import junjie.commands.Command;
import junjie.commands.DeleteCommand;
import junjie.commands.ExitCommand;
import junjie.commands.FindCommand;
import junjie.commands.ListCommand;
import junjie.commands.MarkCommand;
import junjie.commands.UnmarkCommand;
import junjie.exceptions.EmptyDescriptionException;
import junjie.exceptions.JunJieException;
import junjie.exceptions.UnknownCommandException;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {
    /**
     * Parses user input from string to a corresponding Command object.
     *
     * @param input a string inputted by the user.
     * @return a Command object corresponding to user input.
     * @throws JunJieException if the user input is not a valid command.
     */
    public static Command parse(String input) throws JunJieException {
        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("todo")) {
            try {
                String description = input.split(" ", 2)[1];
                return new AddTodoCommand(description);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescriptionException("todo must at least write description lah");
            }
        }

        if (input.startsWith("deadline")) {
            try {
                String arguments = input.split(" ", 2)[1];
                String description = arguments.split(" /by ")[0];
                String by = arguments.split(" /by ")[1];
                return new AddDeadlineCommand(description, by);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescriptionException("your command got something wrong");
            }
        }

        if (input.startsWith("event")) {
            try {
                String arguments = input.split(" ", 2)[1];
                String description = arguments.split(" /from ")[0];
                String from = arguments.split(" /from ")[1].split(" /to ")[0];
                String to = arguments.split(" /from ")[1].split(" /to ")[1];
                return new AddEventCommand(description, from, to);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescriptionException("your command got something wrong");
            }
        }

        if (input.startsWith("mark ")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(taskIndex);
        }

        if (input.startsWith("unmark ")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new UnmarkCommand(taskIndex);
        }

        if (input.startsWith("delete ")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(taskIndex);
        }

        if (input.startsWith("find ")) {
            String keyword = input.split(" ", 2)[1];
            return new FindCommand(keyword);
        }

        if (input.equals("bye")) {
            return new ExitCommand();
        }

        throw new UnknownCommandException("idk what command this is");
    }
}