package Ancheta017.java;

/**
 * This class holds the length of the three sides of a triangle.
 */
public class Triangle implements Shape {
    private int[] side = new int[3];

    /**
     * Constructor with three arguments.
     * @param value1 an integer value
     * @param value2 an integer value
     * @param value3 an integer value
     */
    public Triangle(int value1, int value2, int value3){
        side[0] = value1;
        side[1] = value2;
        side[2] = value3;
    }

    /**
     * Compute perimeter of a Triangle object.
     * @return double
     */
    public double getPerimeter() {
        int sum = 0;
        for (int value : side) {
            sum += value;
        }
        return sum;
    }

    @Override
    public String toString(){
        return "Triangle {a=" + side[0] + ", b=" + side[1] + ", c=" + side[2] +
                "} has perimeter: "  + String.format("%.2f",getPerimeter());
    }

    @Override
    public int hashCode() {
        int result = 1;
        for(int number : side){
            result = 31 * result + number;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle test = (Triangle) o;
        for (int i = 0; i < side.length; i++ ) {
            if (side[i] != test.side[i]) {
                return false;
            }
        }
        return true;
    }
}
