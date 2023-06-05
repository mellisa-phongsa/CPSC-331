public class Main {
    public  static void main(String[] args) {
        //elements array is generated once
        int[] elementsArray = ArrayGenerator.generateElementsArray();

        //create one search array at a time, search it, record running time
        //set searchArray = null and repeat
        for (int n = 1000; n <= 1000000; n += 10000) {
            //create the array 
            int[] searchArray = ArrayGenerator.generateSearchArray(n);

            //search it
            //...

            //record running time of search
            //...

            //searchArray = null;
        }
    }
}
