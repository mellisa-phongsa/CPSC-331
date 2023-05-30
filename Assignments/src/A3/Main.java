
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

        // Variable for totalheight
        int totalheight = 0;

        int perfectrees = 0;

        int completetrees = 0;

        int doubletrees = 0;

        //try to write to file
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            //generate 100 trees
            for (int numTree = 1; numTree <= 100; numTree++) {
                //print number tree created
                System.out.println("\nTree #" + numTree);

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

                //create variables
                Node<Integer> root = t.getRoot();
                int heightbst = t.height();
                boolean perfect = t.isPerfect();
                boolean complete = t.isComplete();
                boolean doubles = t.hasDoubles();

                // incrementing the totalheight by height of tree
                totalheight += heightbst;

                if (t.isPerfect() == true) {
                    perfectrees++;
                }

                if (t.isComplete() == true) {
                    completetrees++;
                }

                if (t.hasDoubles() == true) {
                    doubletrees++;
                }

                System.out.println("Root: " + root.getValue()); //print root of tree
                System.out.println("Height: " + heightbst); //print height of tree
                System.out.println("Perfect: " + perfect); //print is tree perfect
                System.out.println("Complete: " + complete); //print is tree complete
                System.out.println("Doubles: " + doubles); //print tree has doubles

                //randomly generate a value
                int value = rnd.nextInt(100);
                //var to store the level the value was found in
                int i = t.level(value);

                System.out.println("Value: " + value);

                //if the value is not found in the tree print msg
                if(!t.contains(value)) {
                    System.out.println(value + " was not found in the tree");
                //if the value is stored in the root print msg
                } else if (t.parent(value) == null) {
                    //the root does not have a parent
                    System.out.println(value + " has no parent because it is the root of the tree");
                    System.out.println("The level of the value " + value + " is " + i);
                } else {
                    //get parent of the value
                    Node<Integer> p = t.parent(value);
                    //print to terminal
                    System.out.println("The parent of " + value + " is " + p.getValue());
                    System.out.println("The level of the value " + value + " is " + i);
                }

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
        // Variable for average height of the tree
        int averageheight = totalheight/100;

        // Printing the average height of the trees
        System.out.println("The average height of the 100 trees of size " + treeSize + " is " + averageheight);
        pw.println("The average height of the 100 trees of size " + treeSize + " is " + averageheight);
        System.out.println("The toal number of perfect trees is " + perfectrees);
        pw.println("The toal number of perfect trees is " + perfectrees);
        System.out.println("The toal number of complete trees is " + completetrees);
        pw.println("The toal number of complete trees is " + completetrees);
        System.out.println("The total number of double trees is " + doubletrees);
        pw.println("The total number of double trees is " + doubletrees);

        } catch (IOException e) {
            //if there is an error writing to file print error message
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
