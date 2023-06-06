import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public  static void main(String[] args) {
        //elements array is generated once
        int[] elementsArray = ArrayGenerator.generateElementsArray();

        try (PrintWriter pw = new PrintWriter(new FileWriter("Part1Output.txt"))) {
            //create one search array at a time, search it, record running time
            //set searchArray = null and repeat
            for (int n = 1000; n <= 1000000; n += 10000) {
                //create the array 
                int[] searchArray = ArrayGenerator.generateSearchArray(n);

                //total linear search time
                long linearSearchTime = 0;
                //for each element in the elements array
                for (int element : elementsArray) {
                    //search for each element in the search array
                    long searchTime = Search.linearSearch(searchArray, element);
                    //add time it took to find each element to the total time
                    linearSearchTime += searchTime;
                }

                MergeSort.mergeSort(searchArray);

                //total binary search time
                long binarySearchTime = 0;
                //for each element in the elements array
                for (int element : elementsArray) {
                    //search for each elemeent in the search array
                    long searchTime = Search.binarySearch(searchArray, element);
                    //add time it took to find each element to the total time
                    binarySearchTime += searchTime;
                }

                pw.println("\nArray Size\tLinear Search Time\tBinary Search Time");
                pw.printf("%d\t\t%d\t\t\t\t%d%n", n, linearSearchTime, binarySearchTime);
                searchArray = null;
            }

        } catch (IOException e) {
            //if there is an error writing to file print error message
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
    }
}
