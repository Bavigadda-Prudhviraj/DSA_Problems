package com.prudhvi.dynamic_programming.unbounded_knapsack;

import java.util.Arrays;

public class CoinSumInfinite {

	public static void main(String[] args) {
		int[] coins = {1, 2, 3};
		int	 amount = 4;
		int coinsUniqueCombinations=coinsUniqueCombinatios(coins,amount);
		System.out.println(coinsUniqueCombinations);
		int coinsAllConbination=coinsAllCombinations(coins, amount);
		System.out.println(coinsAllConbination);
	}
	/*
	Code is designed to calculate the number of unique combinations to make a given amount using a given set of coins. 
	Time Complexity:
		The function uses two nested loops, resulting in a time complexity of O(amount * number of coin denominations).
	Space Complexity:
		The function uses an array dp of size amount + 1, resulting in a space complexity of O(amount).
	
	 */
	//unique Combination tabulation
	private static int coinsUniqueCombinatios(int[] coins, int amount) {//for example 4=3+1 && 1+3 are same
		
		//It initializes an integer array dp of size amount + 1. dp[i] will store the number of unique combinations to make the amount i.
		int[] dp=new int[amount+1];
		//It sets dp[0] to 1 because there is one way to make change for an amount of 0, which is by using no coins.
		dp[0]=1;
		for(int i=0;i<coins.length;i++) {
			for(int j=0;j<=amount;j++) {
				//it checks whether it's possible to use the current coin to make change for the current amount (j).
				if(j-coins[i]>=0){// it means the current coin can be used without exceeding the current amount.
					//it updates dp[j] by adding the value of dp[j - coins[i]]. 
					//This is because the number of ways to make the current amount j using the current coin is equal to the number of ways to make the remaining amount (j - coins[i]) using the same coin plus the number of ways to make the current amount j using other coins.
					dp[j]+=dp[j-coins[i]];
				}
			}
		}
		return dp[amount];
	}
	//unique Combination memorization
	public static int coinsUniqueCombinationsMemo(int[] coins, int amount) {
	    int[] dp = new int[amount + 1];
	    Arrays.fill(dp, -1);
	    return memoizedUniqueCombinations(coins, amount, dp);
	}
	private static int memoizedUniqueCombinations(int[] coins, int amount, int[] dp) {
	    if (amount == 0) {
	        return 1;
	    }
	    
	    if (dp[amount] != -1) {
	        return dp[amount];
	    }
	    
	    int combinations = 0;
	    for (int coin : coins) {
	        if (amount >= coin) {
	            combinations += memoizedUniqueCombinations(coins, amount - coin, dp);
	        }
	    }
	    dp[amount] = combinations;
	    return combinations;
	}
	//All Combination tabulation  example 4=3+1 && 1+3 are not same
	private static int coinsAllCombinations(int[] coins,int amount){
		int[] dp=new int[amount+1];
		dp[0]=1;
		for(int i=1;i<=amount;i++) {
			for(int coin=0;coin<coins.length;coin++) {
				if(i-coins[coin]>=0) {
					dp[i]+=dp[i-coins[coin]];
				}
			}
		}
		return dp[amount];
		
	}
	//all combination memorization
	public static int coinsAllCombinationsMemo(int[] coins, int amount) {
	    int[] memo = new int[amount + 1];
	    Arrays.fill(memo, -1);
	    return memoizedAllCombinations(coins, amount, memo);
	}

	private static int memoizedAllCombinations(int[] coins, int amount, int[] memo) {
	    if (amount == 0) {
	        return 1;
	    }
	    if (memo[amount] != -1) {
	        return memo[amount];
	    }
	    int combinations = 0;
	    for (int coin : coins) {
	        if (amount >= coin) {
	            combinations += memoizedAllCombinations(coins, amount - coin, memo);
	        }
	    }
	    memo[amount] = combinations;
	    return combinations;
	}

	

}
