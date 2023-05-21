//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//This is a doubly linked list implementation of a stack

package A2;
public class DLLStack<T> {
    //declaring variables
    private Node<T> top = null;
    private int size;

    //return top is null if stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    //ADD to TOP of the list/stack
    public void push(T value) {
        //create a new node
        Node<T> newNode = new Node<T>(value);

        //check if the stack is empty
        if (isEmpty()) { //yes
            //set top of the stack to be the newNode
            top = newNode;

        } else { //no
            //set the prev property of the current head to be the newNode
            top.prev = newNode;
            //set the next property of the new Node to be current head
            newNode.next = top;
            //set the top of the stack to be the new node
            top = newNode;
        }
        //increment size of stack 
        size++;
    }

    //REMOVE from the TOP of the stack
    public T pop() {
        //check if the stack is empty
        if (isEmpty()) { //yes
            //throw an exception
            throw new IllegalStateException("Stack is empty!");
        }

        //store popped node in variable
        Node<T> poppedNode = top;

        //check if size of stack is equal to 1
        if(size == 1) { //yes
            //set only value in stack to null
            top = null;

        } else { //no
            //set top of stack to be the next item
            top = top.next;
            //set the prev property of the new top of stack to be null
            top.prev = null;
            //set the popped nodes next property to null
            poppedNode.next = null;
        }
        //decrement size of stack
        size--;
        //return popped value
        return poppedNode.value;
    }
}
