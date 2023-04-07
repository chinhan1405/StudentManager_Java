import java.util.ArrayList;

public class Grade {
    private Course course;
    private ArrayList<Pair> points;

    public Grade(Course course) {
        this.course = course;
        this.points = new ArrayList<>();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Pair> getPoints() {
        return points;
    }

    public void addPoint(float point, float coefficient) {
        this.points.add(new Pair(point, coefficient));
    }

    public void removePoint(int index) {
        this.points.remove(index);
    }

    public float average() {
        float sum = 0, sumCoef = 0;
        for (Pair pair : this.points) {
            sum += pair.point * pair.coefficient;
            sumCoef += pair.coefficient;
        }
        return sum/sumCoef;
    }

    public static void main(String[] args) {
        Course course = new Course("12", "123", "131231231414123");
        Grade grade = new Grade(course);
        grade.addPoint(8.0f, 0.3f);
        grade.addPoint(7f, 0.7f);
        grade.addPoint(5f, 0.2f);
        System.out.print(grade.average());
    }
}

class Pair {
    public float point;
    public float coefficient;

    public Pair(float point, float coefficient) {
        this.point = point;
        this.coefficient = coefficient;
    }
}
