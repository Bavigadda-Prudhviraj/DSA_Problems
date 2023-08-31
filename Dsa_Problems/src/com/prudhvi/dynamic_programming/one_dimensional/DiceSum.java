package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.Arrays;

public class DiceSum {
	/*
	Problem Description
		You rolled a dice K times and got a sum of A after summing all the values you got after a roll.
		Find the number of ways you could have got a sum of A after rolling K times, since this value can be very large return modulo 109+7.
	Problem Constraints
		1 <= A <= 106
	 */
	public static void main(String[] args) {
		int sum=7;
		int noOfways=diceSumDpEquation(sum);
		System.out.println(noOfways);
		int noOfwaysTogetSum=diceSum(sum);
		System.out.println(noOfwaysTogetSum);
		int waysMemo=diceSumMemo(sum);
		System.out.println(waysMemo);

	}
	//TC(N) SC(1)
	private static int diceSumDpEquation(int sum) {
		int mod=1000000007;
		if(sum==0) {
			return 1;
		}
		long[] dp=new long[sum+1];
		dp[0]=1;
		for(int i=1;i<=sum;i++) {
			long currentSum=0;
			for(int j=1;j<=6 && j<=i;j++) {
				currentSum=(currentSum+dp[i-j])%mod;
			}
			dp[i]=currentSum;
		}
		return (int)dp[sum];
	}
	/*
	
	Time Complexity:
		The dynamic programming loop iterates through all values from 6 to the given sum once.
		Therefore, the time complexity of this approach is O(sum).
	Space Complexity:
		The space complexity is O(sum) due to the dp array used to store the number of ways for each sum value.
	 */
	private static int diceSum(int sum) {
		int mod=1000000007;//A constant modulo value used to prevent integer overflow during calculations.
		//If the desired sum is less than or equal to 5, the code directly returns the value from a predefined array dp containing the number of combinations for sums 0 to 5. 
		//This is because the number of combinations for these smaller sums can be determined directly.
		if(sum<=5) {
			int[] dp=new int[6];
			dp[0]=1;dp[1]=1;dp[2]=2;dp[3]=4;dp[4]=8;dp[5]=16;
			return dp[sum];
		}
		int[] dp=new int[sum+1];//Otherwise, the code initializes an array dp of size sum + 1 to store the number of combinations for each sum from 0 to the desired sum.
		dp[0]=1;dp[1]=1;dp[2]=2;dp[3]=4;dp[4]=8;dp[5]=16;//Initialize the values of dp for sums 0 to 5 using the same predefined values: 1, 1, 2, 4, 8, 16. These values represent the number of possible combinations for rolling a sum of each index.
		//Starting from i = 6, loop through until i reaches the desired sum.
		for(int i=6;i<=sum;i++) {
			//dp[i]=dp[i-1]+dp[i-2]+dp[i-3]+dp[i-4]+dp[i-5]+dp[i-6]; The formula represents the sum of the combinations from the previous six possible dice rolls.
			//For each i, calculate dp[i] by summing the values of the previous six indices in dp, using modulo operations to prevent integer overflow.
			dp[i]= (((dp[i-1]+dp[i-2])%mod + (dp[i-3]+dp[i-4])%mod)%mod + (dp[i-5]+dp[i-6])%mod)%mod ;
		}
		return dp[sum];
	}
	//memo
	private static int diceSumMemo(int sum) {
	    int mod = 1000000007;
	    long[] memo = new long[sum + 1];
	    Arrays.fill(memo, -1);
	    return (int) memoDiceSum(sum, memo, mod);
	}

	private static long memoDiceSum(int sum, long[] memo, int mod) {
	    if (sum == 0) {
	        return 1;
	    }
	    
	    if (memo[sum] != -1) {
	        return memo[sum];
	    }
	    
	    long currentSum = 0;
	    for (int j = 1; j <= 6 && j <= sum; j++) {
	        currentSum = (currentSum + memoDiceSum(sum - j, memo, mod)) % mod;
	    }
	    
	    memo[sum] = currentSum;
	    return currentSum;
	}
	





}
