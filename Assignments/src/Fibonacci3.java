//Cpsc 331 Assignment 1 - Algorithm 3
//the following algorithm computes the nth fibonacci number recursively based on linear difference equations

import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;

public class Fibonacci3 {

    public static void main(String[] args){
        final int MAX = 5000;
        BigInteger[] f = new BigInteger[MAX];
        f[0] = BigInteger.ZERO;
        f[1] = f[2] = BigInteger.ONE;
    }

    public static BigInteger Fib3(int n){
        if (n < 3){
            //return f[n]
        }
        //if (f[n] != 0) return f[n]
        //if (n is odd)
            //f[n] = (fib3(k)^2 + (fib3(k-1))^2)
        //else f[n] = (2*fib3(k-1) + fib3(k))*fib3(k)
        //return f[n]
    }
}
