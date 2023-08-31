package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.Arrays;

public class FriendsPairing {
	/*
	Given N friends, each one can remain single or can be paired up with some other friend. Each friend can be paired only once. Find out the total number of ways in which friends can remain single or can be paired up.
	Note: Since answer can be very large, return your answer mod 10^9+7.
	our Task:
		You don't need to read input or print anything. Your task is to complete the function countFriendsPairings() which accepts an integer n and return number of ways in which friends can remain single or can be paired up.
			Expected Time Complexity: O(N)
			Expected Auxiliary Space: O(1)
	Constraints:
	1 ≤ N ≤ 104
	 */
	public static void main(String[] args) {
		int numberOfFriends=32;
		long pairs=pairsCount(numberOfFriends);
		System.out.println(pairs);
		long pair=countPair(numberOfFriends);
		System.out.println(pair);
		long pairMemo=pairsCountMemo(numberOfFriends);
		System.out.println(pairMemo);

	}
	//TC=O(N), SC=O(1);
	private static long countPair(int noOfFriends) {
		long mod=1000000007;
		if(noOfFriends<=2) {
			return 1;
		}
		long a=1;
		long b=2;
		for(int i=3;i<=noOfFriends;i++) {
			long staySingle=b;
			long pairWithOthers=((i-1)*(a))%mod;
			long c=(staySingle+pairWithOthers)%mod;
			a=b;
			b=c;
		}
		return b;
	}
	/*
	Code calculates the number of ways to form pairs among a group of friends in such a way that each friend can either be alone or form a pair with another friend. 
	The code uses dynamic programming to efficiently compute the number of possible pairings.
	
	Time Complexity:
		The dynamic programming loop iterates through all values from 2 to the given numberOfFriends once.
		Therefore, the time complexity of this approach is O(numberOfFriends).
	Space Complexity:
		The space complexity is O(numberOfFriends) due to the dp array used to store the number of ways to pair up each number of friends.
	 */
	private static long pairsCount(int numberOfFriends) {
		int mod=1000000007;//A constant modulo value used to prevent integer overflow during calculations.
		//If the number of friends is 1, the method returns 1. If the number of friends is 2, the method returns 2.
		//These are base cases that directly account for these specific situations.
		if(numberOfFriends<=2) {
			return numberOfFriends==1?1:2;
		}
		//Initialize an array dp of size numberOfFriends + 1 to store the number of ways to form pairs for each possible count of friends.
		long[] dp=new long[numberOfFriends+1];
		//Initialize dp[0] and dp[1] to 1. These represent the number of ways to form pairs with 0 and 1 friend respectively.
		dp[0]=1;
		dp[1]=1;
		//oop through the array dp starting from index 2 (since base cases are already set).
		for(int i=2;i<dp.length;i++) {
			//Calculate saySingle as the number of ways to keep the current friend single, which is simply the value of dp[i - 1].
			long saySingle=dp[i-1];
			//Calculate pairWithOther as the number of ways to form a pair with another friend, which is (i - 1) * dp[i - 2] (as there are i - 1 possible friends to pair with and dp[i - 2] ways to form pairs with the remaining friends).
			long pairWithOther=((i-1)*dp[i-2])%mod;
			//dp[i]=dp[i-1]+(i-1)*dp[i-2] dp Expression
			dp[i]=(saySingle+pairWithOther)%mod;
			
		}
		return dp[numberOfFriends];
	}
	//memo
	private static long pairsCountMemo(int numberOfFriends) {
	    int mod = 1000000007;
	    if (numberOfFriends <= 2) {
	        return numberOfFriends == 1 ? 1 : 2;
	    }
	    
	    long[] memo = new long[numberOfFriends + 1];
	    Arrays.fill(memo, -1);
	    
	    return memoPairsCount(numberOfFriends, memo, mod);
	}

	private static long memoPairsCount(int n, long[] memo, int mod) {
	    if (n <= 2) {
	        return n == 1 ? 1 : 2;
	    }
	    
	    if (memo[n] != -1) {
	        return memo[n];
	    }
	    
	    long saySingle = memoPairsCount(n - 1, memo, mod);
	    long pairWithOther = ((n - 1) * memoPairsCount(n - 2, memo, mod)) % mod;
	    
	    memo[n] = (saySingle + pairWithOther) % mod;
	    return memo[n];
	}


}
