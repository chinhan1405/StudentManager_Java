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

    public Object[][] getAllCourses() {
        ArrayList<Course> temp = courses.getDataAsList();
        Object[][] dataArray = new Object[temp.size()][5];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getDescription();
            Lecturer lecturer = temp.get(i).getLecturer();
            if (lecturer == null) {
                dataArray[i][3] = "";
            }
            else {
                dataArray[i][3] = lecturer.getId();
            }
            dataArray[i][4] = temp.get(i).isFinished();
        }
        return dataArray;
    }

    public Object[][] findCourseByName(String name) {
        ArrayList<Course> temp = courses.findByName(name);
        Object[][] dataArray = new Object[temp.size()][5];
        for (int i=0; i<temp.size(); i++) {
            dataArray[i][0] = temp.get(i).getId();
            dataArray[i][1] = temp.get(i).getName();
            dataArray[i][2] = temp.get(i).getDescription();
            Lecturer lecturer = temp.get(i).getLecturer();
            if (lecturer == null) {
                dataArray[i][3] = "";
            }
            else {
                dataArray[i][3] = lecturer.getId();
            }
            dataArray[i][4] = temp.get(i).isFinished();
        }
        return dataArray;
    }

    public Object[][] findCourseByLecturer(String lecturerId) {
        if (lecturers.contain(lecturerId)) {
            ArrayList<Course> temp = courses.findByLecturer(lecturers.get(lecturerId));
            Object[][] dataArray = new Object[temp.size()][5];
            for (int i=0; i<temp.size(); i++) {
                dataArray[i][0] = temp.get(i).getId();
                dataArray[i][1] = temp.get(i).getName();
                dataArray[i][2] = temp.get(i).getDescription();
                Lecturer lecturer = temp.get(i).getLecturer();
                if (lecturer == null) {
                    dataArray[i][3] = "";
                }
                else {
                    dataArray[i][3] = lecturer.getId();
                }
                dataArray[i][4] = temp.get(i).isFinished();
            }
            return dataArray;
        }
        return new Object[0][5];
    }

    public Object[][] findCourseByStudent(String studentId) {
        if (students.contain(studentId)) {
            ArrayList<Course> temp = students.get(studentId).getAllCourse();
            Object[][] dataArray = new Object[temp.size()][5];
            for (int i=0; i<temp.size(); i++) {
                dataArray[i][0] = temp.get(i).getId();
                dataArray[i][1] = temp.get(i).getName();
                dataArray[i][2] = temp.get(i).getDescription();
                Lecturer lecturer = temp.get(i).getLecturer();
                if (lecturer == null) {
                    dataArray[i][3] = "";
                }
                else {
                    dataArray[i][3] = lecturer.getId();
                }
                dataArray[i][4] = temp.get(i).isFinished();
            }
            return dataArray;
        }
        return new Object[0][5];
    }

    public Object[][] getAllEnrolledCourses() {
        ArrayList<Student> studentsTemp = new ArrayList<>();
        ArrayList<Course> coursesTemp = new ArrayList<>();
        for (Student student : students.getDataAsList()) {
            for (Course course : student.getAllCourse()) {
                studentsTemp.add(student);
                coursesTemp.add(course);
            }
        }
        Object[][] dataArray = new Object[coursesTemp.size()][3];
        for (int i=0; i<coursesTemp.size(); i++) {
            dataArray[i][0] = studentsTemp.get(i).getId();
            dataArray[i][1] = coursesTemp.get(i).getId();
            dataArray[i][2] = coursesTemp.get(i).isFinished();
        }
        return dataArray;
    }

    public Object[][] findEnrolledCoursesByStudent(String studentId) {
        if (students.contain(studentId)) {
            Student student = students.get(studentId);
            ArrayList<Course> coursesTemp = student.getAllCourse();
            Object[][] dataArray = new Object[coursesTemp.size()][3];
            for (int i=0; i<coursesTemp.size(); i++) {
                dataArray[i][0] = student.getId();
                dataArray[i][1] = coursesTemp.get(i).getId();
                dataArray[i][2] = coursesTemp.get(i).isFinished();
            }
            return dataArray;
        }
        return new Object[0][3];
    }

    public Object[][] findEnrolledCoursesByCourse(String courseId) {
        if (courses.contain(courseId)) {
            Course course = courses.get(courseId);
            ArrayList<Student> studentsTemp = course.getJoinedStudents();
            Object[][] dataArray = new Object[studentsTemp.size()][3];
            for (int i=0; i<studentsTemp.size(); i++) {
                dataArray[i][0] = studentsTemp.get(i).getId();
                dataArray[i][1] = course.getId();
                dataArray[i][2] = course.isFinished();
            }
            return dataArray;
        }
        return new Object[0][3];
    }

    public boolean isStudentInCourse(String studentId, String courseId) {
        if (students.contain(studentId) && courses.contain(courseId)) {
                return students.get(studentId).isInCourse(courses.get(courseId));
            }
        return false;
    }

    public Object[][] getAllGrades() {
        ArrayList<Student> studentsTemp = new ArrayList<>();
        ArrayList<Course> coursesTemp = new ArrayList<>();
        ArrayList<Pair> pairsTemp = new ArrayList<>();
        for (Student student : students.getDataAsList()) {
            for (Grade grade : student.getGradeAsList()) {
                for (Pair pair : grade.getPoints()) {
                    studentsTemp.add(student);
                    coursesTemp.add(grade.getCourse());
                    pairsTemp.add(pair);
                }
            }
        }
        Object[][] dataArray = new Object[studentsTemp.size()][4];
        for (int i=0; i<studentsTemp.size(); i++) {
            dataArray[i][0] = studentsTemp.get(i).getId();
            dataArray[i][1] = coursesTemp.get(i).getId();
            dataArray[i][2] = pairsTemp.get(i).point;
            dataArray[i][3] = pairsTemp.get(i).coefficient;
        }
        return dataArray;
    }

    public Object[][] getStudentAllGrades(String studentId) {
        if (students.contain(studentId)) {
            Student student = students.get(studentId);
            ArrayList<Course> coursesTemp = new ArrayList<>();
            ArrayList<Pair> pairsTemp = new ArrayList<>();
            for (Grade grade : student.getGradeAsList()) {
                for (Pair pair : grade.getPoints()) {
                    coursesTemp.add(grade.getCourse());
                    pairsTemp.add(pair);
                }
            }
            Object[][] dataArray = new Object[coursesTemp.size()][4];
            for (int i=0; i<coursesTemp.size(); i++) {
                dataArray[i][0] = student.getId();
                dataArray[i][1] = coursesTemp.get(i).getId();
                dataArray[i][2] = pairsTemp.get(i).point;
                dataArray[i][3] = pairsTemp.get(i).coefficient;
            }
            return dataArray;
        }
        else {
            return new Object[0][4];
        }
    }

    public Object[][] getStudentAverage(String studentId) {
        if (students.contain(studentId)) {
            Student student = students.get(studentId);
            ArrayList<Grade> gradesTemp = student.getGradeAsList();
            Object[][] dataArray = new Object[gradesTemp.size()+1][4];
            dataArray[0][0] = student.getId();
            dataArray[0][1] = "";
            dataArray[0][2] = "All";
            dataArray[0][3] = student.getAllAverage();
            for (int i=0; i<gradesTemp.size(); i++) {
                dataArray[i+1][0] = student.getId();
                dataArray[i+1][1] = gradesTemp.get(i).getCourse().getId();
                dataArray[i+1][2] = gradesTemp.get(i).getCourse().getName();
                dataArray[i+1][3] = gradesTemp.get(i).average();
            }
            return dataArray;
        }
        else {
            return new Object[0][4];
        }
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

    public static void main(String[] args) {
        StudentManagerInterface smi = new StudentManagerInterface();
        smi.createData();
    }
}
