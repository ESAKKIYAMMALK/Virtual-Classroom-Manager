import java.util.*;
import java.time.*;

public class Assignment {
    private final String assignmentId;
    private final String title;
    private final String description;
    private final LocalDate dueDate; // may be null

    public Assignment(String assignmentId, String title, String description, LocalDate dueDate) {
        if (assignmentId == null || assignmentId.isEmpty())
            throw new IllegalArgumentException("Assignment ID cannot be empty");
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("Assignment title cannot be empty");
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description == null ? "" : description;
        this.dueDate = dueDate;
    }

    public String getAssignmentId() { return assignmentId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Optional<LocalDate> getDueDate() { return Optional.ofNullable(dueDate); }

    @Override
    public String toString() {
        return "Assignment[" + assignmentId + ", " + title +
               (dueDate != null ? ", due:" + dueDate : "") + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;
        Assignment a = (Assignment) o;
        return assignmentId.equals(a.assignmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentId);
    }
}