package com.prudhvi.dynamic_programming.unbounded_knapsack;

public class LetsParty {

	public static void main(String[] args) {
		int noOfFriends=5;//26;
		int ways=waysToParty(noOfFriends);
		System.out.println(ways);

	}
	/*
	Time Complexity:
		The time complexity of this solution is O(n) because it iterates through the friends from 2 to noOfFriends once.
	Space Complexity:
		The space complexity is O(n) because of the dp array of size noOfFriends + 1.
	 */
	private static int waysToParty(int noOfFriends) {
		int[] dp=new int[noOfFriends+1];
		//It sets dp[0] and dp[1] to 1. This is the base case, indicating that there is only one way to arrange a party with 0 or 1 friend (no arrangements to be made).
		dp[0]=dp[1]=1;
		//It uses a loop starting from 2 (since we have already set the base cases). For each i from 2 to noOfFriends, it calculates dp[i] as follows:
		for (int i = 2; i < dp.length; i++) {
			//The term dp[i-1] represents the case where the ith friend sits alone, and we calculate the number of ways to arrange the remaining i-1 friends.
			//The term (i-1) * dp[i-2] represents the case where the ith friend sits with one of the previous i-1 friends, and we calculate the number of ways to arrange the remaining i-2 friends. 
			  //We multiply by (i-1) because there are i-1 choices for the friend to sit with.
			dp[i]=dp[i-1]+((i-1)*dp[i-2]);
		}
		return dp[noOfFriends];
	}

}
