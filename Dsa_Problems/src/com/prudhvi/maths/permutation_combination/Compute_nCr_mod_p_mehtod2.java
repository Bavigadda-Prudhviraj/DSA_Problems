package com.prudhvi.maths.permutation_combination;

public class Compute_nCr_mod_p_mehtod2 {
	/*
	Problem Description
		Given three integers A, B, and C, where A represents n, B represents r, and C represents p and p is a prime number greater than equal to n, 
		find and return the value of nCr % p where nCr % p = (n! / ((n-r)! * r!)) % p.
		x! means factorial of x i.e. x! = 1 * 2 * 3... * x.
		NOTE: 
			For this problem, we are considering 1 as a prime.

	Problem Constraints
		1 <= A <= 106
		1 <= B <= A
		A <= C <= 109+7
	 */

	public static void main(String[] args) {
		int A=51299; 
		int B=25646; 
		int C=3685739;
		int answer=nCr(A,B,C);
		
		System.out.println(answer);
		
	}
	/*
	The nCr method calculates the value of "n choose r" (binomial coefficient) modulo mod. 
	It uses the factorial function to calculate the factorial of n, r, and n-r. 
	It also uses the fast power function to perform modular exponentiation. 
	The method returns the calculated value of "n choose r
	
	Time Complexity:
			The time complexity of this code is O(n), where n is the input parameter num. 
			This is because both the factorial and fastPower functions iterate num times.
	Space Complexity:
			The space complexity of this code is O(1), as it uses a constant amount of additional space for storing intermediate results and variables.
	 */
	public static int nCr(int n,int r,int mod) {
		//It calls the factorial function to calculate the factorials of n, r, and (n-r), storing the results in nFactorial, rFactorial, and nrFactorial respectively.
		long nFactorial=factorial(n,mod);
		long rFactorial=factorial(r, mod);
		long nrFactorial=factorial(n-r, mod);
		//It calculates the inverses of nrFactorial and rFactorial using the fastPower function, which performs modular exponentiation.
		long inverseOfNRfactorial=fastPower(nrFactorial,(long)mod-2,(long)mod);
		long inverseOfRfactorial=fastPower(rFactorial,(long) mod-2, (long)mod);
		//the final answer by multiplying nFactorial, inverseOfNRfactorial, and inverseOfRfactorial, and then takes the modulo mod of the result.
		long answer=(((nFactorial*inverseOfNRfactorial)%mod)*inverseOfRfactorial)%mod;
		return (int)answer;
	}
	/*
	The factorial function calculates the factorial of a number modulo mod. 
	It initializes factorialOfNum to 1 and iterates from 2 to num, 
	multiplying each number and taking the modulo mod at each step.
	 */
	public static long factorial(int num,int mod){
		long factorialOfNum=1;
		for(int i=2;i<=num;i++) {
			factorialOfNum=((factorialOfNum*i)%mod);
		}
		return factorialOfNum;
	}
	/*
	he fastPower method calculates the result of base^exponent % mod using the concept of exponentiation by squaring. 
	It iteratively squares the base and reduces the exponent in binary form, multiplying the result by the base whenever the corresponding bit of the exponent is set to 1. 
	The method returns the calculated result.
	
	The time complexity of this code is O(log(exponent)), as the number of iterations in the while loop is determined by the number of bits in the exponent.

	The space complexity of this code is O(1), as it uses a constant amount of additional space to store the res, base, and exponent variables.
	 */
	private static long fastPower(long base, long exponent, long mod) { 
        long res = 1;        // It initializes the res variable to 1, which will store the result of base^exponent % mod
        /*
        The method performs a modulo operation on base to ensure that it is not greater than or equal to mod. 
        This step is necessary to keep the intermediate calculations within the desired modulus
         */
        base = base % mod;        // Update x if it is more than or equal to p 
        while (exponent > 0) { //It enters a while loop that continues until exponent becomes 0.
            // If y is odd, multiply x with result 
        	/*
        	it checks if the least significant bit (LSB) of exponent is 1. 
        	This is done by performing a bitwise AND operation (exponent & 1) with 1. 
        	If the result is 1, it means that the LSB of exponent is set to 1, indicating an odd value.
        	 */
            if ((exponent & 1) == (long)1) 
            	//If the LSB of exponent is 1, it multiplies the res variable by base and takes the modulo mod of the result. 
            	//This step incorporates the contribution of the current bit position in the exponent to the final result.
                res = (res*base) % mod; 
            //It then divides exponent by 2 using a right shift operation exponent >> 1,
            //effectively shifting the bits of exponent to the right by one position. 
            //This step reduces the exponent by a factor of 2.
            exponent = exponent >> 1; 
            //It squares the base variable by multiplying it with itself and taking the modulo mod of the result. 
            //This step accounts for the contribution of the next bit position in the exponent.
            base = (base * base) % mod; 
        }
        return res; 
    }

}
