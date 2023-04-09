package StudentManager;
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

    public void deletePoint(int index) {
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
}

class Pair {
    public float point;
    public float coefficient;

    public Pair(float point, float coefficient) {
        this.point = point;
        this.coefficient = coefficient;
    }
}
