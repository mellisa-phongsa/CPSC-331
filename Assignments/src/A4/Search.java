
public class Search {

    public static void linearSearch(int[] searchArray, int element) {
        //iterate through the search array
        for (int i = 0; i < searchArray.length; i++) {
            //if the element in the elements array is found in the search array
            //break out of the loop
            if (searchArray[i] == element) {
                break;
            }
        }
       
    }

    public static void binarySearch(int[] searchArray, int element) {
        //index of the first and last elements in the array
        int low = 0;
        int high = searchArray.length - 1;

        //while low is less than or equal to high
        while (low <= high) {
            //find the index of the new middle
            int mid = low + (high - low) / 2;

            //if the value in the middle of the array is equal to the element break out of the loop
            if (element == searchArray[mid]) {
                break;

            //if the element is greater than the middle value only look at the values greater than mid
            } else if (element > searchArray[mid]) {
                //find the index of the new low
                low = mid + 1;

            //if the element is less than the middle value only look at the values less than mid
            } else {
                //find the index of the new high
                high = mid - 1;
            }
        }
    }
}
