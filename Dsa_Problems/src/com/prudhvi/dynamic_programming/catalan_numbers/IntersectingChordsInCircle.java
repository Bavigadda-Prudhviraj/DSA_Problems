package com.prudhvi.dynamic_programming.catalan_numbers;

import java.util.Arrays;

public class IntersectingChordsInCircle {

	public static void main(String[] args) {
		int num=8;
		int nthCatalan=nThCatalanNum(num);
		System.out.println(nthCatalan);

	}
	/*
	Time Complexity: The code uses a nested loop to calculate each Catalan number from 2 to num, so the time complexity is O(n^2), where n is the input num.

	Space Complexity: The code uses an integer array of size num + 1 to store the Catalan numbers, resulting in a space complexity of O(num).
	 */
	private static int nThCatalanNum(int num) {
		//It starts by initializing an integer array dp of size num + 1. This array will be used to store the Catalan numbers.
		int[] dp=new int[num+1];
		//It sets dp[0] and dp[1] to 1. These are the base cases for Catalan numbers.
		dp[0]=1;dp[1]=1;
		//
		for(int i=2;i<=num;i++) {
			int c=i-1;
			int sum=0;
			//Inside the loop, for each i, it calculates the Catalan number by summing up products of two Catalan numbers from the previous iterations. It uses two indices, j and c, to select pairs of previously calculated Catalan numbers. It calculates dp[i] as the sum of (dp[j] * dp[c]) for all j from 0 to i - 1, where c starts at i - 1 and decreases by 1 in each iteration of the inner loop.
			for(int j=0;j<i;j++) {
				sum=sum+(dp[j]*dp[c]);
				c--;
			}
			//After computing dp[i], it stores this value in the dp array.
			dp[i]=sum;
		}
		System.out.println(Arrays.toString(dp));
		//Finally, the code prints the entire dp array for debugging purposes and returns dp[num], which is the nth Catalan number.
		return dp[num];
	}

}
