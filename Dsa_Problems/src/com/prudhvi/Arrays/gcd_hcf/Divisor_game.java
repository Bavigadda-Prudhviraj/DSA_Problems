package com.prudhvi.Arrays.gcd_hcf;
/*
	Problem Description
		Scooby has 3 three integers A, B, and C.
		Scooby calls a positive integer special if it is divisible by B and it is divisible by C. 
		You need to tell the number of special integers less than or equal to A.
	
	Problem Constraints
		1 <= A, B, C <= 109
	
	Input Format
		First argument is a positive integer A
		Second argument is a positive integer B
		Third argument is a positive integer C
	
	Output Format
		One integer corresponding to the number of special integers less than or equal to A.
*/
public class Divisor_game {

	public static void main(String[] args) {
		int a=12;
		int b=3;
		int c=2;
		int ans=divisorGame(a, b, c);
		System.out.println(ans);

	}
	/*
		The method defines two functions, divisorGame and gcd, for solving a problem related to the Greatest Common Divisor (GCD) and Lowest Common Multiple (LCM). 
		The code calculates the GCD of two integers B and C, and then uses the GCD to find the LCM. 
		Finally, it computes and returns a result based on the values of A, B, and C. 
		
		Time Complexity:-
			The overall time complexity of the divisorGame function is determined by the time complexity of the gcd function, which is O(log(max(B, C)).

		Space Complexity:-
			The space complexity of both functions is O(1) because they use only a constant amount of additional memory for variables and function call stack.
	 */
	//In this function, it first calculates the GCD of B and C and stores it in the variable Gcd. 
	//Then, it computes the LCM of B and C by dividing their product by the GCD. 
	//Finally, it returns the result of A divided by the LCM.
	public static int divisorGame(int A, int B, int C) {
        int Gcd=gcd(B,C);
        int lcm=B*C/Gcd;
        return A/lcm;
    }
	//This function calculates the GCD of two integers a and b using the Euclidean algorithm. 
	//The time complexity of this function is O(log(max(a, b)) because it makes recursive calls until one of the numbers becomes zero. 
	//The number of recursive calls is proportional to the number of bits required to represent the larger of the two numbers.
    public static int gcd(int a,int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }

}
