package Ancheta017.java;

/**
 * This class holds the length and width of the sides of a rectangle.
 */
public class Rectangle implements Shape{
    private int length;
    private int width;

    /**
     * Constructor with two arguments
     * @param value1 an integer value
     * @param value2 an integer value
     */
    public Rectangle(int value1, int value2){
        length = value1;
        width = value2;
    }

    /**
     * Compute perimeter of a Rectangle object.
     * @return double
     */
    public double getPerimeter(){
        return (2 * length + 2 * width) ;
    }

    @Override
    public String toString(){
        return "Rectangle {l=" + length + ", w=" + width + "} has perimeter: "  + String.format("%.2f",getPerimeter());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + length;
        result = 31 * result + width;
        return result;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle test = (Rectangle) o;
        if (length != test.length) return false;
        if (width != test.width) return false;
        return true;
    }
}
