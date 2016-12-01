package Ancheta017.java;

/**
 * This class holds the test data.
 */

public class Main {

    public static void main(String[] args) {

        int[] values = {2,1,3,5,3,5,7,1,4,5,1,2,8,9};
        ShapeLinkedList sll = new ShapeLinkedList();

        /*
         * PART 1
         * Create objects for the different classes (Circle, Square, Rectangle, Triangle).
         */

        Circle c1 = new Circle(values[0]);
        Circle c2 = new Circle(values[1]);

        Square sq1 = new Square(values[2]);
        Square sq2 = new Square(values[3]);

        Triangle t1 = new Triangle(values[4],values[5],values[6]);
        Triangle t2 = new Triangle(values[7],values[8],values[9]);

        Rectangle r1 = new Rectangle(values[10],values[11]);
        Rectangle r2 = new Rectangle(values[12],values[13]);

        /*
         * PART 2
         * Insert objects at the beginning or end of the linked list.
         * Delete the tail of the linked list.
         * Delete a node from the linked list using a shape object as argument.
         * Search, delete and insert nodes in the linked list using index.
         */

        System.out.println("********** TASK 1 : insert objects to the linked list **********");
        sll.insertAtBeginning(r1);
        sll.insertAtBeginning(r2);
        sll.insertAtBeginning(c1);
        sll.insertAtBeginning(c2);
        sll.insertAtEnd(sq1);
        sll.insertAtEnd(sq2);
        sll.insertAtEnd(t1);
        sll.insertAtEnd(t2);
        System.out.println(sll);

        System.out.println("********** TASK 2 : delete the tail of the linked list **********");
        sll.deleteTail();
        System.out.println(sll);

        System.out.println("********** TASK 3 : delete an object(sq2) from the linked list **********");
        sll.deleteData(sq2);
        System.out.println(sll);

        System.out.println("********** findIndex(int idx) : search objects using index  **********");
        System.out.println("Length: " + sll.length());
        for(int i = 0; i < sll.length(); i++) {
            System.out.println("index " + i +": "  + sll.findAtIndex(i).toString());
        }
        System.out.println();

        System.out.println("********** insertAtIndex( 2,t2 ) : insert objects using index **********");
        sll.insertAtIndex(2,t2);
        System.out.println("Length: " + sll.length());
        for(int i = 0; i < sll.length(); i++) {
            System.out.println("index " + i +": " + sll.findAtIndex(i).toString());
        }
        System.out.println();

        System.out.println("********** deleteAtIndex( 1 ) : delete objects using an index  **********");
        sll.deleteAtIndex(1);
        System.out.println("Length: " + sll.length());
        for(int i = 0; i < sll.length(); i++) {
            System.out.println("index " + i +": " + sll.findAtIndex(i).toString());
        }
    }
}
