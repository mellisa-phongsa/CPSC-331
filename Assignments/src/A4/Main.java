import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public  static void main(String[] args) {
        //elements array is generated once
        int[] elementsArray = ArrayGenerator.generateElementsArray();
        HashTableSC<HashableObject> hashTable;
        hashTable = new HashTableSC<>(9973);

        try (PrintWriter pw = new PrintWriter(new FileWriter("Part1Output.txt"))) {
            //create one search array at a time, search it, record running time
            //set searchArray = null and repeat
            for (int n = 1000; n <= 1000000; n += 10000) {
                //create the array 
                int[] searchArray = ArrayGenerator.generateSearchArray(n);

                //total linear search time
                long linearstarttime = System.nanoTime();
                //for each element in the elements array
                for (int element : elementsArray) {
                    //search for each element in the search array
                    Search.linearSearch(searchArray, element);
                    //add time it took to find each element to the total time
                }
                long linearendtime = System.nanoTime();
                long lineartotaltime = linearendtime - linearstarttime;

                //sort the array
                MergeSort.mergeSort(searchArray);

                //total binary search time
                long binarystartime1 = System.nanoTime();
                //for each element in the elements array
                for (int element : elementsArray) {
                    //search for each elemeent in the search array
                    Search.binarySearch(searchArray, element);
                    //add time it took to find each element to the total time
                }
                long binaryendtime1 = System.nanoTime();
                long binarytotaltime1 = binaryendtime1 - binarystartime1;

                //print to file
                pw.print("Array Size " + n + " ");
                pw.print("Linear Search Time " + lineartotaltime + " ");
                pw.print(" Binary Search Time " + binarytotaltime1 + "\n");

                //set array to null
                searchArray = null;
            }

        } catch (IOException e) {
            //if there is an error writing to file print error message
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
        try (PrintWriter pw = new PrintWriter(new FileWriter("Part2Output.txt"))) {
            //create one search array at a time, search it, record running time
            //set searchArray = null and repeat
            for (int n = 1000; n <= 1000000; n += 10000) {
                //create the array 
                int[] searchArray = ArrayGenerator.generateSearchArray(n);

                //sort the array
                MergeSort.mergeSort(searchArray);

                //total binary search time
                long binarystartTime = System.nanoTime();
                //for each element in the elements array
                for (int element : elementsArray) {
                    //search for each elemeent in the search array
                    Search.binarySearch(searchArray, element);
                    //add time it took to find each element to the total time
                }
                long binaryendTime = System.nanoTime();
                long binarytotaltime = binaryendTime - binarystartTime;

                // Hash search time
                
                // Building the hash table
                for (int element : searchArray) {
                    hashTable.add(element);
                }
     
                // doing the hash search using the elements array
                long startTime = System.nanoTime();
                for (int element : elementsArray) {
                    hashTable.contains(element);
                }
                long endTime = System.nanoTime();
                long totaltime = endTime - startTime;


                //print to file
                pw.print("Array size " + n + " ");
                pw.print("Binary Search Time " + binarytotaltime + " ");
                pw.print("Hash Search Time " + totaltime + "\n");

                //set array to null
                searchArray = null;

                // clearing the hashtable after each iteration
                hashTable.clear();
            }

        } catch (IOException e) {
            //if there is an error writing to file print error message
            System.out.println("Error writing to file: " + e.getMessage());
        } 
    }
}
