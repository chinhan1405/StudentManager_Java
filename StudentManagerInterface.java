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
            Student student = new Student(id, name, yearOfBirth, email);
            students.update(id, student);
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
            Lecturer lecturer = new Lecturer(id, name, yearOfBirth, email);
            lecturers.update(id, lecturer);
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
            Course course = new Course(id, name, description);
            courses.update(id, course);
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

    public ArrayList<Student> getAllStudents() {
        return students.getDataAsList();
    }

    public ArrayList<Student> findStudentByName(String name) {
        return students.findByName(name);
    }

    public ArrayList<Student> findStudentByYearOfBirth(int yearOfBirth) {
        return students.findByYearOfBirth(yearOfBirth);
    }

    public ArrayList<Student> findStudentByEmail(String email) {
        return students.findByEmail(email);
    }

    public ArrayList<Student> findStudentByLecturer(String lecturerId) {
        if (lecturers.contain(lecturerId)) {
            return students.findByLecturer(lecturers.get(lecturerId));
        }
        return new ArrayList<>();
    }

    public ArrayList<Lecturer> getAllLecturers() {
        return lecturers.getDataAsList();
    }

    public ArrayList<Lecturer> findLecturerByName(String name) {
        return lecturers.findByName(name);
    }

    public ArrayList<Lecturer> findLecturerByYearOfBirth(int yearOfBirth) {
        return lecturers.findByYearOfBirth(yearOfBirth);
    }

    public ArrayList<Lecturer> findLecturerByEmail(String email) {
        return lecturers.findByEmail(email);
    }

    public ArrayList<Lecturer> findLecturerByStudent(String studentId) {
        if (students.contain(studentId)) {
            return lecturers.findByStudent(students.get(studentId));
        }
        return new ArrayList<>();
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

    public static void main(String[] args) {
        StudentManagerInterface sm = new StudentManagerInterface();
        sm.createData();
        // sm.addStudent("123", "Hoang Chi Nhan", 2003, "hoangchinhankl@gmail.com");
        // sm.addStudent("124", "Tran Dai Thanh", 2003, "gbvietnamese@gmail.com");
        // sm.addCourse("123", "Math", "Logic and Numbers");
        // sm.addCourse("124", "Physics", "Magiccc");
        // sm.addLecturer("123", "Jack", 1990, "jack@gmail.com");
        // sm.addLecturer("124", "Adam", 1988, "adam@gmail.com");
        // sm.setNewLecturerToCourse("123", "123");
        // sm.enrollNewCourseForStudent("123", "123");
        // sm.addGradeForStudent("123", "123", 9f, 0.3f);
        // sm.addGradeForStudent("123", "123", 7f, 0.7f);
        // sm.testListLecturer(sm.getAllLecturers());
    }
}
