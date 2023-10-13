package com.prudhvi.dynamic_programming.one_dimensional;
public class MinCostClimbingStairs {
/*
	You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
	You can either start from the step with index 0, or the step with index 1.
	Return the minimum cost to reach the top of the floor.
 */
	public static void main(String[] args) {
		int[] cost= {10,15,20};
		int minCost=minCostClimbingStairs(cost);
		System.out.println("mincost "+minCost);

	}
	/*
	->The key idea here is that we're leveraging dynamic programming to build a table (dp) where each entry represents the minimum cost to reach a particular step. 
	  By calculating and storing the minimum cost for each step incrementally, we ensure that we consider all possible paths and make optimal choices to minimize the total cost.
	->The final answer, found in dp[n], represents the minimum cost to climb the entire staircase, considering the cost associated with each step. 
	  This code is efficient and ensures that we explore and evaluate only the necessary subproblems, making it a classic example of dynamic programming.
	Time Complexity:
		The code uses a single loop that iterates through all steps, from 2 to n. So, the time complexity is O(n).
	Space Complexity:
		The code uses an additional array dp of size n + 1, so the space complexity is O(n) to store the dynamic programming table.
	In summary, the code efficiently finds the minimum cost to climb the stairs using dynamic programming with a time complexity of O(n) and a space complexity of O(n).
	 */
	private static int minCostClimbingStairs(int[] cost) {
		int n = cost.length;  // The total number of steps (including the base and the top)
	    int[] dp = new int[n + 1]; // Create an array to store minimum costs for reaching each step
	    for (int i = 2; i <= n; i++) {
	        dp[i] =Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
	        // dp[i] represents the minimum cost to reach step i
	        // The minimum cost is either coming from step i-1 or step i-2
	    }
	    return dp[n]; // The minimum cost to reach the top (step n)
	}
	/*
	Explanation of the code:
			1.The minCostClimbingStairs function takes an array cost as input, where cost[i] represents the cost of climbing the i-th step.
			2.It calculates the total number of steps, including the base (step 0) and the top (step n). n is the length of the cost array.
			3.It creates an array dp of size n + 1 to store the minimum cost required to reach each step.
			4.The loop starts from step 2 (since reaching the first and second steps costs nothing, and we initialize dp[0] and dp[1] to 0).
			5.For each step i, it calculates the minimum cost to reach that step by considering two options:
				i.dp[i - 1] + cost[i - 1]: The cost of reaching step i from step i - 1.
				ii.dp[i - 2] + cost[i - 2]: The cost of reaching step i from step i - 2.
			6.It stores the minimum cost in dp[i].
			7.After the loop, dp[n] represents the minimum cost to reach the top step, which is the answer to the problem.
	 */

}
