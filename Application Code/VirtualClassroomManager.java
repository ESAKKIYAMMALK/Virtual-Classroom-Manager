import java.util.*;

public class VirtualClassroomManager {
    private final Map<String, Classroom> classrooms = new HashMap<>();
    private final Map<String, Teacher> teachers = new HashMap<>();

    public void addClassroom(String id, String name) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Classroom ID required");
        if (classrooms.containsKey(id)) throw new IllegalArgumentException("Classroom already exists");
        classrooms.put(id, new Classroom(id, name));
    }

    public void removeClassroom(String id) {
        if (classrooms.remove(id) == null) throw new IllegalArgumentException("Classroom not found");
    }

    public Classroom getClassroom(String id) {
        Classroom c = classrooms.get(id);
        if (c == null) throw new IllegalArgumentException("Classroom not found");
        return c;
    }

    public Collection<Classroom> listClassrooms() {
        return Collections.unmodifiableCollection(classrooms.values());
    }

    public void addTeacher(String teacherId, String name) {
        if (teacherId == null || teacherId.isEmpty()) throw new IllegalArgumentException("Teacher ID required");
        if (teachers.containsKey(teacherId)) throw new IllegalArgumentException("Teacher already exists");
        teachers.put(teacherId, new Teacher(teacherId, name));
    }

    public Teacher getTeacher(String teacherId) {
        Teacher t = teachers.get(teacherId);
        if (t == null) throw new IllegalArgumentException("Teacher not found");
        return t;
    }

    public Collection<Teacher> listTeachers() {
        return Collections.unmodifiableCollection(teachers.values());
    }

    public void assignTeacherToClass(String teacherId, String classId) {
        Teacher t = getTeacher(teacherId);
        Classroom c = getClassroom(classId);
        c.setTeacher(t);
    }
}