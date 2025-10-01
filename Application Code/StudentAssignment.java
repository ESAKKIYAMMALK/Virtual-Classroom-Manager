import java.util.*;
import java.time.*;

class StudentAssignment {
    private final Assignment assignment;
    private boolean completed;
    private LocalDate completedOn;
    private Integer grade; // null if not graded

    public StudentAssignment(Assignment assignment) {
        this.assignment = assignment;
        this.completed = false;
        this.completedOn = null;
        this.grade = null;
    }

    public Assignment getAssignment() { return assignment; }
    public boolean isCompleted() { return completed; }
    public Optional<LocalDate> getCompletedOn() { return Optional.ofNullable(completedOn); }
    public Optional<Integer> getGrade() { return Optional.ofNullable(grade); }

    public void markDone(LocalDate date) {
        if (date == null) date = LocalDate.now();
        this.completed = true;
        this.completedOn = date;
    }

    public void setGrade(int marks) {
        if (marks < 0 || marks > 100) throw new IllegalArgumentException("Marks must be between 0 and 100");
        this.grade = marks;
    }

    @Override
    public String toString() {
        return assignment.toString() +
               " | Completed: " + completed +
               (completedOn != null ? " on " + completedOn : "") +
               (grade != null ? " | Grade: " + grade : "");
    }
}