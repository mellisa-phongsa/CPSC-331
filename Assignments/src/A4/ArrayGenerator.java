/**
 * names: Mellisa Phongsa, Hassan Sohail
 * This method randomly generates an array of integers with size n 
 * where n is between 1000 and 1000000 and the values in the array
 * are from 0 to 5000
 */

import java.util.Random;
public class SearchArrayGen {
    public static int[] genSearchArray(int size) {
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
}
