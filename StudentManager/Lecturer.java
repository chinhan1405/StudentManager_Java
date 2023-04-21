package StudentManager;
import java.util.ArrayList;

public class Lecturer extends Person{
    private ArrayList<Course> joinedCourses = new ArrayList<>();
    private ArrayList<Course> finishedCourses = new ArrayList<>();

    public Lecturer(String id, String name, int yearOfBirth, String email) {
        setId(id);
        setName(name);
        setYearOfBirth(yearOfBirth);
        setEmail(email);
    }

    public Lecturer() {
        this("", "", 0, "");
    }

    public void exitAllCourses() {
        for (Course course : getJoinedCourses()) {
            course.setLecturer(new Lecturer());
        }
        for (Course course : getFinishedCourses()) {
            course.setLecturer(new Lecturer());
        }
    }

    public ArrayList<Course> getJoinedCourses() {
        return joinedCourses;
    }

    public ArrayList<Course> getFinishedCourses() {
        return finishedCourses;
    }
}
