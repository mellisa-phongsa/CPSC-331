package A1;
//Cpsc 331 Assignment 1 - Algorithm 3
//the following algorithm computes the first 54 fibonacci number recursively based on linear difference equations

import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;

public class Fibonacci3_54 {
    //initialize array
    static int MAX = 54;
    static BigInteger[] f = new BigInteger[MAX];

    public static void main(String[] args){

        //initialize first 3 fib numbers and variables
        f[0] = BigInteger.ZERO;
        f[1] = f[2] = BigInteger.ONE;
        long start, end, duration;

        //try (write to output to file) catch (error writing to file)
        try{
            //make new file, fibonacci2.txt (1st parameter), overwrite file each time program is run (2nd parameter)
            FileWriter fw = new FileWriter("fibonacci3_54.txt", false);

            //calculate first 5000 fibonacci numbers (i = 3 to i = 4999)
            for (int i = 0; i < MAX; i++){

                //start system clock
                start = System.nanoTime();

                //call recursive method
                Fib3(i);

                //stop system clock
                end = System.nanoTime();

                //calculate time taken
                duration = end - start;

                //write output to file: fibonacci3.txt
                fw.write("Fibonacci(" + i + ") = " + f[i] + "\nTime taken: " + duration + " nanoseconds\n");
            }
            //close file writer
            fw.close();

            //print statement to confirm output was saved to a new file
            System.out.println("saved to fibonacci3_54.txt");
        }
        //catch error
        catch (IOException e){
            System.out.println("error writing to file: " + e);
        }
    }

    //fib3() method calculates the nth fib number using recursion based on linear equations
    public static BigInteger Fib3(int n){

        //initialize variable
        int k;

        //dont re calculate first 3 fib numbers
        if (n < 3){
            return f[n];
        }

        //if nth fib number is not = to zero return f[n]
        if (f[n] != null){
            return f[n];
        } 

        //if n is even
        if (n % 2 == 0) {
            //assign the value of k
            k = n / 2;
        } 

        //if n is odd
        else {
            //assign value of k
            k = (n + 1) / 2;
        }

        //if n is even
        if (n % 2 == 0){
            //recurison using linear equations
            return f[n] = Fib3(k - 1).multiply(BigInteger.valueOf(2)).add(Fib3(k)).multiply(Fib3(k));
        }
        //if n is odd
        else {
            //recursion using linear equations
            return f[n] = Fib3(k).multiply(Fib3(k)).add(Fib3(k - 1).multiply(Fib3(k - 1)));
        }
    }
}
