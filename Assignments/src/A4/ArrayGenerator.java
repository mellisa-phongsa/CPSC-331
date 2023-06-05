/**
 * Names: Mellisa Phongsa, Hassan Sohail
 * 
 * generateSearchArray: Array to be searched, The array size is between 
 * 1000 and 1,000,000 and the values in the array are from 0 to 5000.
 * 
 * generateElementsArray: The array size is 100 and has 99 distinct 
 * random numners between 0 to 5000. and 5001 is the value in the 100th cell the array.
 * 
 */

import java.util.Random;
import java.util.HashSet;
import java.util.Set;
public class ArrayGenerator {

    public static int[] generateSearchArray(int size) {
        //create int array with size between 1000 and 1000000
        int[] searchArray = new int[size];
        Random r = new Random();
        
        //randomly generate values in the array between 0 and 5000
        for (int i = 0; i < size; i++) {
            searchArray[i] = r.nextInt(5001);
        }
        //return the array
        return searchArray;
    }

    public static int[] generateElementsArray() {
        //create int array with size 100
        int[] elementsArray = new int[100];
        Random r = new Random();

        //create hashset to keep track of distinct values in the array
        Set<Integer> distinctNumbers = new HashSet<>();

        //Generate 99 distinct random nnumbers between 0 and 500
        for (int i = 0; i < 99; i++) {
            int rn = r.nextInt(5001);

            //if the value already exists in the array regenerate the number
            while (distinctNumbers.contains(rn)) {
                rn = r.nextInt(5001);
            }
            //add number to hash set
            distinctNumbers.add(rn);

            //store distinct randomly generated numbers in the array
            elementsArray[i] = rn;
        }
        //add 5001 as the 100th cell in the array
        elementsArray[99] = 5001;

        //return the array
        return elementsArray;
    }
}
