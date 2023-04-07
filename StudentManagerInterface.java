public class StudentManagerInterface {
    private StudentsData students;
    private LecturerData lecturers;
    private CoursesData courses;

    public StudentManagerInterface() {
        this.students = new StudentsData();
        this.lecturers = new LecturerData();
        this.courses = new CoursesData();
    }

    public boolean loadData() {
        //TODO
        return false;
    }

    public boolean storeData() {
        //TODO
        return false;
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
    
}
