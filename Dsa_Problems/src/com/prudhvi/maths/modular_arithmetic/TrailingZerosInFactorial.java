package com.prudhvi.maths.modular_arithmetic;

public class TrailingZerosInFactorial {
	/*
	Problem Description
		Given an integer A, return the number of trailing zeroes in A! i.e., factorial of A.
	Note: 
		Your solution should run in logarithmic time complexity.

	Problem Constraints
		1 <= A <= 109
	 */

	public static void main(String[] args) {
		int num=100;
		int trailingZeros=trailingZeroes(num);
		System.out.println(trailingZeros);
	}
	/*
	The trailingZeroes method calculates the number of trailing zeros in the factorial of a given integer A. 
	It uses a simple iterative approach to count the number of factors of 5 in the numbers from 1 to A. 
	The method returns the count of trailing zeros.
	
	Time Complexity:
		The time complexity of this code is O(log ₅ num) O(log base5 num), where A is the input integer. 
		The loop iterates until A becomes less than powerOf5, which happens when powerOf5 exceeds A. 
		The number of iterations required to reach this point is logarithmic in A with base 5.
	
	Space Complexity:
		The space complexity of this code is O(1) because it uses a constant amount of additional space to store the variables answer and powerOf5.
	 */
	public static int trailingZeroes(int num) {
        int answer=0;
        int powerOf5=5;
        //he code enters a loop while A is greater than or equal to powerOf5.
        while(num>=powerOf5){
        	//it calculates the number of occurrences of powerOf5 as a factor in num by dividing num by powerOf5 and adding the result to the answer variable. 
            //This accounts for the number of trailing zeros that are multiples of 5.
            answer=answer+(num/powerOf5);
            powerOf5=powerOf5*5;//variable is updated by multiplying it by 5, increasing its value for the next iteration to consider the next power of 5.
        //Once num becomes less than powerOf5, the loop exits.
        }
        return answer;
    }

}
