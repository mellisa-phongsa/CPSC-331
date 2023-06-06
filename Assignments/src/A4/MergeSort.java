import java.util.Arrays; 
public class MergeSort {
    public static void mergeSort(int[] searchArray) {
        //if array is empty or has length 0 or 1
        if (searchArray == null || searchArray.length <= 1) {
            return;
        }

        //calculate middle of array
        int mid = searchArray.length / 2;

        //create a copy of the lower half of the search array 
        int[] left = Arrays.copyOfRange(searchArray, 0, mid);

        //create a copy of the upper half of the search array 
        int[] right = Arrays.copyOfRange(searchArray, mid, searchArray.length);

        //recursively sort left and right halfs
        mergeSort(left);
        mergeSort(right);

        //call method to mearch sorted halves
        merge(left, right, searchArray);
    }

    private static void merge(int[] left, int[] right, int[] merged) {
        //left array index
        int L = 0;

        //right array index
        int R = 0;

        //merged array index
        int M = 0;

        //iterate through the arrays and compare elements from the left and right arrays 
        //place the smaller element into the merged array
        while (L < left.length && R < right.length) {
            if (left[L] <= right[R]) {
                //put element from left array into the merged array
                merged[M] = left[L];
                //increment index, to update the current element
                L++;
            } else {
                //put element from the right array into the merged array
                merged[M] = right[R];
                //increment index, to update current element
                R++;
            }
            //increment index
            M++;
        }

        //merge remaining elements
        while (L < left.length) {
            merged[M] = left[L];
            L++;
            M++;
        }

        //merge remaining elements
        while (R < right.length) {
            merged[M] = right[R];
            R++;
            M++;
        }
    }
}
