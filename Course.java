import java.util.ArrayList;

public class Course {
    private String id;
    private String name;
    private String description;
    private Lecturer lecturer;
    private ArrayList<Student> joinedStudents = new ArrayList<>();
    private boolean finished;

    public Course(String id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
        setLecturer(new Lecturer());
        this.finished = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void finishCourse(boolean finished) {
        this.finished = true;
        this.lecturer.getJoinedCourses().remove(this);
        this.lecturer.getFinishedCourses().add(this);
        for (Student student : getJoinedStudents()) {
            student.getJoinedCourses().remove(this);
            student.getFinishedCourses().add(this);
        }
    }

    public ArrayList<Student> getJoinedStudents() {
        return joinedStudents;
    }

}
