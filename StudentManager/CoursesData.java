package StudentManager;
import java.util.ArrayList;

public class CoursesData extends ObjData<Course> {
    
    public ArrayList<Course> findByName(String name) {
        ArrayList<Course> courses = new ArrayList<>();
        for (String id : getData().keySet()) {
            if (getData().get(id).getName().equals(name)) {
                courses.add(getData().get(id));
            }
        }
        return courses;
    }

    public ArrayList<Course> findByLecturer(Lecturer lecturer) {
        ArrayList<Course> courses = new ArrayList<>();
        courses.addAll(lecturer.getJoinedCourses());
        courses.addAll(lecturer.getFinishedCourses());
        return courses;
    }
}
