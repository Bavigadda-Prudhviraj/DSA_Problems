package com.prudhvi.maths.modular_arithmetic;

public class VeryLargePower {
	/*
	Problem Description
		Given two Integers A, B. You have to calculate (A ^ (B!)) % (1e9 + 7).
			"^" means power,
			"%" means mod, and
			"!" means factorial.

		Note: Ensure to handle integer overflow when performing the calculations.

	Problem Constraints
		1 <= A, B <= 5e5
	 */
	public static void main(String[] args) {
		int a=2;
		int b=27;
		long mod=1000000007;
		int answer=largePower(a, b,mod);
		System.out.println(answer);
		
		
	}
	/*
	The largePower method calculates the value of base^exponent % mod using modular exponentiation. 
	It calculates the factorial of the exponent (factorialOfB) modulo (mod - 1) and then calls the fastPower method to perform modular exponentiation on the base, exponent, and mod values. 
	The method returns the calculated value.
	
	Time complexity:
		The time complexity of this code depends on the value of exponent. 
		In the largePower method, the loop iterates exponent times to find factorial, resulting in a time complexity of O(exponent). 
		In the fastPower method, the recursion depth is logarithmic in exponent, resulting in a time complexity of O(log(exponent)).
	Space Complexity:
		The space complexity of this code is O(log(exponent)), 
		as the fastPower method uses recursive calls that create a stack frame for each recursion. 
		The space required by the stack is proportional to the logarithm of the exponent.
	 */
	public static int largePower(int base, int exponent,long mod) {
		//This variable will store the factorial of the exponent modulo (mod - 1).
		long factorialOfB=1;
		//loop calculates the factorial of the exponent by multiplying factorialOfB with each number from 1 to exponent and taking the modulo (mod - 1) at each step
	    for(int i=1;i<=exponent;i++){
	         factorialOfB=(factorialOfB*i)%(mod-1);
	    }
	    //After calculating factorialOfB, the code calls the fastPower method and passes the base, factorialOfB, and mod values as arguments. 
	    //The result is stored in the answer variable.
	    int answer=fastPower(base,factorialOfB,mod);
	    return answer;
	   }
	//The fastPower method implements modular exponentiation to calculate base^exponent % mod
	public static int fastPower(int base,long exponent,long mod){
		//base cases1: if exponent is 0, it returns 1; if exponent is 1, it returns base.
		if(exponent==0){
			return 1;
	    }
		//base cases2: if exponent is 1, it returns base.
	    else if(exponent==1){
	    	return base;
	    }
		//For exponents greater than 1, the method recursively calculates p = fastPower(base, exponent / 2, mod). 
		//It computes p as the result of modular exponentiation for base^(exponent / 2) % mod.
	    long p=fastPower(base,exponent/2,mod);
	    //If exponent is even, it returns (p * p) % mod.
	    if(exponent%2==0){
	    	return (int)( ( (p) * (p) )%mod ); 
	    }
	    //If exponent is odd, it returns (((p * p) % mod) * base) % mod.
	    else{
	        return (int)(( (( (p) * (p) )%mod) * base )%mod);
	    }
	}
	
	

}
