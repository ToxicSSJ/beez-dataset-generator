package generator;

import javafx.geometry.Point3D;

import java.util.List;

public interface IGenerator {

    public boolean make(boolean print);

    public double newX();

    public double newY();

    public double newZ();

    public List<Point3D> getBees();

}
