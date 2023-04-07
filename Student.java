import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person {
    private ArrayList<Course> joinedCourses = new ArrayList<>();
    private ArrayList<Course> finishedCourses = new ArrayList<>();
    private HashMap<Course, Grade> grades = new HashMap<>();

    public Student(String id, String name, int yearOfBirth, String email) {
        setId(id);
        setName(name);
        setYearOfBirth(yearOfBirth);
        setEmail(email);
    }

    public Student() {
        this("", "", 0, "");
    }

    public HashMap<Course, Grade> getGrades() {
        return grades;
    }

    public ArrayList<Course> getJoinedCourses() {
        return joinedCourses;
    }

    public ArrayList<Course> getFinishedCourses() {
        return finishedCourses;
    }

    public void joinCourse(Course course) {
        this.joinedCourses.add(course);
        course.getJoinedStudents().add(this);
        this.grades.put(course, new Grade(course));
    }

    public void exitCourse(Course course) {
        this.joinedCourses.remove(course);
        course.getJoinedStudents().remove(this);
        grades.remove(course);
    }

    public static void main(String[] args) {
        Course course = new Course("123", "233dfak", "lsflksdfjlksflsjf");
        Student student = new Student("asd", "aa", 123, "dad");
        Lecturer lecturer = new Lecturer("sdf", "dsfa", 123, "dalkd");
        course.setLecturer(lecturer);
        student.joinCourse(course);
        student.joinCourse(course);
        student.joinCourse(course);
        student.joinCourse(course);
        student.exitCourse(course);
        for (Student st : course.getJoinedStudents()) {
            System.out.println(st.getName() + "  " + st.getEmail());
        }
    }
}
