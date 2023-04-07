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

    public ArrayList<Course> getJoinedCourses() {
        return joinedCourses;
    }

    public ArrayList<Course> getFinishedCourses() {
        return finishedCourses;
    }

    public void joinCourse(Course course) {
        this.joinedCourses.add(course);
    }
}
