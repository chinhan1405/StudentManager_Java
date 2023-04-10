package StudentManager;
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

    public ArrayList<Grade> getGradeAsList() {
        ArrayList<Grade> gradesList = new ArrayList<>();
        for (Course key : this.grades.keySet()) {
            gradesList.add(grades.get(key));
        }
        return gradesList;
    }

    public Grade getGrade(Course course) {
        return this.grades.get(course);
    }

    public ArrayList<Course> getJoinedCourses() {
        return joinedCourses;
    }

    public ArrayList<Course> getFinishedCourses() {
        return finishedCourses;
    }

    public ArrayList<Course> getAllCourse() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.addAll(getJoinedCourses());
        courses.addAll(getFinishedCourses());
        return courses;
    }

    public void joinCourse(Course course) {
        if (!course.getJoinedStudents().contains(this)) {
            this.joinedCourses.add(course);
            course.getJoinedStudents().add(this);
            this.grades.put(course, new Grade(course));
        }
    }

    public void exitCourse(Course course) {
        if (course.getJoinedStudents().contains(this)) {
            this.joinedCourses.remove(course);
            course.getJoinedStudents().remove(this);
            grades.remove(course);
        }
    }

    public void exitAllCourse() {
        for (Course course : getJoinedCourses()) {
            this.joinedCourses.remove(course);
            course.getJoinedStudents().remove(this);
            grades.remove(course);
        }
        for (Course course : getFinishedCourses()) {
            this.joinedCourses.remove(course);
            course.getJoinedStudents().remove(this);
            grades.remove(course);
        }
    }

    public boolean isInCourse(Course course) {
        return joinedCourses.contains(course);
    }

    public void addGrade(Course course, float point, float coefficient) {
        this.getGrade(course).addPoint(point, coefficient);
    }

    public void deleteGrade(Course course, int index) {
        this.getGrade(course).deletePoint(index);
    }

    public float getAllAverage() {
        if (finishedCourses.size()==0) return 0;
        float sum = 0f;
        for (Course course : finishedCourses) {
            sum += grades.get(course).average();
        }
        return sum / (float) finishedCourses.size();
    }
}
