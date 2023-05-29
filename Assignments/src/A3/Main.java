
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //instantiating objects
        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);
        BST<Integer> t = new BST<Integer>();

        //ask user for size of tree
        System.out.print("enter tree size: ");
        int treeSize = scanner.nextInt();

        //create filename
        String fileName = "size" + treeSize + ".txt";

        //try to write to file
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            //generate 100 trees
            for (int numTree = 1; numTree <= 100; numTree++) {
                //print number tree created
                System.out.println("\nTree #" + numTree);

                //create variables
                Node<Integer> root = t.getRoot();
                int heightbst = t.height();
                boolean perfect = t.isPerfect();
                boolean complete = t.isComplete();
                boolean doubles = t.hasDoubles();

                //generate a tree with size, treeSize
                for (int i = 0; i < treeSize; i++){
                    //add randomly generated values
                    t.add(rnd.nextInt(100));
                }

                //print to terminal
                //print tree traversal order
                t.printBST(BST.INORDER); 
                t.printBST(BST.PREORDER);
                t.printBST(BST.POSTORDER);
                System.out.println("Root: " + root.getValue()); //print root of tree
                System.out.println("Height: " + heightbst); //print height of tree
                System.out.println("Perfect: " + perfect); //print is tree perfect
                System.out.println("Complete: " + complete); //print is tree complete
                System.out.println("Doubles: " + doubles); //print tree has doubles

                //print to file
                pw.println("Tree #" + numTree); //tree number
                pw.println("Height: " + heightbst); //height of tree
                pw.println("Perfect: " + perfect); //is tree perfect
                pw.println("Complete: " + complete); //is tree complete
                pw.println("Doubles: " + doubles); //does tree have doubles
                pw.println();

                //clear tree after each iteration
                t.clear();
            } 
        } catch (IOException e) {
            //if there is an error writing to file print error message
            System.out.println("Error writing to file: " + e.getMessage());
        }

        //ask user for value inside tree
        System.out.print("enter a value in tree #100: ");
        int value = scanner.nextInt();
        scanner.close();

        //var to store the level the value was found in
        int i;
        //if the value is not found in the tree print msg
        if(!t.contains(value)) {
            System.out.println(value + " was not found in the tree");
        //if the value is stored in the root print msg
        } else if (t.parent(value) == null) {
            //level is = 0 if value is found at root
            i = t.level(value);
            //the root does not have a parent
            System.out.println(value + " has no parent because it is the root of the tree");
            System.out.println("The level of the value " + value + " is " + i);
        } else {
            //update i to be the level the value was found in
            i = t.level(value);
            //get parent of the value
            Node<Integer> p = t.parent(value);
            //print to terminal
            System.out.println("The parent of " + value + " is " + p.getValue());
            System.out.println("The level of the value " + value + " is " + i);
        }
    }
}
