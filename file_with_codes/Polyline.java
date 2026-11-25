import java.util.Arrays;

public class Polyline {

    private final Point[] points;

    public Polyline(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Массив точек не должен быть null");
        }
        this.points = points.clone();
    }

    public Point[] getPoints() {
        return points.clone();
    }

    @Override
    public String toString() {
        return "Линия " + Arrays.toString(points);
    }
}
