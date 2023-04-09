package StudentManager;
import java.util.ArrayList;

public class StudentManagerInterface {
    private StudentsData students;
    private LecturersData lecturers;
    private CoursesData courses;
    private Sqlite sqlite;

    public StudentManagerInterface() {
        this.students = new StudentsData();
        this.lecturers = new LecturersData();
        this.courses = new CoursesData();
        this.sqlite = new Sqlite("jdbc:sqlite:test.db", students, lecturers, courses);
    }

    public boolean loadData() {
        boolean noError = true;
        noError = noError && sqlite.loadStudentsInformation();
        noError = noError && sqlite.loadLecturersInformation();
        noError = noError && sqlite.loadCoursesInformation();
        noError = noError && sqlite.loadEnrolledCoursesInformation();
        noError = noError && sqlite.loadGradesInformation();
        noError = noError && sqlite.loadFinishedCoursesInformation();
        return noError;
    }

    public boolean storeData() {
        boolean noError = true;
        noError = noError && sqlite.storeStudentsInformation();
        noError = noError && sqlite.storeLecturersInformation();
        noError = noError && sqlite.storeCoursesInformation();
        noError = noError && sqlite.storeEnrolledCoursesInformation();
        noError = noError && sqlite.storeGradesInformation();
        return noError;
    }

    public boolean createData() {
        boolean noError = true;
        noError = noError && sqlite.createDatabase();
        return noError;
    }

    public boolean addStudent(String id, String name, int yearOfBirth, String email) {
        if (!students.contain(id)) {
            Student student = new Student(id, name, yearOfBirth, email);
            students.add(id, student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(String id) {
        if (students.contain(id)) {
            students.remove(id);
            return true;
        }
        return false;
    }

    public boolean updateStudent(String id, String name, int yearOfBirth, String email) {
        if (students.contain(id)) {
            Student student = students.get(id);
            if (!name.equals("")) {
                student.setName(name);
            }
            if (yearOfBirth != 0) {
                student.setYearOfBirth(yearOfBirth);
            }
            if (!email.equals("")) {
                student.setEmail(email);
            }
            return true;
        }
        return false;
    }

    public boolean addLecturer(String id, String name, int yearOfBirth, String email) {
        if (!lecturers.contain(id)) {
            Lecturer lecturer = new Lecturer(id, name, yearOfBirth, email);
            lecturers.add(id, lecturer);
            return true;
        }
        return false;
    }

    public boolean removeLecturer(String id) {
        if (lecturers.contain(id)) {
            lecturers.remove(id);
            return true;
        }
        return false;
    }
    
    public boolean updateLecturer(String id, String name, int yearOfBirth, String email) {
        if (lecturers.contain(id)) {
            Lecturer lecturer = lecturers.get(id);
            if (!name.equals("")) {
                lecturer.setName(name);
            }
            if (yearOfBirth != 0) {
                lecturer.setYearOfBirth(yearOfBirth);
            }
            if (!email.equals("")) {
                lecturer.setEmail(email);
            }
            return true;
        }
        return false;
    }

    public boolean addCourse(String id, String name, String description) {
        if (!courses.contain(id)) {
            Course course = new Course(id, name, description);
            courses.add(id, course);
            return true;
        }
        return false;
    }

    public boolean removeCourse(String id) {
        if (courses.contain(id)) {
            courses.remove(id);
            return true;
        }
        return false;
    }

    public boolean updateCourse(String id, String name, String description) {
        if (courses.contain(id)) {
            Course course = courses.get(id);
            if (!name.equals("")) {
                course.setName(name);
            }
            
            if (!description.equals("")) {
                course.setDescription(description);
            }
            return true;
        }
        return false;
    }

    public boolean enrollNewCourseForStudent(String studentId, String courseId) {
        if (students.contain(studentId) && courses.contain(courseId)) {
            students.get(studentId).joinCourse(courses.get(courseId));
            return true;
        }
        return false;
    }

    public boolean removeStudentfromCourse(String studentId, String courseId) {
        if (students.contain(studentId) && courses.contain(courseId)) {
            students.get(studentId).exitCourse(courses.get(courseId));
            return true;
        }
        return false;
    }

    public boolean setNewLecturerToCourse(String lecturerId, String courseId) {
        if (lecturers.contain(lecturerId) && courses.contain(courseId)) {
            courses.get(courseId).setLecturer(lecturers.get(lecturerId));
            return true;
        }
        return false;
    }

    public boolean finishCourse(String id) {
        if (courses.contain(id)) {
            courses.get(id).finishCourse();
            return true;
        }
        return false;
    }

    public boolean addGradeForStudent(String studentId, String courseId, float point, float coefficient) {
        if (students.contain(studentId) && courses.contain(courseId)) {
            Student student = students.get(courseId);
            Course course = courses.get(courseId);
            if (student.isInCourse(course)) {
                student.addGrade(course, point, coefficient);
                return true;
            }
        }
        return false;
    }

    public boolean deleteGradeForStudent(String studentId, String courseId, int index) {
        if (students.contain(studentId) && courses.contain(courseId)) {
            Student student = students.get(courseId);
            Course course = courses.get(courseId);
            if (student.isInCourse(course)) {
                student.deleteGrade(course, index);
                return true;
            }
        }
        return false;
    }

    public Object[][] getAllStudents() {
        ArrayList<Student> temp = students.getDataAsList();
        Object[][] dataArray = new Object[temp.size()][4];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getYearOfBirth();
            dataArray[i][3] = temp.get(i).getEmail();
        }
        return dataArray;
    }

    public Object[][] findStudentByName(String name) {
        ArrayList<Student> temp = students.findByName(name);
        Object[][] dataArray = new Object[temp.size()][4];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getYearOfBirth();
            dataArray[i][3] = temp.get(i).getEmail();
        }
        return dataArray;
    }

    public Object[][] findStudentByYearOfBirth(int yearOfBirth) {
        ArrayList<Student> temp = students.findByYearOfBirth(yearOfBirth);
        Object[][] dataArray = new Object[temp.size()][4];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getYearOfBirth();
            dataArray[i][3] = temp.get(i).getEmail();
        }
        return dataArray;
    }

    public Object[][] findStudentByEmail(String email) {
        ArrayList<Student> temp = students.findByEmail(email);
        Object[][] dataArray = new Object[temp.size()][4];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getYearOfBirth();
            dataArray[i][3] = temp.get(i).getEmail();
        }
        return dataArray;
    }

    public Object[][] findStudentByLecturer(String lecturerId) {
        if (lecturers.contain(lecturerId)) {
            ArrayList<Student> temp = students.findByLecturer(lecturers.get(lecturerId));
            Object[][] dataArray = new Object[temp.size()][4];
            for (int i=0; i<temp.size(); i++) {
                dataArray[i][0] = temp.get(i).getId();
                dataArray[i][1] = temp.get(i).getName();
                dataArray[i][2] = temp.get(i).getYearOfBirth();
                dataArray[i][3] = temp.get(i).getEmail();
            }
            return dataArray;
        }
        else {
            return new Object[0][4];
        }
        
    }

    public Object[][] findStudentByCourse(String courseId) {
        if (courses.contain(courseId)) {
            ArrayList<Student> temp = courses.get(courseId).getJoinedStudents();
            Object[][] dataArray = new Object[temp.size()][4];
            for (int i=0; i<temp.size(); i++) {
                dataArray[i][0] = temp.get(i).getId();
                dataArray[i][1] = temp.get(i).getName();
                dataArray[i][2] = temp.get(i).getYearOfBirth();
                dataArray[i][3] = temp.get(i).getEmail();
            }
            return dataArray;
        }
        else {
            return new Object[0][4];
        }
    }

    public Object[][] getAllLecturers() {
        ArrayList<Lecturer> temp = lecturers.getDataAsList();
        Object[][] dataArray = new Object[temp.size()][4];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getYearOfBirth();
            dataArray[i][3] = temp.get(i).getEmail();
        }
        return dataArray;
    }

    public Object[][] findLecturerByName(String name) {
        ArrayList<Lecturer> temp = lecturers.findByName(name);
        Object[][] dataArray = new Object[temp.size()][4];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getYearOfBirth();
            dataArray[i][3] = temp.get(i).getEmail();
        }
        return dataArray;
    }

    public Object[][] findLecturerByYearOfBirth(int yearOfBirth) {
        ArrayList<Lecturer> temp = lecturers.findByYearOfBirth(yearOfBirth);
        Object[][] dataArray = new Object[temp.size()][4];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getYearOfBirth();
            dataArray[i][3] = temp.get(i).getEmail();
        }
        return dataArray;
    }

    public Object[][] findLecturerByEmail(String email) {
        ArrayList<Lecturer> temp = lecturers.findByEmail(email);
        Object[][] dataArray = new Object[temp.size()][4];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getYearOfBirth();
            dataArray[i][3] = temp.get(i).getEmail();
        }
        return dataArray;
    }

    public Object[][] findLecturerByStudent(String studentId) {
        if (students.contain(studentId)) {
            ArrayList<Lecturer> temp = lecturers.findByStudent(students.get(studentId));
            Object[][] dataArray = new Object[temp.size()][4];
            for (int i=0; i<temp.size(); i++) {
                dataArray[i][0] = temp.get(i).getId();
                dataArray[i][1] = temp.get(i).getName();
                dataArray[i][2] = temp.get(i).getYearOfBirth();
                dataArray[i][3] = temp.get(i).getEmail();
            }
            return dataArray;
        }
        return new Object[0][4];
    }

    public ArrayList<Course> getAllCourses() {
        return courses.getDataAsList();
    }

    public ArrayList<Course> findCourseByName(String name) {
        return courses.findByName(name);
    }

    public ArrayList<Course> findCourseByLecturer(String lecturerId) {
        if (lecturers.contain(lecturerId)) {
            return courses.findByLecturer(lecturers.get(lecturerId));
        }
        return new ArrayList<>();
    }

    public Student getStudent(String id) {
        if (students.contain(id)) {
            return students.get(id);
        }
        return null;
    }

    public Lecturer getLecturer(String id) {
        if (lecturers.contain(id)) {
            return lecturers.get(id);
        }
        return null;
    }

    public Course getCourse(String id) {
        if (courses.contain(id)) {
            return courses.get(id);
        }
        return null;
    }

    public boolean isStudentInCourse(String studentId, String courseId) {
        if (students.contain(studentId) && courses.contain(courseId)) {
                return students.get(studentId).isInCourse(courses.get(courseId));
            }
        return false;
    }

    public ArrayList<Pair> getStudentGradeInCourse(String studentId, String courseId) {
        if (students.contain(studentId) && courses.contain(courseId)) {
            return students.get(studentId).getGrade(courses.get(courseId)).getPoints();
        }
        return null;
    }

    public float getStudentAllAverageGrade(String studentId) {
        if (students.contain(studentId)) {
            return students.get(studentId).getAllAverage();
        }
        return 0;
    }

    public void testListLecturer (ArrayList<Lecturer> objs) {
        for (Person obj : objs) {
            System.out.println(obj.getEmail());
        }
    }
}
