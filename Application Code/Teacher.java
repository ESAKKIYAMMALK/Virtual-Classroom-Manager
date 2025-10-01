public class Teacher {
    private final String teacherId;
    private final String name;

    public Teacher(String teacherId, String name) {
        if (teacherId == null || teacherId.isEmpty()) throw new IllegalArgumentException("Teacher ID cannot be empty");
        this.teacherId = teacherId;
        this.name = name == null ? "" : name;
    }

    public String getTeacherId() { return teacherId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Teacher[" + teacherId + ", " + name + "]";
    }
}