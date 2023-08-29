package com.prudhvi.dynamic_programming.one_dimensional;

public class Fibonacci {
	/*
	Problem Description
		Given a positive integer A, write a program to find the Ath Fibonacci number.
		In a Fibonacci series, each term is the sum of the previous two terms and the first two terms of the series are 0 and 1. i.e. f(0) = 0 and f(1) = 1. Hence, f(2) = 1, f(3) = 2, f(4) = 3 and so on.
		NOTE: 0th term is 0. 1th term is 1 and so on.
	Problem Constraints
		0 <= A <= 44
	 */
	public static void main(String[] args) {
		int number=44;
		//TC=O(n) & SC=O(1) Tabulation
		int fibCSC=fibonacciWithConstantSc(number);
		System.out.println(fibCSC);
		//TC=O(n) & SC=O(n) Tabulation
		int fibDp=fibonacciDpArr(number);
		System.out.println(fibDp);
		//TC=O(n) & SC=O(n(stack)+n(arr)) Memoization
		int[] dp=new int[number+1];
		for(int i=0;i<dp.length;i++) {
			dp[i]=-1;
		}
		int fibMemo=fibonacciDpMemo(number,dp);
		System.out.println(fibMemo);
		//TC=O(2^n) and SC=O(n)
		int fib=fibonacciBruteForce(number);
		System.out.println(fib);
		

	}
	private static int fibonacciWithConstantSc(int number) {
		if(number<=1) {
			return number;
		}
		int previous=0;
		int nextPrevious=1;
		int fib=-1;
		for(int i=2;i<=number;i++) {
			fib=previous+nextPrevious;
			previous=nextPrevious;
			nextPrevious=fib;
		}
		return fib;
	}

	private static int fibonacciDpArr(int number) {
		if(number<=1) {
			return number;
		}
		int[] dp=new int[number+1];
		dp[0]=0;
		dp[1]=1;
		for(int i=2;i<=number;i++) {
			dp[i]=dp[i-1]+dp[i-2];
		}
		return dp[number];
	}

	private static int fibonacciDpMemo(int number,int[] dp) {
		if(number<=1) {
			return number;
		}
		if(dp[number]==-1) {
			dp[number]=fibonacciDpMemo(number-1, dp)+fibonacciDpMemo(number-2, dp);
		}
		return dp[number];
	}

	/*
	Time Complexity:
			The recurrence relation T(n) = T(n-1) + T(n-2) represents the time complexity of the algorithm.
			In each recursive call, two more recursive calls are made (one for number - 1 and one for number - 2).
		As a result, the time complexity of this approach grows exponentially with number. It's roughly O(2^n), which is highly inefficient for large values of number.
	Space Complexity:
		The space complexity of the algorithm depends on the depth of the recursion stack.
		In the worst case, the recursion stack depth is number, which means the space complexity is also O(number).
	Note:
		While this brute force approach is simple, it becomes impractical for calculating Fibonacci numbers for large values of number due to its exponential time complexity. 
		For better performance, iterative and memoization (dynamic programming) approaches are commonly used above .
	 */
	private static int fibonacciBruteForce(int number) {
		if(number<=1) {
			return number;
		}
		return fibonacciBruteForce(number-1)+fibonacciBruteForce(number-2);
	}
	

}
