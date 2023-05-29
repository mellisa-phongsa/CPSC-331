
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //////////////////////////////////////////////////////////////
        /**
        * @author Jalal Kawash
        * code from BSTTester.java
        */
        System.out.println("Creating a new tree of integers");
        BST<Integer> t = new BST<Integer>();
        Random rnd = new Random();

        for (int i = 0; i < 20; i++)
        {
            t.add(rnd.nextInt(100));
        }
        t.printBST(BST.INORDER);
        t.printBST(BST.PREORDER);
        t.printBST(BST.POSTORDER);
        //////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////
        /**
        * added code
        * names: Mellisa Phongsa, Hassan Sohail
        */
        Node<Integer> root = t.getRoot();
        int heightbst = t.height();
        boolean perfect = t.isPerfect();
        boolean complete = t.isComplete();
        boolean doubles = t.hasDoubles();

        Scanner scanner = new Scanner(System.in);
        System.out.print("enter a value: ");
        int value = scanner.nextInt();
        scanner.close();
        int i = t.level(value);

        System.out.println("The Root of the tree is: " + root.getValue());
        if(!t.contains(value)) {
            System.out.println(value + " was not found in the tree");
        } else if (t.parent(value) == null) {
            System.out.println(value + " has no parent because it is the root of the tree");
            System.out.println("The level of the value " + value + " is " + i);
        } else {
            Node<Integer> p = t.parent(value);
            System.out.println("The parent of " + value + " is " + p.getValue());
            System.out.println("The level of the value " + value + " is " + i);
        }
        System.out.println("The Height of the Tree is: " + heightbst);
        System.out.println("Is Perfect: " + perfect);
        System.out.println("Is Complete: " + complete);
        System.out.println("Has Doubles: " + doubles);
        //////////////////////////////////////////////////////////////
    }
}
