
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        /**
        * @author Jalal Kawash
        * code from BSTTester.java
        */
        System.out.println("Creating a new tree of integers");
        BST<Integer> t = new BST<Integer>();
        
        Random rnd = new Random();
        for (int i = 0; i < 100; i++)
        {
            t.add(rnd.nextInt(100));
        }
        t.printBST(BST.INORDER);
        t.printBST(BST.PREORDER);
        t.printBST(BST.POSTORDER);
        
        Node<Integer> root = t.getRoot();
        int heightbst = t.height();
        boolean perfect = t.isPerfect();
        boolean complete = t.isComplete();
        boolean doubles = t.hasDoubles();
        System.out.println("Height: " + heightbst);
        System.out.println("Is Perfect: " + perfect);
        System.out.println("Is Complete: " + complete);
        System.out.println("Has Doubles: " + doubles);
        System.out.println("Root is: " + root.getValue());
    }
}
