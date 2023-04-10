package StudentManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sqlite {
    private StudentsData students;
    private LecturersData lecturers;
    private CoursesData courses;
    private Connection connection;
    private Statement statement;
    
    public Sqlite(String url, StudentsData students, LecturersData lecturers, CoursesData courses) {
        this.students = students;
        this.lecturers = lecturers;
        this.courses = courses;
        try {
            Class.forName("org.sqlite.JDBC");
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(url);
            this.statement = connection.createStatement();
        } 
        catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public boolean executeNoQuery(String executeString) {
        try {
            this.statement.execute(executeString);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public ResultSet executeQuery(String executeString) {
        try {
            ResultSet result = this.statement.executeQuery(executeString);
            return result;
        }
        catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<String> queryColumn(String executeString, String collumnLabel) {
        ArrayList<String> queryData = new ArrayList<>();
        ResultSet result = executeQuery(executeString);
        try {
            while (result.next()) {
                queryData.add(result.getString(collumnLabel));
            }
            return queryData;
        }
        catch (SQLException e) {
            return queryData;
        }
    }

    public boolean createDatabase() {
        for (String name : queryColumn("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;", "name")) {
            executeNoQuery("DROP TABLE IF EXISTS " + name + ";");
        }
        String creatingStudentsTableStatement = "CREATE TABLE IF NOT EXISTS students (\n"  
        + " id text PRIMARY KEY,\n"  
        + " name text NOT NULL,\n"  
        + " yearOfBirth int,\n"  
        + " email text\n"  
        + ");";
        String creatingLecturersTableStatement = "CREATE TABLE IF NOT EXISTS lecturers (\n"  
        + " id text PRIMARY KEY,\n"  
        + " name text NOT NULL,\n"  
        + " yearOfBirth int,\n"  
        + " email text\n"  
        + ");";
        String creatingCoursesTableStatement = "CREATE TABLE IF NOT EXISTS courses (\n"  
        + " id text PRIMARY KEY,\n"  
        + " name text NOT NULL,\n"  
        + " description text,\n"  
        + " lecturerId text\n"  
        + ");";
        String creatingEnrolledCourseTableStatement = "CREATE TABLE IF NOT EXISTS enrolledCourses (\n"
        + " studentId text NOT NULL,\n"  
        + " courseId text NOT NULL,\n"  
        + " finished BOOLEAN\n"  
        + ");";
        String creatingGradesTableStatement = "CREATE TABLE IF NOT EXISTS grades (\n"
        + " studentId text NOT NULL,\n"  
        + " courseId text NOT NULL,\n"  
        + " point REAL,\n"  
        + " coefficient REAL\n"  
        + ");";
        if (!this.executeNoQuery(creatingStudentsTableStatement)) return false;
        if (!this.executeNoQuery(creatingLecturersTableStatement)) return false;
        if (!this.executeNoQuery(creatingCoursesTableStatement)) return false;
        if (!this.executeNoQuery(creatingEnrolledCourseTableStatement)) return false;
        if (!this.executeNoQuery(creatingGradesTableStatement)) return false;
        return true;
    }

    public boolean insertStudentInformation(String id, String name, int yearOfBirth, String email) {
        try {
            String insertString = "INSERT INTO students (id, name, yearOfBirth, email) VALUES (?, ?, ?, ?)";
            PreparedStatement prepare = this.connection.prepareStatement(insertString);
            prepare.setString(1, id);
            prepare.setString(2, name);
            prepare.setInt(3, yearOfBirth);
            prepare.setString(4, email);
            prepare.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean storeStudentsInformation() {
        boolean noError = true;
        for (Student student : students.getDataAsList()) {
            noError = noError && insertStudentInformation(student.getId(), student.getName(), student.getYearOfBirth(), student.getEmail());
        }
        return noError;
    }

    public boolean loadStudentsInformation() {
        try {
            String selectString = "SELECT id, name, yearOfBirth, email FROM students";
            ResultSet result = executeQuery(selectString);
            if (result != null) {
                while (result.next()) {
                    String id = result.getString("id");
                    String name = result.getString("name");
                    int yearOfBirth = result.getInt("yearOfBirth");
                    String email = result.getString("email");
                    students.add(id, new Student(id, name, yearOfBirth, email));
                }
            }
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean insertLecturerInformation(String id, String name, int yearOfBirth, String email) {
        try {
            String insertString = "INSERT INTO lecturers (id, name, yearOfBirth, email) VALUES (?, ?, ?, ?)";
            PreparedStatement prepare = this.connection.prepareStatement(insertString);
            prepare.setString(1, id);
            prepare.setString(2, name);
            prepare.setInt(3, yearOfBirth);
            prepare.setString(4, email);
            prepare.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean storeLecturersInformation() {
        boolean noError = true;
        for (Lecturer lecturer : lecturers.getDataAsList()) {
            noError = noError && insertLecturerInformation(lecturer.getId(), lecturer.getName(), lecturer.getYearOfBirth(), lecturer.getEmail());
        }
        return noError;
    }

    public boolean loadLecturersInformation() {
        try {
            String selectString = "SELECT id, name, yearOfBirth, email FROM lecturers";
            ResultSet result = executeQuery(selectString);
            if (result != null) {
                while (result.next()) {
                    String id = result.getString("id");
                    String name = result.getString("name");
                    int yearOfBirth = result.getInt("yearOfBirth");
                    String email = result.getString("email");
                    lecturers.add(id, new Lecturer(id, name, yearOfBirth, email));
                }
            }
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean insertCourseInformation(String id, String name, String description, String lecturerId) {
        try {
            String insertString = "INSERT INTO courses (id, name, description, lecturerId) VALUES (?, ?, ?, ?)";
            PreparedStatement prepare = this.connection.prepareStatement(insertString);
            prepare.setString(1, id);
            prepare.setString(2, name);
            prepare.setString(3, description);
            prepare.setString(4, lecturerId);
            prepare.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean storeCoursesInformation() {
        boolean noError = true;
        for (Course course : courses.getDataAsList()) {
            noError = noError && insertCourseInformation(course.getId(), course.getName(), course.getDescription(), course.getLecturer().getId());
        }
        return noError;
    }

    public boolean loadCoursesInformation() {
        try {
            String selectString = "SELECT id, name, description, lecturerId FROM courses";
            ResultSet result = executeQuery(selectString);
            if (result != null) {
                while (result.next()) {
                    String id = result.getString("id");
                    String name = result.getString("name");
                    String description = result.getString("description");
                    String lecturerId = result.getString("lecturerId");
                    Course newCourse = new Course(id, name, description);
                    if (lecturers.contain(lecturerId)) {
                        newCourse.setLecturer(lecturers.get(lecturerId));
                    }
                    courses.add(id, newCourse);
                }
            }
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean insertEnrolledCourseInformation(String studentId, String courseId, boolean finished) {
        try {
            String insertString = "INSERT INTO enrolledCourses (studentId, courseId, finished) VALUES (?, ?, ?)";
            PreparedStatement prepare = this.connection.prepareStatement(insertString);
            prepare.setString(1, studentId);
            prepare.setString(2, courseId);
            prepare.setBoolean(3, finished);
            prepare.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean storeEnrolledCoursesInformation() {
        boolean noError = true;
        for (Course course : courses.getDataAsList()) {
            for (Student student : course.getJoinedStudents()) {
                Boolean finished = course.isFinished();
                noError = noError && insertEnrolledCourseInformation(student.getId(), course.getId(), finished);
            }
            
        }
        return noError;
    }

    public boolean loadEnrolledCoursesInformation() {
        try {
            String selectString = "SELECT studentId, courseId FROM enrolledCourses";
            ResultSet result = executeQuery(selectString);
            if (result != null) {
                while (result.next()) {
                    String studentId = result.getString("studentId");
                    String courseId = result.getString("courseId");
                    students.get(studentId).joinCourse(courses.get(courseId));
                }
            }
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean loadFinishedCoursesInformation() {
        try {
            String selectString = "SELECT DISTINCT courseId, finished FROM enrolledCourses";
            ResultSet result = executeQuery(selectString);
            if (result != null) {
                while (result.next()) {
                    String courseId = result.getString("courseId");
                    boolean finished = result.getBoolean("finished");
                    if (finished) courses.get(courseId).finishCourse();
                }
            }
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean insertGradesInformation(String studentId, String courseId, float point, float coefficient) {
        try {
            String insertString = "INSERT INTO grades (studentId, courseId, point, coefficient) VALUES (?, ?, ?, ?)";
            PreparedStatement prepare = this.connection.prepareStatement(insertString);
            prepare.setString(1, studentId);
            prepare.setString(2, courseId);
            prepare.setFloat(3, point);
            prepare.setFloat(4, coefficient);
            prepare.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public boolean storeGradesInformation() {
        boolean noError = true;
        for (Course course : courses.getDataAsList()) {
            for (Student student : course.getJoinedStudents()) {
                for (Pair pair : student.getGrade(course).getPoints()) {
                    noError = noError && insertGradesInformation(student.getId(), course.getId(), pair.point, pair.coefficient);
                }
            }
        }
        return noError;
    }

    public boolean loadGradesInformation() {
        try {
            String selectString = "SELECT studentId, courseId, point, coefficient FROM grades";
            ResultSet result = executeQuery(selectString);
            if (result != null) {
                while (result.next()) {
                    String studentId = result.getString("studentId");
                    String courseId = result.getString("courseId");
                    float point = result.getFloat("point");
                    float coefficient = result.getFloat("coefficient");
                    students.get(studentId).addGrade(courses.get(courseId), point, coefficient);
                }
            }
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }
}
