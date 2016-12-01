package Ancheta017.java;

/**
 * This class holds the radius of a circle.
 */
public class Circle implements Shape {
    private int radius;

    /**
     * Constructor with one argument
     * @param value an integer value
     */
    public Circle(int value){
        radius = value;
    }

    /**
     * Compute perimeter of a Circle object.
     * @return double
     */
    public double getPerimeter(){
        return 2 * Math.PI * radius ;
    }

    @Override
    public String toString(){
        return "Circle {r=" + radius +"} has perimeter: "  + String.format("%.2f",getPerimeter());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + radius;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle test = (Circle) o;
        if (radius != test.radius) return false;
        return true;
    }
}
