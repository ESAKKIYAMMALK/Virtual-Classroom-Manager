import java.util.*;
import java.time.*;

public class Student {
    private final String studentId;
    private String name;
    private final List<Attendance> attendanceList = new ArrayList<>();
    private final List<StudentAssignment> assignments = new ArrayList<>();

    public Student(String studentId, String name) {
        if (studentId == null || studentId.isEmpty()) throw new IllegalArgumentException("Student ID cannot be empty");
        if (name == null) name = "";
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }

    public void markAttendance(LocalDate date, boolean present) {
        attendanceList.add(new Attendance(date, present));
    }

    public List<Attendance> getAttendanceList() {
        return Collections.unmodifiableList(attendanceList);
    }

    public void addAssignment(Assignment assignment) {
        if (findStudentAssignment(assignment.getAssignmentId()).isPresent()) return;
        assignments.add(new StudentAssignment(assignment));
    }

    public List<StudentAssignment> getAssignments() {
        return Collections.unmodifiableList(assignments);
    }

    public Optional<StudentAssignment> findStudentAssignment(String assignmentId) {
        return assignments.stream()
                .filter(sa -> sa.getAssignment().getAssignmentId().equals(assignmentId))
                .findFirst();
    }

    public void markAssignmentDone(String assignmentId, LocalDate date) {
        StudentAssignment sa = findStudentAssignment(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found for student"));
        sa.markDone(date == null ? LocalDate.now() : date);
    }

    public void gradeAssignment(String assignmentId, int marks) {
        StudentAssignment sa = findStudentAssignment(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found for student"));
        sa.setGrade(marks);
    }

    @Override
    public String toString() {
        return "Student[" + studentId + ", " + name + "]";
    }
}
