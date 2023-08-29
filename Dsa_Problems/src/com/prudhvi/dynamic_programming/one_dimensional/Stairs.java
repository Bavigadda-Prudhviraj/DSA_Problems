package com.prudhvi.dynamic_programming.one_dimensional;

public class Stairs {
	/*
	Problem Description
		You are climbing a staircase and it takes A steps to reach the top.
		Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
		Return the number of distinct ways modulo 1000000007
	Problem Constraints
		1 <= A <= 105
	 */
	public static void main(String[] args) {
		int staiers=55007;
		int noOfWays=countWays(staiers);
		System.out.println(noOfWays);
		int noOfWay=countWaysDpArr(staiers);
		System.out.println(noOfWay);

	}
	/*
	The  code is an alternate implementation of calculating the number of distinct ways to climb a staircase with a given number of stairs, using constant space. 
	Similar to the below code(countWay), this implementation also uses dynamic programming and the concept of Fibonacci sequence.
	
	Time Complexity:
			The loop runs for stairs - 2 iterations, as the base cases (firstStair and secondStair) are already initialized.
			Each iteration involves constant time operations, so the time complexity is O(stairs).
	Space Complexity:
			The implementation uses only a constant amount of space for the variables (firstStair, secondStair, nthStair, and loop counter i).
			The space complexity is O(1), indicating constant space usage.
	 */
	private static int countWays(int staiers) {
		//If there is only one stair (stairs == 1), there is only one way to reach the top (take one step).
		if(staiers==1) {
			return 1;
		}
		//The variables firstStair and secondStair represent the number of ways to reach the current step and the step just before, respectively.
		long firstStair=1;
		long secondStaier=2;
		long nthStaier=-1;
		//The loop runs from i = 3 to stairs, where i represents the current step.
		for(int i=3;i<=staiers;i++) {
			//The recurrence relation nthStair = firstStair + secondStair calculates the number of ways to reach step i.
			nthStaier=(firstStair+secondStaier)%1000000007;
			//After each iteration, the values of firstStair and secondStair are updated to prepare for the next iteration.
			firstStair=secondStaier;
			secondStaier=nthStaier;
		}
        //After the loop, secondStair contains the number of ways to reach the top of the staircase.
		return (int)nthStaier;
	}
	/*
	The code calculates the number of distinct ways to climb a staircase with a given number of stairs using dynamic programming. 
	The goal is to calculate the number of ways to reach the top of the staircase, where you can either take one or two steps at a time.
	
	Time Complexity:
		The loop runs for stairs - 2 iterations, as the base cases (dp[1] and dp[2]) are already initialized.
		Each iteration involves constant time operations, so the time complexity is O(stairs).
	Space Complexity:
		The dp array is used to store the number of ways for each step.
		The space complexity is O(stairs) to store the dp array.
	 */
	private static int countWaysDpArr(int staiers) {
		//If there is only one stair (stairs == 1), there is only one way to reach the top (take one step).
		if(staiers==1) {
			return 1;
		}
		long[] dp=new long[staiers+1];//The dp array is used to store the number of ways to reach each step.
		//The base cases are set: dp[1] = 1 and dp[2] = 2.
		dp[1]=1;
		dp[2]=2;
		//The loop runs from i = 3 to stairs, where i represents the current step.
		for(int i=3;i<=staiers;i++) {
			//The recurrence relation dp[i] = dp[i - 1] + dp[i - 2] calculates the number of ways to reach step i.
			dp[i]=(dp[i-1]+dp[i-2])%1000000007;//Since the number of ways can become large, each dp[i] is taken modulo 1000000007 to avoid overflow.
		}
		//After the loop, dp[stairs] contains the number of ways to reach the top of the staircase.
		return (int)dp[staiers];
	}

}
