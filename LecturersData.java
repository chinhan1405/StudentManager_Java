import java.util.ArrayList;

public class LecturersData extends ObjData<Lecturer> implements IPeopleData<Lecturer> {

    public ArrayList<Lecturer> findByName(String name) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        for (String id : getData().keySet()) {
            if (getData().get(id).getName().equals(name)) {
                lecturers.add(getData().get(id));
            }
        }
        return lecturers;
    }

    public ArrayList<Lecturer> findByYearOfBirth(int yearOfBirth) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        for (String id : getData().keySet()) {
            if (getData().get(id).getYearOfBirth() == yearOfBirth) {
                lecturers.add(getData().get(id));
            }
        }
        return lecturers;
    }

    public ArrayList<Lecturer> findByEmail(String email) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        for (String id : getData().keySet()) {
            if (getData().get(id).getEmail() == email) {
                lecturers.add(getData().get(id));
            }
        }
        return lecturers;
    }

    public ArrayList<Lecturer> findByStudent(Student student) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        for (Course course : student.getJoinedCourses()) {
            if (!lecturers.contains(course.getLecturer()))
                lecturers.add(course.getLecturer());
        }
        return lecturers;
    }
}
