//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//node within the doubly linked list implementations

package A2;
public class Node<T> {
    //initailizing varaibles
    public T value;
    public Node<T> next = null;
    public Node<T> prev = null;

    //constructor
    public Node(T value) {
        //value of the node
        this.value = value;
    }
}
