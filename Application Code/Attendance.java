import java.time.*;
public class Attendance {
    private final LocalDate date;
    private final boolean present;

    public Attendance(LocalDate date, boolean present) {
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        this.date = date;
        this.present = present;
    }

    public LocalDate getDate() { return date; }
    public boolean isPresent() { return present; }

    @Override
    public String toString() {
        return "Attendance[" + date + " : " + (present ? "Present" : "Absent") + "]";
    }
}