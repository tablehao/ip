package junjie.task;

/**
 * An abstract class representing a generic task that has a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", getStatusIcon(), description);
    }
}