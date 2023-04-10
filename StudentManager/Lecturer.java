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

    public void exitAllCourse() {
        for (Course course : getJoinedCourses()) {
            this.joinedCourses.remove(course);
            course.setLecturer(new Lecturer());
        }
        for (Course course : getFinishedCourses()) {
            this.joinedCourses.remove(course);
            course.setLecturer(new Lecturer());
        }
    }

    public ArrayList<Course> getJoinedCourses() {
        return joinedCourses;
    }

    public ArrayList<Course> getFinishedCourses() {
        return finishedCourses;
    }

    public void joinCourse(Course course) {
        if (!this.joinedCourses.contains(course)) {
            this.joinedCourses.add(course);
            course.setLecturer(this);
        }
    }
}
