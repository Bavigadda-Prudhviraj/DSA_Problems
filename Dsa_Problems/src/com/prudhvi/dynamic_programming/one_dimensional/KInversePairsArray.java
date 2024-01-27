package com.prudhvi.dynamic_programming.one_dimensional;

public class KInversePairsArray {

	public static void main(String[] args) {
		int n = 3, k = 1;
		System.out.println(kInversePairsMemo(n, k));
		System.out.println(kInversePairsTab(n, k));
		System.out.println(kInversePairsTabSO(n, k));
	}
	//-----------> TC - (n * k * n) <-------------------
	/**
	 * Calculates the number of arrays that consist of numbers from 1 to n with k inverse pairs.
	 *
	 * @param n The upper bound of numbers in the array.
	 * @param k The number of inverse pairs.
	 * @return The number of arrays with the given constraints.
	 * @TimeComplexity The time complexity is O(n * k * n), where n is the upper bound and k is the number of inverse pairs.
	 * @SpaceComplexity The space complexity is O(n * k), as the function uses memorization to store computed results.
	 */
	public static int kInversePairsMemo(int n, int k) {
	    // Memorization table to store computed results
	    Integer[][] dp = new Integer[n + 1][k + 1];

	    // Call the helper function to calculate the number of arrays
	    return helper1(n, k, dp);
	}

	/**
	 * Helper function to recursively calculate the number of arrays with k inverse pairs.
	 *
	 * @param n  The current upper bound of numbers in the array.
	 * @param k  The current number of inverse pairs.
	 * @param dp Memorization table to store computed results.
	 * @return The number of arrays with the given constraints.
	 */
	public static int helper1(int n, int k, Integer[][] dp) {
	    // Base case: If the required number of inverse pairs is 0, there is only one array (sorted)
	    if (k == 0)
	        return 1;

	    // Base case: If the upper bound is 0, there are no valid arrays
	    if (n == 0)
	        return 0;

	    // Check if the result for the current state is already computed, return it
	    if (dp[n][k] != null)
	        return dp[n][k];

	    int count = 0;

	    // Iterate through possible values to form inverse pairs
	    for (int i = 0; i <= Math.min(k, n - 1); i++) {
	        count = (count + helper1(n - 1, k - i, dp)) % (int) (1e9 + 7);
	    }

	    // Update the memorization table and return the result
	    return dp[n][k] = count;
	}
	//----------------------> Tabulation <-----------------------------
	/**
	 * Calculates the number of arrays that consist of numbers from 1 to n with k inverse pairs.
	 *
	 * @param n The upper bound of numbers in the array.
	 * @param k The number of inverse pairs.
	 * @return The number of arrays with the given constraints.
	 * @TimeComplexity : O(n * k^2), where n is the upper bound and k is the number of inverse pairs.
	 * @SpaceComplexity : O(n * k), as the function uses a 2D table to store computed results.
	 */
	public static int kInversePairsTab(int n, int k) {
	    int mod = (int) 1e9 + 7;
	    // 2D table to store computed results
	    int[][] dp = new int[n + 1][k + 1];

	    // Initialize the base case for k = 0 (no inverse pairs)
	    for (int i = 0; i <= n; i++)
	        dp[i][0] = 1;

	    // Populate the table using bottom-up dynamic programming
	    for (int i = 1; i <= n; i++) {
	        for (int j = 1; j <= k; j++) {
	            // Iterate through possible values to form inverse pairs
	            for (int inverse = 0; inverse <= Math.min(i - 1, j); inverse++) {
	                // Update the current state using the previous results
	                dp[i][j] = (dp[i][j] % mod + dp[i - 1][j - inverse] % mod) % mod;
	            }
	        }
	    }

	    // Return the result for the given constraints
	    return dp[n][k];
	}
	
	//----------------> Tabulation space Optimization <---------------------------------------
	/**
	 * Calculates the number of arrays that consist of numbers from 1 to n with k inverse pairs.
	 *
	 * @param n The upper bound of numbers in the array.
	 * @param k The number of inverse pairs.
	 * @return The number of arrays with the given constraints.
	 * @TimeComplexity : O(n * k), where n is the upper bound and k is the number of inverse pairs.
	 * @SpaceComplexity : O(n * k), as the function uses a 2D table to store computed results.
	 */
	public static int kInversePairsTabSO(int n, int k) {
	    int mod = (int) 1e9 + 7;
	    // 2D table to store computed results
	    int[][] dp = new int[n + 1][k + 1];

	    // Initialize the base case for k = 0 (no inverse pairs)
	    for (int i = 0; i <= n; i++)
	        dp[i][0] = 1;

	    // Populate the table using bottom-up dynamic programming
	    for (int i = 1; i <= n; i++) {
	        for (int j = 1; j <= k; j++) {
	            // Calculate the current value based on the previous results
	            int val = (dp[i][j - 1] + dp[i - 1][j]) % mod;
	            // Adjust the value if j is greater than or equal to i
	            if (j >= i)
	                val = (val - dp[i - 1][j - i] + mod) % mod;
	            // Update the current state using the calculated value
	            dp[i][j] = (dp[i][j] + val) % mod;
	        }
	    }

	    // Return the result for the given constraints
	    return dp[n][k];
	}



}
