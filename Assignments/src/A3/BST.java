import java.util.HashSet;

/**
 * Implementation of a BST using linking
 * 
 * Implementation is by contract and by reference
 *
 * @author Jalal Kawash, based on the implementation of Dale, Joyce, and Weems (Object-Oriented Data Structures in Java)
 * 
 */


public class BST<T extends Comparable> implements BSTInterface<T>
{   
    protected Node<T> root;
    
    public static final int INORDER = 0;
    public static final int PREORDER = 1;
    public static final int POSTORDER = 2;
    
    private LinkedQueue<T> inOrderQueue, preOrderQueue, postOrderQueue;
    
    public Node<T> getRoot(){ // needed in TreePrinter
        return root;
    }

    /**
     * Constructor for objects of class BST
     */
    public BST()
    {
        root = null;
    }

    /**
     * Precondition: None
     * Postcondition: returns true if BST is empty
     */
    public boolean isEmpty()
    {
        return (root == null);
    }
    
    /**
     * Precondition: None
     * Postcondition: returns false
     */
    public boolean isFull()
    {
        return false;
    }
    
    /**
     * Precondition: None
     * Postcondition: returns the number of elements inthe BST
     */
    public int size()
    {
        return recursiveSize(root);
    }
    
    int recursiveSize(Node<T> root) 
    {
        if (root == null) return 0;
        else return recursiveSize(root.getLeft()) + recursiveSize(root.getRight()) + 1;
    }

    /**
     * Precondition: None
     * Postcondition: deletes all the elements in the BST and resests it to the initial condition
     */
    public void clear()
    {
        root = null;
    }
    
    /**
     * Precondition: None
     * Postcondition: resets the current index to the begining of the BST
     */
    public void reset(int order) 
    {
        if (order == BST.INORDER)
        {
            inOrderQueue = new LinkedQueue<T>();
            inOrderTraversal(root);
        }
        
        if (order == BST.PREORDER)
        {
            preOrderQueue = new LinkedQueue<T>();
            preOrderTraversal(root);
        }
        
        if (order == BST.POSTORDER)
        {
            postOrderQueue = new LinkedQueue<T>();
            postOrderTraversal(root);
        }
    }
    
    void inOrderTraversal(Node<T> root) 
    {
        if (root != null) 
        {
            inOrderTraversal(root.getLeft());
            inOrderQueue.enqueue(root.getValue());
            inOrderTraversal(root.getRight());
        }
    }
    
    void preOrderTraversal(Node<T> root) 
    {
        if (root != null) 
        {
            preOrderQueue.enqueue(root.getValue());
            preOrderTraversal(root.getLeft());
            preOrderTraversal(root.getRight());
        }
    }
    
    void postOrderTraversal(Node<T> root) 
    {
        if (root != null) 
        {
            postOrderTraversal(root.getLeft());
            postOrderTraversal(root.getRight());
            postOrderQueue.enqueue(root.getValue());
        }
    }
    
    /**
     * Precondition: None
     * Postcondition: returns the next element in the list based on specified traversal order (inorder, preorder, postorder)
     */
    public T getNext(int order) 
    {
       if (order == BST.INORDER) return inOrderQueue.dequeue();
       if (order == BST.PREORDER) return preOrderQueue.dequeue();
       if (order == BST.POSTORDER) return postOrderQueue.dequeue();
       return null;
    }
    
    /**
     * Precondition: None
     * Postcondition: Adds a new element to the list
     */
    public void add(T item) 
    {
        root = recursiveAdd(item, root);
    }
    
    Node<T> recursiveAdd(T item, Node<T> root)
    {
        if (root == null) //insert here
        {
            root = new Node<T>();
            root.setValue(item);    
        }
        else if (item.compareTo(root.getValue()) < 0) // got to left subtree
            root.setLeft(recursiveAdd(item, root.getLeft()));
        else root.setRight(recursiveAdd(item, root.getRight())); // go right
        
        return root;
    }
    
    /**
     * Precondition: None
     * Postcondition: returns true if a given item is in the tree; otherwise returns false
     */
    public boolean contains(T item) 
    {
        return recursiveContains(item, root);
    }
    
    boolean recursiveContains(T item, Node<T> root)
    {
        if (root == null) return false;
        else if (item.compareTo(root.getValue()) < 0) return recursiveContains(item, root.getLeft());
        else if (item.compareTo(root.getValue()) > 0) return recursiveContains(item, root.getRight());
        else return true;
    }
    
    /**
     * Precondition: Item to be removed is in the tree
     * Postcondition: removes an item from the BST
     */
    public void remove(T item) 
    {
        root = recursiveRemove(item,root);
    }
    
    Node<T> recursiveRemove(T item, Node<T> root)
    {
        if (root == null) return null;
        else if (item.compareTo(root.getValue()) < 0) root.setLeft(recursiveRemove(item, root.getLeft()));
        else if (item.compareTo(root.getValue()) > 0) root.setRight(recursiveRemove(item, root.getRight()));
        else root = removeNode(root);
        return root;
    }
    
    Node<T> removeNode(Node<T> root)
    {
        T tmp;
        if (root.getLeft() == null) return root.getRight();
        else if (root.getRight() == null) return root.getLeft();
        else
        {
            tmp = predecessor(root.getLeft());
            root.setValue(tmp);
            root.setLeft(recursiveRemove(tmp, root.getLeft()));
            return root;
        }
    }
    
    T predecessor(Node<T> root)
    {
        while (root.getRight() != null) root = root.getRight();
        return root.getValue();
    }
    
    
    /**
     * Precondition: None
     * Postcondition: prints the contents of the list nased on the provided traversal order type
     */
    public void printBST(int order)
    {
        int s = size();
        if (order == INORDER) 
        {
            reset(INORDER);
            inOrderTraversal(root);
            System.out.println("Get items inorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.INORDER) + " ");
            System.out.println();
        }
        
        if (order == PREORDER) 
        {
            reset(PREORDER);
            preOrderTraversal(root);
            System.out.println("Get items preorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.PREORDER) + " ");
            System.out.println();
        }
        
        if (order == POSTORDER) 
        {
            reset(POSTORDER);
            postOrderTraversal(root);
            System.out.println("Get items postorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.POSTORDER) + " ");
            System.out.println();
        }
        
    }
//////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * added method
     * names: Mellisa Phongsa, Hassan Sohail
     * returns the height of the tree
     * the method contains and recursiveContains was used as reference
     * @param root2
     */
    public int height() {
        return recursiveHeight(root);
    }
    
    private int recursiveHeight(Node<T> node) {
        if (node == null) {
            return -1; // Height of an empty tree is -1
        } else {
            int leftHeight = recursiveHeight(node.getLeft());
            int rightHeight = recursiveHeight(node.getRight());
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * added method
     * names: Mellisa Phongsa, Hassan Sohail
     * returns a reference to the parent of a node containing value
     */
    public Node<T> parent(T value) {
        return recursiveParent(value, root, null);
    }
    
    private Node<T> recursiveParent(T value, Node<T> current, Node<T> parent) {
        if (current == null) {
            return null; // Value not found in the tree
        } else if (value.compareTo(current.getValue()) == 0) {
            return parent; // Found the node containing the value, return its parent
        } else if (value.compareTo(current.getValue()) < 0) {
            return recursiveParent(value, current.getLeft(), current); // Search in the left subtree
        } else {
            return recursiveParent(value, current.getRight(), current); // Search in the right subtree
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * added method
     * names: Mellisa Phongsa, Hassan Sohail
     * returns the level of the node containing value. If there is more than one node with the same value, it returns the highest level
     */
    public int level(T value) {
        return recursiveLevel(value, root, 0);
    }
    
    private int recursiveLevel(T value, Node<T> current, int level) {
        if (current == null) {
            return -1; // Value not found in the tree
        } else if (value.compareTo(current.getValue()) == 0) {
            return level; // Found the node containing the value, return its level
        } else if (value.compareTo(current.getValue()) < 0) {
            return recursiveLevel(value, current.getLeft(), level + 1); // Search in the left subtree, increment level
        } else {
            return recursiveLevel(value, current.getRight(), level + 1); // Search in the right subtree, increment level
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * added method
     * names: Mellisa Phongsa, Hassan Sohail
     * returns true if the tree is complete; must call a recursive method recIsComplete(root, index)
     */
    public boolean isComplete() {
        int index = 0;
        return recIsComplete(root, index, size());
    }
    
    private boolean recIsComplete(Node<T> node, int index, int size) {
        if (node == null) {
            return true; // Empty subtree is complete
        }
        
        if (index >= size) {
            return false; // Index of node exceeds the number of nodes in the tree
        }
        
        return recIsComplete(node.getLeft(), 2 * index + 1, size) &&
               recIsComplete(node.getRight(), 2 * index + 2, size);
    }
//////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * added method
     * names: Mellisa Phongsa, Hassan Sohail
     * returns true if the tree is perfect
     */
    public boolean isPerfect() {
        // calculate the height of the tree
        int treeHeight = height();
        // calculate the number of nodes in the tree using size
        int nodeCount = size();
        // calculated the expected number of nodes of a perfect tree which is 2^(h+1)-1 and saving this to expected nodes
        int expectedNodeCount = (int) Math.pow(2, treeHeight + 1) - 1;
        
        // comparing the actual nodecount with the expected nodecount, if they are equal the tree is perfect, if not then the tree is not perfect.
        return nodeCount == expectedNodeCount;
    }
//////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * added method
     * names: Mellisa Phongsa, Hassan Sohail
     * returns true if the tree has duplicate values
     */
    public boolean hasDoubles() {
        HashSet<T> set = new HashSet<>();
        return recHasDoubles(root, set);
    }
    
    private boolean recHasDoubles(Node<T> node, HashSet<T> set) {
        if (node == null) {
            return false;
        }
        
        if (set.contains(node.getValue())) {
            return true; // Found a duplicate value
        }
        
        set.add(node.getValue());
        
        return recHasDoubles(node.getLeft(), set) || recHasDoubles(node.getRight(), set);
    }
//////////////////////////////////////////////////////////////////////////////////////////////

public static void main(String[] args) {
    BST<Integer> binaryst = new BST<>();
    binaryst.add(25);
    binaryst.add(15);
    binaryst.add(50);
    binaryst.add(10);
    binaryst.add(22);
    binaryst.add(35);
    binaryst.add(70);
    binaryst.add(4);
    binaryst.add(12);
    binaryst.add(18);
    binaryst.add(24);
    binaryst.add(31);
    binaryst.add(44);
    binaryst.add(66);
    binaryst.add(90);
    

    binaryst.printBST(PREORDER);
    Node<Integer> root = binaryst.getRoot();
    int heightbst = binaryst.height();
    boolean perfect = binaryst.isPerfect();
    boolean complete = binaryst.isComplete();
    boolean doubles = binaryst.hasDoubles();
    int value = 50;
    Node<Integer> p = binaryst.parent(value);
    int i = binaryst.level(value);
    System.out.println("Height: " + heightbst);
    System.out.println("Is Perfect: " + perfect);
    System.out.println("Is Complete: " + complete);
    System.out.println("Has Doubles; " + doubles);
    System.out.println("Root is: "  + root.getValue());
    System.out.println("The parent of " + value + " is " + p.getValue());
    System.out.println("The level of the value " + value + " is " + i);

}
    
}

