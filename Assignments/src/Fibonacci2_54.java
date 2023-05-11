//Cpsc 331 Assignment 1 - Algorithm 2
//the following algorithm computes the first 54 fibonacci number iteratively

import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;

public class Fibonacci2_54 {
    public static void main(String[] args){

        //initializing variables
        int n = 53;
        long start, end, duration;

        //try (write ouput to file) catch (error writing it to file)
        try {
            //make new file, fibonacci2.txt (1st parameter), overwrite file each time program is run (2nd parameter)
            FileWriter fw = new FileWriter("fibonacci2_54.txt", false);

            //calculate first 5000 fibonacci numbers (i = 0 to i = 4999)
            for (int i = 0; i <= n; i++){

                //start system clock
                start = System.nanoTime();

                //call iterative method on input i and store the ith fib number in variable fibnumber
                BigInteger fibNumber = Fib2(i);

                //stop system clock
                end = System.nanoTime();

                //calculate time taken
                duration = end - start;

                //write output to file: fibonacci2.txt
                fw.write("Fibonacci(" + i + ") = " + fibNumber + "\nTime taken: " + duration + " nanoseconds\n");
            }
            //close file writer
            fw.close();

            //print statement to confirm output was saved to a new file
            System.out.println("saved to fibonacci2_54.txt");
        }
        //catch error
        catch (IOException e){
            System.out.println("error writing to file: " + e);
        }

    }

    //fib2() method, calculates the nth fibonacci number using an iterative algorithm
    public static BigInteger Fib2(int n){

        //assign values and initialize j and i
        BigInteger i = BigInteger.ONE;
        BigInteger j = BigInteger.ZERO;

        //calculate nth fib number using iterative method
        for (int k = 1; k <= n; k++){
            j = i.add(j);
            i = j.subtract(i);
        }
        return j;
    }
}
