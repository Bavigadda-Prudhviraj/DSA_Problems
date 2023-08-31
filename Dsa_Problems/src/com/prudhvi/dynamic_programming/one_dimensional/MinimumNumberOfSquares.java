package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.Arrays;

public class MinimumNumberOfSquares {
	/*
	Problem Description
		Given an integer A. Return minimum count of numbers, sum of whose squares is equal to A.
	Problem Constraints
		1 <= A <= 105
	 */
	public static void main(String[] args) {
		int number=6;
		int noOfperfectSquares=minPerfectSquares(number);
		System.out.println(noOfperfectSquares);
		int minPerfectSquares=minPerfectSquaresMemo(number);
		System.out.println(minPerfectSquares);
		

	}
	/*
	The code find the minimum number of perfect square numbers that sum up to a given input number. 
	For example, if the input number is 12, the function would return 2, as 12 can be expressed as 4 (2^2) + 4 (2^2) = 8.
	
	Time Complexity:
		where the outer loop runs up to number, and for each iteration, the inner loop runs up to the square root of number.
		The nested loops contribute to a time complexity of O(number * sqrt(number)).
	Space Complexity:
		The space complexity is O(number) due to the dp array.
	 */
	private static int minPerfectSquares(int number) {
		//Base Case (number == 1): If the input number is 1, then the minimum number of perfect squares required is 1, which is the number itself. So, the function returns 1.
		if(number==1) {
			return 1;
		}
		int[] dp=new int[number+1];//dp array of size number + 1 to store the minimum number of perfect squares needed to sum up to each index.
		//Initialize all elements of the dp array to Integer.MAX_VALUE,
		Arrays.fill(dp,Integer.MAX_VALUE);
		//except dp[0] which is set to 0 (0 perfect squares needed to sum to 0) and dp[1] which is set to 1.
		dp[0]=0;dp[1]=1;
		for(int i=2;i<=number;i++) {
			//Iterate over all possible perfect square numbers x * x where x * x <= i.
			for(int x=1;x*x<=i;x++) {
				//Update dp[i] by taking the minimum of its current value and dp[i - (x * x)] + 1. 
				//This essentially means trying to use each perfect square number as a summand and checking how many perfect squares were needed to sum up to i - (x * x), then adding 1 for the current perfect square.
				dp[i]=Math.min(dp[i],dp[i-(x*x)]+1);
			}
		}
		return dp[number];
	}
	private static int minPerfectSquaresMemo(int number) {
	    int[] dp = new int[number + 1];      
	    Arrays.fill(dp, -1);
	    return memorizeSquares(number, dp);
	}
	private static int memorizeSquares(int n, int[] dp) {
	    if (n == 0) {
	        return 0;
	    }
	    if (dp[n] != -1) {
	        return dp[n];
	    }
	    int minSquares = Integer.MAX_VALUE;
	    for (int x = 1; x * x <= n; x++) {
	        int currentSquares = memorizeSquares(n - x * x, dp) + 1;
	        minSquares = Math.min(minSquares, currentSquares);
	    }
	    dp[n] = minSquares;
	    return minSquares;
	}
	

}
