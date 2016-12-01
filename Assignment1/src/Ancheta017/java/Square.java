package Ancheta017.java;

/**
 * This class holds the length of the sides of a square.
 */
public class Square implements Shape {
    private int length;

    /**
     * Constructor with one argument.
     * @param value an integer value
     */
    public Square(int value){
        length = value;
    }

    /**
     * Compute perimeter of a Square object.
     * @return double
     */
    public double getPerimeter(){
        return 4 * length ;
    }

    @Override
    public String toString(){
        return "Square {l=" + length +"} has perimeter: "  + String.format("%.2f",getPerimeter());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + length;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square test = (Square) o;
        if (length != test.length) return false;
        return true;
    }
}
