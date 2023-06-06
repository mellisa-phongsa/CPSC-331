import java.util.Arrays;
public class Search {

    public static long linearSearch(int[] searchArray, int element) {
        //start timer
        long startTime = System.nanoTime();

        //iterate through the search array
        for (int i = 0; i < searchArray.length; i++) {
            //if the element in the elements array is found in the search array
            //break out of the loop
            if (searchArray[i] == element) {
                break;
            }
        }
        //end timer
        long endTime = System.nanoTime();

        //return time taken
        return endTime - startTime;
    }

    public static long binarySearch(int[] searchArray, int element) {
        //start timer
        long startTime = System.nanoTime();

        //binary search returns the index of the value if it is found in the array
        //and returns -1 if it is not found in the array
        int index = Arrays.binarySearch(searchArray, element);

        //stop timer
        long endTime = System.nanoTime();

        //return time taken
        return endTime - startTime;
    }
}
