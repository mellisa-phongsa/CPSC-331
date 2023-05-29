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
        //call recursive method to find height of the tree starting at the root 
        return recursiveHeight(root);
    }
    private int recursiveHeight(Node<T> node) {
        if (node == null) {
            return -1; // Height of an empty tree is -1
        }
        //find height of the left subtree, recursive call
        int leftHeight = recursiveHeight(node.getLeft());
        //find height of right subtree, recursive call
        int rightHeight = recursiveHeight(node.getRight());
        //return the maximum height of the two subtrees
        return Math.max(leftHeight, rightHeight) + 1;
    }
//////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * added method
     * names: Mellisa Phongsa, Hassan Sohail
     * returns a reference to the parent of a node containing value
     */
    public Node<T> parent(T value) {
        //if the value is not in the tree return null
        if (!contains(value)){
            return null;
        } else {
            //call recursive method to find the parent of the node containing the value
            return recursiveParent(value, root, null);
        }
    }
    private Node<T> recursiveParent(T value, Node<T> current, Node<T> parent) {
        // Found the node containing the value 
        if (value.compareTo(current.getValue()) == 0) {
            // return its parent
            return parent; 
        } else if (value.compareTo(current.getValue()) < 0) {
            // Search in the left subtree
            return recursiveParent(value, current.getLeft(), current); 
        } else {
            // Search in the right subtree
            return recursiveParent(value, current.getRight(), current); 
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
        //if value is not in the tree return -1
        if (!contains(value)){
            return -1;
        } else {
            //call recursive method
            return recursiveLevel(value, root, 0, -1);
        }
    }
    private int recursiveLevel(T value, Node<T> current, int level, int highestLevel) {
        //mark current node as visited
        current.setVisited(true);
        //if the value is found set the new highest level
        if (value.compareTo(current.getValue()) == 0) {
            highestLevel = Math.max(highestLevel, level);
        }
        //check the left subtree for the value
        if (value.compareTo(current.getValue()) < 0 && !current.getLeft().isVisited()) {
            // Search in the left subtree, increment level
            highestLevel = recursiveLevel(value, current.getLeft(), level + 1, highestLevel); 
        } 
        //check the right subtree for the value
        if (value.compareTo(current.getValue()) > 0 && !current.getRight().isVisited()) {
            // Search in the right subtree, increment level
            highestLevel = recursiveLevel(value, current.getRight(), level + 1, highestLevel); 
        }
        //visited is reset to false
        current.setVisited(false);
        //return highest level
        return highestLevel;
    }
//////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * added method
     * names: Mellisa Phongsa, Hassan Sohail
     * returns true if the tree is complete; must call a recursive method recIsComplete(root, index)
     */
    public boolean isComplete() {
        //start at the root, index 0
        int index = 0;
        //call recursive method
        return recIsComplete(root, index, size());
    }
    private boolean recIsComplete(Node<T> node, int index, int size) {
        // Empty subtree is complete
        if (node == null) {
            return true; 
        }
        // Index of node exceeds the number of nodes in the tree
        if (index >= size) {
            return false; 
        }
        //return true if index < size
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
        //create hash set
        HashSet<T> set = new HashSet<>();
        //call recursive method
        return recHasDoubles(root, set);
    }
    private boolean recHasDoubles(Node<T> node, HashSet<T> set) {
        //if the tree is empty return false, and empty tree doesnt have doubled values
        if (node == null) {
            return false;
        }
        //if the set already contains the value
        if (set.contains(node.getValue())) {
            return true; // Found a duplicate value
        }
        //add value to set
        set.add(node.getValue());
        //if the left subtree or the right subtree contains a dupilcate value return true
        return recHasDoubles(node.getLeft(), set) || recHasDoubles(node.getRight(), set);
    }
//////////////////////////////////////////////////////////////////////////////////////////////
}

