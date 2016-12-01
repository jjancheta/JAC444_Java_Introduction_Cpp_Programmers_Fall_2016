package Ancheta017.java;

/**
 * This class holds the linked list of geometrical shapes.
 */
public class ShapeLinkedList {

    private Node head;
    private int linkCount;

    /**
     * Default constructor
     */
    public ShapeLinkedList() {
        head = new Node();
        linkCount = 0;
    }

    /**
     * Constructor with one argument
     * @param head a reference of Node object
     */
    public ShapeLinkedList(Node head) {
        this.head = head;
    }

    /**
     * Check if the linked list is empty.
     * @return boolean
     */

    public boolean isEmpty() {
       return length() == 0;
    }

    /**
     * Insert object at the beginning of the linked list.
     * @param data a reference of Shape object
     */

    public void insertAtBeginning(Shape data) {
        Node current = new Node(data);                // create new Node with the passed argument
        current.setNext(head.getNext());
        head.setNext(current);                        // set head to the new node created.
        linkCount++;                                  // increase the linkedCount
    }

    /**
     * Insert object at the end of the linked list.
     * @param data a reference of Shape object
     */
    public void insertAtEnd(Shape data) {
        Node temp = new Node(data);                           // create a new node with the passed argument
        Node current = head;

        while(current.getNext() != null){                     // get the last node of linked list
            current = current.getNext();
        }
        current.setNext(temp);                                // set the next of the last node with the new node created
        linkCount++;                                          // increase the linkedCount
    }

    /**
     * Search the last node of the linked list.
     * @return Node
     */
    public Node tail() {
        Node tail = head.getNext();
        while (tail.getNext() != null ){
            tail = tail.getNext();
        }
        return tail;
    }

    /**
     * Delete the tail or the last node of the linked list.
     */
    public void deleteTail(){
        Node tail = tail();
        Node temp = head.getNext();
        while (temp.getNext() != tail){
            temp = temp.getNext();
        }
        temp.setNext(null);
        linkCount--;
    }

    /**
     * Returns the number of nodes in the linked list.
     * @return int
     */
    public int length() {
        return linkCount;
    }

    /**
     * Returns a node using the index of the linked list.
     * @param idx an integer representing the index of the node
     * @return Node
     */
    Node findAtIndex(int idx) {
        int i = 0;
        Node temp = head.getNext();
        while (i != idx && i < length()){
            temp = temp.getNext();
            i++;
        }
        return temp;
    }

    /**
     * Insert object in the specified index in the linked list.
     * @param idx an integer representing the index of the node
     * @param data a reference of Shape object
     */
    void insertAtIndex(int idx, Shape data) {
        if(idx == 0){
            insertAtBeginning(data);
        }
        else if (idx == length() - 1){
            insertAtEnd(data);
        }
        else{
            Node temp = new Node(data);
            temp.setNext(findAtIndex(idx));
            Node before = findAtIndex(idx -1);
            before.setNext(temp);
            linkCount++;
        }
    }

    /**
     * Delete a node using the index from the linked list.
     * @param idx an integer representing the index of the node
     */
    void deleteAtIndex(int idx) {
        Node temp = findAtIndex(idx);
        deleteData(temp.getData());
    }

    /**
     * Search and delete a node from the linked using the object.
     * @param data a reference of Shape object
     */
    void deleteData(Shape data) {
        Node findShape = head.getNext();
        Node before = head.getNext();
        while (!findShape.getData().equals(data)){
            before = findShape;
            findShape = findShape.getNext();
        }

        if (findShape == head.getNext()){
            head.setNext(findShape.getNext());            //set the head pointer to the next node
            findShape.setNext(null);
            linkCount--;
        }
        else if (findShape == tail()) {                  //if the object is the tail, call deleteTail() method
                deleteTail();
        }
        else {
                before.setNext(findShape.getNext());
                findShape.setNext(null);
                linkCount--;
            }
        }


    @Override
    public String toString() {
        Node current = head.getNext();
        String output = "";
        while(current != null)
        {
            output += current.getData().toString() + "\n";
            current = current.getNext();
        }
        return output;
    }


    @Override
    public int hashCode(){
        int result = head.hashCode();
        result = 31 * result + linkCount;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ShapeLinkedList)) return false;
        ShapeLinkedList test = (ShapeLinkedList) obj;
        if (head != test.head) return false;
        if (linkCount != test.linkCount) return false;
        return true;
    }

    /**
     * Class Node
     */
    public class Node{
        private Shape data;
        private Node next;

        /**
         * Default Constructor
         */
        public Node() {
            this.next = null;
            this.data = null;
        }

        /**
         * Constructor with one argument.
         * @param data a reference of Shape object
         */
        public Node(Shape data) {
            this.next = null;
            this.data = data;
        }

        /**
         * Return the value of data field.
         * @return data a reference of Shape object
         */
        public Shape getData() {
            return data;
        }

        /**
         * Return the value of next field.
         * @return next a reference of Node object
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set the value of the next field.
         * @param next a reference of Node object
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the value of the data field.
         * @param data a reference of Shape object
         */
        public void setData(Shape data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }

        @Override
        public int hashCode(){
            int result = data.hashCode();
            result = 31 * result + next.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Node)) return false;
            Node test = (Node) obj;
            if (data != test.data) return false;
            if (next != test.next) return false;
            return true;
        }
    }
}


