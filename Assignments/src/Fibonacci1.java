//Cpsc 331 Assignment 1 - Algorithm 1
//the following algorithm computes the nth fibonacci number recursively
import java.math.BigInteger;

public class Fibonacci1 {
    //main() method calls the method Fib1 in a for loop and measures the time it takes to compute each fibonacci number
    public static void main(String[] args){
        long start, end, duration; //initializing variables
        for (int i = 0; i <= 53; i++){ //calculate first 54 fibonacci numbers (i = 0 to i = 53)
            start = System.nanoTime();
            int fibNumber = Fib1(i); //call recursive method with input i
            end = System.nanoTime();
            duration = end - start; //calculate time it took fir system to calculate the ith fibonacci number
            System.out.print("Fibonacci(" + i + ") = " + fibNumber + " - Time taken: " + duration + " nanoseconds");
        }
    }
    //fib1() method, calculates the nth fibonacci number using a recursive algorithm 
    public static int Fib1(int n){
        if (n < 2) {
            return n;
        } else {
            return Fib1(n - 1) + Fib1(n - 2);
        }
    }
}
