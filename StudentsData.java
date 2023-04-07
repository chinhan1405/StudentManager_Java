import java.util.ArrayList;

public class StudentsData extends ObjData<Student> {

    public ArrayList<Student> findByName(String name) {
        ArrayList<Student> students = new ArrayList<>();
        for (String id : getData().keySet()) {
            if (getData().get(id).getName().equals(name)) {
                students.add(getData().get(id));
            }
        }
        return students;
    }

    public ArrayList<Student> findByYearOfBirth(int yearOfBirth) {
        ArrayList<Student> students = new ArrayList<>();
        for (String id : getData().keySet()) {
            if (getData().get(id).getYearOfBirth() == yearOfBirth) {
                students.add(getData().get(id));
            }
        }
        return students;
    }

    public ArrayList<Student> findByEmail(String email) {
        ArrayList<Student> students = new ArrayList<>();
        for (String id : getData().keySet()) {
            if (getData().get(id).getEmail().equals(email)) {
                students.add(getData().get(id));
            }
        }
        return students;
    }

    public ArrayList<Student> findByLecturer(Lecturer lecturer) {
        ArrayList<Student> students = new ArrayList<>();
        for (String id : getData().keySet()) {
            for (Course course : getData().get(id).getJoinedCourses()) {
                if (course.getLecturer().equals(lecturer)) {
                    students.add(getData().get(id));
                }
            }
        }
        return students;
    }
}
