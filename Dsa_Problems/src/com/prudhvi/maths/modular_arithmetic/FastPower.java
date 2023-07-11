package com.prudhvi.maths.modular_arithmetic;

public class FastPower {

	public static void main(String[] args) {
		long base=2;
		long exponent=100;
		long mod=1000000007;
		long answer=fastPower(base, exponent, mod);
		System.out.println(answer);
		

	}
	private static long fastPower(long base, long exponent, long mod) { 
		//Initialize the result variable to 1.
        long res = 1;   
        //Reduce the base value modulo the given mod value to ensure it stays within the range of the modulo.
        base = base % mod; 
        //Start a loop while the exponent is greater than 0.
        while (exponent > 0) { 
        	// Check if the least significant bit of the exponent is set (i.e., if it is an odd number).
            if ((exponent & 1) == (long)1) 
            	//If the exponent is odd, multiply the result by the base and take the modulo with respect to the given mod value.
                res = (res*base) % mod; 
            //Right-shift the exponent by one bit, effectively dividing it by 2.
            exponent = exponent >> 1; 
            // Square the base and take the modulo with respect to the given mod value.
            base = (base * base) % mod; 
        }
        return res; 
    }
	

}
