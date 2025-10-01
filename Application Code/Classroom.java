import java.util.*;
public class Classroom {
    private final String classroomId;
    private String className;
    private final Map<String, Student> students = new HashMap<>();
    private final List<Assignment> assignments = new ArrayList<>();
    private Teacher teacher; // optional

    public Classroom(String classroomId, String className) {
        if (classroomId == null || classroomId.isEmpty()) throw new IllegalArgumentException("Classroom ID cannot be empty");
        this.classroomId = classroomId;
        this.className = className == null ? "" : className;
    }

    public String getClassroomId() { return classroomId; }
    public String getClassName() { return className; }

    public void setTeacher(Teacher t) { this.teacher = t; }
    public Optional<Teacher> getTeacher() { return Optional.ofNullable(teacher); }

    public void addStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("Student cannot be null");
        if (students.containsKey(student.getStudentId())) throw new IllegalArgumentException("Student already exists in class");
        students.put(student.getStudentId(), student);

        // ensure existing assignments are added to the new student
        for (Assignment a : assignments) student.addAssignment(a);
    }

    public void removeStudent(String studentId) {
        if (students.remove(studentId) == null) throw new IllegalArgumentException("Student not found in class");
    }

    public Collection<Student> getStudents() {
        return Collections.unmodifiableCollection(students.values());
    }

    public Optional<Student> getStudent(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }

    public void addAssignment(Assignment assignment) {
        if (assignment == null) throw new IllegalArgumentException("Assignment cannot be null");
        // prevent duplicate assignment ids in classroom
        boolean exists = assignments.stream().anyMatch(a -> a.getAssignmentId().equals(assignment.getAssignmentId()));
        if (exists) throw new IllegalArgumentException("Assignment with same ID already scheduled for this class");
        assignments.add(assignment);
        // distribute to students
        for (Student s : students.values()) s.addAssignment(assignment);

        // notification
        System.out.println("Notification: Assignment " + assignment.getAssignmentId() + " scheduled for classroom " + classroomId +
                           ". Distributed to " + students.size() + " student(s).");
    }

    public List<Assignment> getAssignments() {
        return Collections.unmodifiableList(assignments);
    }

    public Optional<Assignment> findAssignment(String assignmentId) {
        return assignments.stream().filter(a -> a.getAssignmentId().equals(assignmentId)).findFirst();
    }

    @Override
    public String toString() {
        return "Classroom[" + classroomId + ", " + className + ", Students=" + students.size() + (teacher != null ? ", Teacher=" + teacher.getName() : "") + "]";
    }
}
