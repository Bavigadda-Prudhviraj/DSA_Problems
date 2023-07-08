package com.prudhvi.maths.modular_arithmetic;

public class PrimeModuloInverse {
	/*
	Problem Description
		Given two integers A and B. Find the value of A-1 mod B where B is a prime number and gcd(A, B) = 1.
		A-1 mod B is also known as modular multiplicative inverse of A under modulo B.

	Problem Constraints
		1 <= A <= 109
		1<= B <= 109
		B is a prime number
	 */

	public static void main(String[] args) {
		int num=6;
		int mod=23;
		int answer=inverseModulo(num,mod);
		System.out.println(answer);
		/*
		The answer we are searching for is a number num^-1 such that (num * num^-1) % modulo = 1. 
		We need to find the value of num^-1 that satisfies this equation after the calculation.
			For example, consider (5 * 5^-1) % 6 = 1. This equation can be rewritten as (5 % 6 * (5^-1 % 6)) % 6 = 1, 
						 (   5   *    5^-1   )%6 =1 
			 			 (  5%6  *  (5^-1)%6 )%6 =1,
			 			 ( [0,6-1],   [1,6-1])%6 =1, second one range start from 2 because when both are 0 then we can't get 1 range number 
			However, in this case, the range starts from 2 because when both numbers are 0, we cannot obtain 1.
				Let's search for values that can replace 5^-1 to get 1:
						(5 * 1) % 6 = 5
						(5 * 2) % 6 = 4
						(5 * 3) % 6 = 3
						(5 * 4) % 6 = 2
						(5 * 5) % 6 = 1
			Here, the value 5 is equal to 5^-1. If we replace 5^-1 with 5 in the original equation, we get (5 * 5) % 6 = 1. 
			When we compute (25) % 6, we indeed obtain the same output of 1.
			Therefore, in this scenario, we need to search for the element that can replace num^-1 to yield 1 after calculation.
		 */

	}
	/*
	The inverseModulo method calculates the inverse of a number num modulo mod. 
	It utilizes the fastPower method to perform modular exponentiation and find the inverse. 
	The method returns the calculated inverse modulo value.
	
	Time Complexity:
		The time complexity of this code is O(log(exponent)), where exponent is the power to which num is raised. 
		This is because the fastPower method performs recursive calls, dividing the exponent by 2 at each step.
		
	Space Complexity:
		The space complexity of this code is O(log(exponent)) as well, 
		since the recursive calls in the fastPower method create a stack frame for each recursion, 
		with a maximum depth of log(exponent).
		
		If we call power function in inverseModulo then Space complexity will be O(1)
	 */
	public static int inverseModulo(int num,int mod) {
		/*
		The reason we pass mod - 2 as the exponent to the fastPower function is based on Fermat's Little Theorem.
			1.Fermat's Little Theorem states that for any prime number 'p' and any integer 'a' not divisible by 'p', a^(p-1) ≡ 1 (mod p). 
			  Rearranging the equation, we get a^(p-2) ≡ a^(-1) (mod p).
			2.In the context of finding the inverse modulo, we want to find the value of num^(-1)%(mod), where num and mod are integers.
			3.By setting the exponent as mod - 2 in the fastPower function, we are essentially calculating num^(mod-2)%(mod), 
			  which gives us the inverse of num modulo mod as per Fermat's Little Theorem.
			4.So, using mod - 2 as the exponent is a way to utilize the properties of modular arithmetic and Fermat's Little Theorem to efficiently find the inverse modulo value.
			5.It's important to note that this method works specifically when the mod value is a prime number and num is not divisible by mod. 
			  If mod is not prime or num is divisible by mod, the inverse might not exist or be calculated differently.
		 */
		long answer=fastPower(num, mod-2, mod);
		return (int)answer;
		
	}
	private static long fastPower(int base, int exponent, int mod) {
		if(exponent==0) {
			return 1;
		}
		//long answer=fastPower(base, exponent/2, mod);
		long answer=power(base, exponent, mod);
		if(exponent%2==0) {
			return (answer*answer)%mod;
		}
		else {
			return (((answer*answer)%mod)*base)%mod;
			
		}
	}
	private static long power(long base, long exponent, long mod) { 
        long res = 1;        // Initialize result 
        base = base % mod;        // Update x if it is more than or equal to p 
        while (exponent > 0) { 
            // If y is odd, multiply x with result 
            if ((exponent & 1) == (long)1) 
                res = (res*base) % mod; 
            exponent = exponent >> 1; 
            base = (base * base) % mod; 
        }
        return res; 
    }

}
