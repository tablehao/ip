# JunJie User Guide

JunJie is a CLI only chatbot, designed to help you stay on top of your work by tracking your todos, deadlines and events
for you.
---

## Quickstart

1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest `.jar` file from [here](https://github.com/tablehao/ip/releases) into a folder of your choice.
3. Open a command terminal, cd into the folder you put the jar file in, and enter the following command to start the
   application:
    ```bash
    java -jar junjie.jar
    ```
4. JunJie will greet you with a message, after which you may type a command into the terminal and press `Enter` to
   execute it.<br>
   Some example commands you can try:
    - `list`: Lists all tasks.
    - `todo project`: Adds a `todo` with the description `project` to your task list.
    - `bye`: Exits the application.
5. Refer to the Features below for details of each command.

---

## Features

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Adding a todo: `todo`

Adds a basic task to the task list.

Format: `todo <description>`

### Adding a deadline: `deadline`

Adds a task that has a deadline to the task list.

Format: `deadline <description> /by <deadline>`

### Adding an event: `event`

Adds a task that occurs over a time period to the task list.

Format: `event <description> /from <start-time> /to <end-time>`

### Marking a task: `mark`

Marks a task as completed.

Format: `mark <index>`

### Unmarking a task: `unmark`

Marks a task as incomplete.

Format: `unmark <index>`

### Deleting a task: `delete`

Deletes a task from the task list

Format: `delete <index>`

### Finding a task containing a keyword: `find`

Finds all tasks containing the keyword in their description.

Format: `find <keyword>`

### Exiting the application: `bye`

Exits the application.

Format: `bye`