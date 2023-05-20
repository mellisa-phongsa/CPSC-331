public class DLLQueue<T> {

    private static class Node<T> {
        private T value;
        private Node<T> next = null;
        private Node<T> prev = null;
    
        public Node(T value) {
            this.value = value;
        }
    }
    
    private Node<T> front = null;
    private Node<T> back = null;
    private int size;
    
    //return null is queue is empty 
    public boolean isEmpty() {
        return front == null;
    }

    //ADD to the END of the List/Queue
    public void enqueue(T value) {
        //create new node
        Node<T> newNode = new Node<>(value);

        //check if q is empty
        if (isEmpty()) { //yes
            //set the newNode to be both the front and back of the q
            front = newNode;
            back = newNode;

        } else { //no
            //set the next property of the current tail/back of q to be the newNode
            back.next = newNode;
            //set the prev property on the newNode to be the current tail/back of q 
            newNode.prev = back;
            //set newNode to be the new tail/back of q
            back = newNode;
        }
        //making the q circular
        back.next = front;
        front.prev = back;
        //increment size of q
        size++;
    }

    //REMOVE from the front of the List/Queue
    public T dequeue() {
        //check if the q is empty
        if(isEmpty()) { //yes
            //throw an exception
            throw new IllegalStateException("Queue is empty!");
        }

        //store the current head/front of queue in a variable
        Node<T> dqedNode = front;

        //check if there is one element in the list
        if (front == back) { //yes
            //set front and back to null
            front = null;
            back = null;

        } else { //no
            //set new head/front of queue to be the next in "line"
            front = front.next;
            //set the dequeued nodes next property to null
            dqedNode.next = null;
            //set prev property of the new head/front of q to be the tail/end of queue
            front.prev = back;
            //set next property of the back/tail to be the new front
            back.next = front;
        }
        //decrement size of q
        size--;
        //return the removed value
        return dqedNode.value;
    }
}
