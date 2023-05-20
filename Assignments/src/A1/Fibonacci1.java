package A1;
//Cpsc 331 Assignment 1 - Algorithm 1
//the following algorithm computes the nth fibonacci number recursively

import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;

public class Fibonacci1 {
    //main() method calls the method Fib1 in a for loop to calculate the first 54 fibonacci numbers and measures the time it takes to compute each one
    public static void main(String[] args){

        //initializing variables
        long start, end, duration; 

        //try (write output to file) catch (error writing to file) block
        try {
            //make new file, fibonacci.txt (1st parameter), overwrite file each time program is run (2nd parameter)
            FileWriter fw = new FileWriter("fibonacci1.txt", false);

            //calculate first 54 fibonacci numbers (i = 0 to i = 53)
            for (int i = 0; i <= 53; i++){ 

                //start system clock
                start = System.nanoTime();

                //call recursive method on input i and store ith fibonacci number in variable fibNumber
                BigInteger fibNumber = Fib1(i);

                //stop system clock
                end = System.nanoTime();

                //calculate time taken
                duration = end - start;

                //write output to file: fibonacci.txt
                fw.write("Fibonacci(" + i + ") = " + fibNumber + "\nTime taken: " + duration + " nanoseconds\n");
            }
            //file writer
            fw.close();

            //print statement to confirm output was saved to a new file
            System.out.println("saved to fibonacci1.txt");
        }
        //catch error writing to file
        catch (IOException e){
            System.out.println("Error writing to file: " + e);
        }
    }
        
    //fib1() method, calculates the nth fibonacci number using a recursive algorithm 
    public static BigInteger Fib1(int n){
        if (n < 2) {
            return BigInteger.valueOf(n);
        } else {
            return Fib1(n - 1).add(Fib1(n - 2));
        }
    }
}
