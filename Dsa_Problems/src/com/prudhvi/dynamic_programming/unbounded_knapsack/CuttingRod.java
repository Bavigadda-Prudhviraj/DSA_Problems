package com.prudhvi.dynamic_programming.unbounded_knapsack;

import java.util.Arrays;

/*
	Problem Description
		Given a rod of length N units and an array A of size N denotes prices that contains prices of all pieces of size 1 to N.
		Find and return the maximum value that can be obtained by cutting up the rod and selling the pieces.
	Problem Constraints
		1 <= N <= 1000
		0 <= A[i] <= 106
 */
public class CuttingRod {

	public static void main(String[] args) {
		int[] arr= {0,1,4,2,5,6};//3,4,1,6,2
		int maxProfit2=cuttingRod2(arr);
		System.out.println(maxProfit2);
		int maxProfit=cuttingRod(arr);
		System.out.println(maxProfit);
		int maxProfitMemo=cuttingRodMemo(arr);
		System.out.println(maxProfitMemo);

	}
	/*
	Code implementing the Rod Cutting problem using dynamic programming. 
	The goal of this problem is to maximize the value obtained by cutting a rod of length n into smaller pieces of given lengths and values. 
	Each piece has an associated value, and the objective is to find the optimal way to cut the rod to maximize the total value.
	
	Time Complexity:
		The function uses two nested loops, resulting in a time complexity of O(n^2), where n is the length of the rod.
	Space Complexity:
		The function uses an array dp of size n+1, resulting in a space complexity of O(n).
		*/
	public static int cuttingRod2(int[] arr){
		int n=arr.length;
		////It starts by defining an array dp of size n + 1, where n is the length of the rod. The dp array will store the maximum revenue that can be obtained for each rod length from 0 to n.
		int[] dp=new int[n+1];
		dp[0]=0;//It initializes dp[0] to 0 because no revenue can be obtained from a rod of length 0.
		//It enters a loop from i = 1 to n, where i represents the current rod length under consideration.
		for(int i=1;i<=n;i++) {
			//Inside the loop, it initializes dp[i] with the revenue obtained by selling the entire rod of length i. 
			//This value is obtained from the price list, and it uses arr[i-1] because arrays are 0-indexed while rod lengths are 1-indexed.
			dp[i]=arr[i-1];
			for(int j=1;j<i;j++) {//It then enters another loop from j = 1 to i - 1, where j represents the length of the cut piece of the rod.
				//Inside this inner loop, it considers the possibility of cutting the rod into two pieces: 
				//one of length j and the other of length i - j. 
				//It calculates the total revenue by adding the revenue from selling these two pieces, i.e., dp[i - j] + arr[j - 1]. 
				//It compares this value with the current value of dp[i] and updates dp[i] to the maximum of these two values. 
				//This step effectively checks all possible ways of cutting the rod and selects the one that maximizes revenue.
				dp[i]=Math.max(dp[i],dp[i-j]+arr[j-1]);
			}	
		}
		return dp[n];
	}

	private static int cuttingRod(int[] arr) {
		
		int n=arr.length;
		int[] dp=new int[n+1];
        dp[0]=0;
        
        for(int i=1;i<=n;i++) {
        	//Inside the loop, it initializes dp[i] with the revenue obtained by selling the entire rod of length i. This value is obtained from the price list, and it uses arr[i-1] because arrays are 0-indexed while rod lengths are 1-indexed.
            int sum=arr[i-1];
            int cnt=i-1;
            for(int j=1;j<i;j++) {
                int profit=arr[cnt-1]+dp[j];
                sum=Math.max(sum, profit);
                cnt--;
            }
            dp[i]=sum;
        }
        return dp[n];
	}
	//Memorization
	public static int cuttingRodMemo(int[] arr) {
	    int n = arr.length;
	    int[] memo = new int[n + 1];
	    Arrays.fill(memo, -1);
	    return memoizedCuttingRod(arr, n, memo);
	}
	private static int memoizedCuttingRod(int[] arr, int n, int[] memo) {
	    if (n == 0) {
	        return 0;
	    }
	    if (memo[n] != -1) {
	        return memo[n];
	    }
	    int maxVal = Integer.MIN_VALUE;
	    for (int i = 1; i <= n; i++) {
	        maxVal = Math.max(maxVal, arr[i - 1] + memoizedCuttingRod(arr, n - i, memo));
	    }
	    memo[n] = maxVal;
	    return maxVal;
	}

	
}
