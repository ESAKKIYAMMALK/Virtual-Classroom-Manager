import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class MainApp {
    private static final Scanner SC = new Scanner(System.in);
    private static final VirtualClassroomManager MANAGER = new VirtualClassroomManager();

    public static void main(String[] args) {
        printHeader();
        while (true) {
            System.out.print("\n> ");
            String line = SC.nextLine().trim();
            if (line.isEmpty()) continue;
            List<String> tokens = tokenize(line);
            String cmd = tokens.get(0).toLowerCase(Locale.ROOT);

            try {
                switch (cmd) {
                    case "add_classroom":
                        cmd_add_classroom(tokens); break;
                    case "remove_classroom":
                        cmd_remove_classroom(tokens); break;
                    case "list_classrooms":
                        cmd_list_classrooms(); break;

                    case "add_teacher":
                        cmd_add_teacher(tokens); break;
                    case "list_teachers":
                        cmd_list_teachers(); break;
                    case "assign_teacher":
                        cmd_assign_teacher(tokens); break;

                    case "add_student":
                        cmd_add_student(tokens); break;
                    case "remove_student":
                        cmd_remove_student(tokens); break;
                    case "list_students":
                        cmd_list_students(tokens); break;

                    case "mark_attendance":
                        cmd_mark_attendance(tokens); break;
                    case "list_attendance":
                        cmd_list_attendance(tokens); break;

                    case "schedule_assignment":
                        cmd_schedule_assignment(tokens); break;
                    case "list_assignments":
                        cmd_list_assignments(tokens); break;

                    case "list_student_assignments":
                        cmd_list_student_assignments(tokens); break;
                    case "mark_assignment_done":
                        cmd_mark_assignment_done(tokens); break;
                    case "grade_assignment":
                        cmd_grade_assignment(tokens); break;
                    case "view_grades":
                        cmd_view_grades(tokens); break;

                    case "exit":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Unknown command. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // --------------------- Command implementations ---------------------

    private static void cmd_add_classroom(List<String> t) {
        if (t.size() < 3) { System.out.println("Usage: add_classroom <classroomId> <className>"); return; }
        MANAGER.addClassroom(t.get(1), t.get(2));
        System.out.println("Classroom added.");
    }

    private static void cmd_remove_classroom(List<String> t) {
        if (t.size() < 2) { System.out.println("Usage: remove_classroom <classroomId>"); return; }
        MANAGER.removeClassroom(t.get(1));
        System.out.println("Classroom removed.");
    }

    private static void cmd_list_classrooms() {
        Collection<Classroom> cls = MANAGER.listClassrooms();
        if (cls.isEmpty()) System.out.println("No classrooms available.");
        else cls.forEach(System.out::println);
    }

    // Teachers
    private static void cmd_add_teacher(List<String> t) {
        if (t.size() < 3) { System.out.println("Usage: add_teacher <teacherId> <teacherName>"); return; }
        MANAGER.addTeacher(t.get(1), t.get(2));
        System.out.println("Teacher added.");
    }

    private static void cmd_list_teachers() {
        Collection<Teacher> ts = MANAGER.listTeachers();
        if (ts.isEmpty()) System.out.println("No teachers.");
        else ts.forEach(System.out::println);
    }

    private static void cmd_assign_teacher(List<String> t) {
        if (t.size() < 3) { System.out.println("Usage: assign_teacher <teacherId> <classroomId>"); return; }
        MANAGER.assignTeacherToClass(t.get(1), t.get(2));
        System.out.println("Teacher assigned to classroom.");
    }

    // Students
    private static void cmd_add_student(List<String> t) {
        if (t.size() < 4) { System.out.println("Usage: add_student <studentId> <studentName> <classroomId>"); return; }
        String sid = t.get(1), name = t.get(2), cid = t.get(3);
        Classroom c = MANAGER.getClassroom(cid);
        c.addStudent(new Student(sid, name));
        System.out.println("Student added.");
    }

    private static void cmd_remove_student(List<String> t) {
        if (t.size() < 3) { System.out.println("Usage: remove_student <studentId> <classroomId>"); return; }
        String sid = t.get(1), cid = t.get(2);
        Classroom c = MANAGER.getClassroom(cid);
        c.removeStudent(sid);
        System.out.println("Student removed.");
    }

    private static void cmd_list_students(List<String> t) {
        if (t.size() < 2) { System.out.println("Usage: list_students <classroomId>"); return; }
        Classroom c = MANAGER.getClassroom(t.get(1));
        if (c.getStudents().isEmpty()) System.out.println("No students in " + c.getClassName());
        else c.getStudents().forEach(System.out::println);
    }

    // Attendance
    private static void cmd_mark_attendance(List<String> t) {
        // mark_attendance <studentId> <classroomId> <present|absent> [yyyy-MM-dd]
        if (t.size() < 4) { System.out.println("Usage: mark_attendance <studentId> <classroomId> <present|absent> [yyyy-MM-dd]"); return; }
        String sid = t.get(1), cid = t.get(2), status = t.get(3);
        LocalDate date = null;
        if (t.size() >= 5) {
            try { date = LocalDate.parse(t.get(4)); }
            catch (DateTimeParseException e) { throw new IllegalArgumentException("Invalid date format; use yyyy-MM-dd"); }
        } else date = LocalDate.now();

        boolean present = status.equalsIgnoreCase("present") || status.equalsIgnoreCase("true") || status.equals("1");
        Classroom c = MANAGER.getClassroom(cid);
        Student s = c.getStudent(sid).orElseThrow(() -> new IllegalArgumentException("Student not found in class"));
        s.markAttendance(date, present);
        System.out.println("Attendance marked for " + sid + " on " + date + " as " + (present ? "Present" : "Absent"));
    }

    private static void cmd_list_attendance(List<String> t) {
        if (t.size() < 3) { System.out.println("Usage: list_attendance <studentId> <classroomId>"); return; }
        String sid = t.get(1), cid = t.get(2);
        Classroom c = MANAGER.getClassroom(cid);
        Student s = c.getStudent(sid).orElseThrow(() -> new IllegalArgumentException("Student not found in class"));
        List<Attendance> atts = s.getAttendanceList();
        if (atts.isEmpty()) System.out.println("No attendance records for " + sid);
        else atts.forEach(System.out::println);
    }

    // Assignments
    private static void cmd_schedule_assignment(List<String> t) {
        // schedule_assignment <classroomId> <assignmentId> <title> [dueDate:yyyy-MM-dd] [description]
        if (t.size() < 4) { System.out.println("Usage: schedule_assignment <classroomId> <assignmentId> <title> [dueDate] [description]"); return; }
        String cid = t.get(1), aid = t.get(2), title = t.get(3);
        LocalDate due = null;
        String desc = "";
        if (t.size() >= 5) {
            // if token 5 looks like date, parse, else it's a description
            String maybeDate = t.get(4);
            LocalDate parsed = null;
            try { parsed = LocalDate.parse(maybeDate); due = parsed; }
            catch (DateTimeParseException ex) { desc = maybeDate; }
            if (t.size() >= 6) {
                // token 6 is description (if existed)
                desc = desc.isEmpty() ? t.get(5) : desc + " " + t.get(5);
            }
        }
        Classroom c = MANAGER.getClassroom(cid);
        c.addAssignment(new Assignment(aid, title, desc, due));
        System.out.println("Assignment scheduled in class " + cid);
    }

    private static void cmd_list_assignments(List<String> t) {
        if (t.size() < 2) { System.out.println("Usage: list_assignments <classroomId>"); return; }
        Classroom c = MANAGER.getClassroom(t.get(1));
        if (c.getAssignments().isEmpty()) System.out.println("No assignments in " + c.getClassName());
        else c.getAssignments().forEach(System.out::println);
    }

    private static void cmd_list_student_assignments(List<String> t) {
        if (t.size() < 3) { System.out.println("Usage: list_student_assignments <studentId> <classroomId>"); return; }
        String sid = t.get(1), cid = t.get(2);
        Classroom c = MANAGER.getClassroom(cid);
        Student s = c.getStudent(sid).orElseThrow(() -> new IllegalArgumentException("Student not found in class"));
        List<StudentAssignment> list = s.getAssignments();
        if (list.isEmpty()) System.out.println("No assignments for student " + sid);
        else list.forEach(System.out::println);
    }

    private static void cmd_mark_assignment_done(List<String> t) {
        if (t.size() < 4) { System.out.println("Usage: mark_assignment_done <studentId> <classroomId> <assignmentId>"); return; }
        String sid = t.get(1), cid = t.get(2), aid = t.get(3);
        Classroom c = MANAGER.getClassroom(cid);
        Student s = c.getStudent(sid).orElseThrow(() -> new IllegalArgumentException("Student not found in class"));
        s.markAssignmentDone(aid, LocalDate.now());
        System.out.println("Marked assignment " + aid + " done for student " + sid);
    }

    private static void cmd_grade_assignment(List<String> t) {
        if (t.size() < 5) { System.out.println("Usage: grade_assignment <studentId> <classroomId> <assignmentId> <marks>"); return; }
        String sid = t.get(1), cid = t.get(2), aid = t.get(3);
        int marks;
        try { marks = Integer.parseInt(t.get(4)); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("Marks must be integer 0-100"); }
        Classroom c = MANAGER.getClassroom(cid);
        Student s = c.getStudent(sid).orElseThrow(() -> new IllegalArgumentException("Student not found in class"));
        s.gradeAssignment(aid, marks);
        System.out.println("Assigned grade " + marks + " for student " + sid + " on " + aid);
    }

    private static void cmd_view_grades(List<String> t) {
        if (t.size() < 3) { System.out.println("Usage: view_grades <studentId> <classroomId>"); return; }
        String sid = t.get(1), cid = t.get(2);
        Classroom c = MANAGER.getClassroom(cid);
        Student s = c.getStudent(sid).orElseThrow(() -> new IllegalArgumentException("Student not found in class"));
        List<StudentAssignment> list = s.getAssignments();
        boolean anyGraded = false;
        for (StudentAssignment sa : list) {
            if (sa.getGrade().isPresent()) {
                anyGraded = true;
                System.out.println(sa.getAssignment().getAssignmentId() + " : " + sa.getGrade().get());
            }
        }
        if (!anyGraded) System.out.println("No grades recorded for " + sid);
    }

    // --------------------- Utilities ---------------------

    private static void printHeader() {
        System.out.println("=== Virtual Classroom Manager (Enhanced) ===");
        System.out.println("Type a command. Use quotes for multi-word titles or descriptions.");
        System.out.println("Examples:");
        System.out.println("  add_classroom C101 \"Math Grade 10\"");
        System.out.println("  add_student S1 \"Alice Johnson\" C101");
        System.out.println("  schedule_assignment C101 A1 \"Homework 1\" 2025-10-10 \"Chapter 1 exercises\"");
        System.out.println("  mark_assignment_done S1 C101 A1");
        System.out.println();
    }

    /**
     * Tokenize a command line taking quoted strings as single tokens.
     * Example: schedule_assignment C101 A1 "Homework 1" 2025-10-01 "desc"
     */
    private static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder cur = new StringBuilder();
        char quoteChar = '"';
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == quoteChar) {
                inQuotes = !inQuotes;
                continue;
            }
            if (Character.isWhitespace(ch) && !inQuotes) {
                if (cur.length() > 0) {
                    tokens.add(cur.toString());
                    cur.setLength(0);
                }
            } else {
                cur.append(ch);
            }
        }
        if (cur.length() > 0) tokens.add(cur.toString());
        if (inQuotes) System.out.println("Warning: unmatched quote in input");
        return tokens;
    }
}