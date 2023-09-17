package com.prudhvi.dynamic_programming.unbounded_knapsack;

import java.util.Arrays;
/*
	Problem Description
		Given a knapsack weight A and a set of items with certain value B[i] and weight C[i], we need to calculate maximum amount that could fit in this quantity.
		This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.
	Problem Constraints
		1 <= A <= 1000
		1 <= |B| <= 1000
		1 <= B[i] <= 1000
		1 <= C[i] <= 1000
 */
public class UnboundedKnapsack {
	public static void main(String[] args) {
		int capacity=7;
		int[] value= {10,40,50};
		int[] weights= {3,3,4};
		int max=unboundedKnapsack(capacity,value,weights);
		System.out.println(max);
		int maxMemo=memoUnboundedKnapsack(capacity, value, weights);
		System.out.println(maxMemo);

	}
	/*
 	Time Complexity (unboundedKnapsack function):
					1.Initialization of dp Array: 
						You initialize an array dp of size capacity + 1. This initialization step takes O(capacity) time since you fill the array with initial values.
					2.Nested Loops: 
						The core of this function consists of two nested loops. The outer loop (for i) runs from 1 to capacity, and the inner loop (for j) iterates through all items.
							1.The outer loop runs for 'capacity' iterations.
							2,The inner loop runs for 'w.length' iterations, where 'w.length' is the number of items.
					3.Inside the inner loop, you perform constant-time operations.
			So, the total number of iterations is roughly proportional to 'capacity * w.length'. Therefore, the overall time complexity of the unboundedKnapsack function is O(capacity * w.length).
	Space Complexity (unboundedKnapsack function):
					1.dp Array: You create an array dp of size capacity + 1 to store the results of subproblems. This array consumes O(capacity) space.
					2.Other variables and constants use negligible space compared to the dp array.					
				Therefore, the overall space complexity of the unboundedKnapsack function is O(capacity) due to the dp array.
					
			In summary, the unboundedKnapsack function has a time complexity of O(capacity * w.length) and a space complexity of O(capacity) due to the dynamic programming table.				
						 
	 */
	// It takes three parameters: capacity (the maximum weight the knapsack can carry), v (an array representing the values of items), and w (an array representing the weights of items).
	private static int unboundedKnapsack(int capacity, int[] v, int[] w) {
		//This line creates an array dp to store the maximum values that can be achieved for different capacities (from 0 to capacity). The length of the array is capacity + 1 to account for a capacity of zero.
		int[] dp=new int[capacity+1];
		dp[0]=0;// Initialize the value at index 0 in the dp array to 0 because no value can be achieved with a knapsack of zero capacity.
		//This loop iterates through all possible capacities from 1 to capacity.
		for(int i=1;i<=capacity;i++) {
			//Initialize a variable max to the minimum possible integer value. This variable will be used to keep track of the maximum value that can be achieved for the current capacity i.
			int max=Integer.MIN_VALUE;
			//his nested loop iterates through all the items in the given list of items, where w[j] represents the weight of the j-th item.
			for(int j=0;j<w.length;j++) {
				//Check if the current capacity i is greater than or equal to the weight of the current item w[j]. If the item can fit into the knapsack without exceeding the capacity, proceed.
					if(i>=w[j]) {
						//Calculate the value that can be achieved by adding the value of the current item v[j] to the maximum value achieved for the remaining capacity i - w[j]. dp[i - w[j]] represents the maximum value achieved for the remaining capacity.
						max = Math.max(max, v[j] + dp[i - w[j]]);
					}
			}
			//Update the dp array for the current capacity i with the maximum value max calculated in the inner loop. This represents the maximum value that can be achieved for the current capacity.
			dp[i]=max;
		}
		//Finally, after both loops are completed, the method returns dp[capacity], which represents the maximum value that can be achieved with the given capacity.
		return dp[capacity];
	}
	private static int memoUnboundedKnapsack(int capacity, int[] v, int[] w) {
	    int[] dp = new int[capacity + 1];
	    Arrays.fill(dp, -1);
	    memoizedKnapsack(capacity, v, w, dp);
	    return memoizedKnapsack(capacity, v, w, dp);
	    
	}

	private static int memoizedKnapsack(int capacity, int[] v, int[] w, int[] dp) {
	    if (capacity == 0) {
	        return 0;
	    }
	    if (dp[capacity] != -1) {
	        return dp[capacity];
	    }
	    int max = Integer.MIN_VALUE;
	    for (int i = 0; i < w.length; i++) {
	        if (capacity >= w[i]) {
	            max = Math.max(max, v[i] + memoizedKnapsack(capacity - w[i], v, w, dp));
	        }
	    }
	    dp[capacity] = max;
	    return dp[capacity];
	}

}
