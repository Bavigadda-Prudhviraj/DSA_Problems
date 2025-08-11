package com.prudhvi.dynamic_programming.unbounded_knapsack;

/**
 * Problem: Buying Candies (Variant of Unbounded Knapsack)
 *
 * Given:
 * - sweetnessPerUnit[i]: sweetness of 1 unit of the i-th candy
 * - quantityAvailable[i]: how many units are available for the i-th candy
 * - costPerUnit[i]: cost of 1 unit of the i-th candy
 * - maxMoney: total money available to spend
 *
 * Goal: Maximize total sweetness under the budget constraint (maxMoney),
 *       and you can buy **unlimited units** (effectively) as we pre-multiply sweetness.
 *
 * Approach:
 * - Convert each candy into a totalSweetness = sweetnessPerUnit[i] * quantityAvailable[i]
 * - Use memoized recursion (top-down DP) to simulate unbounded knapsack,
 *   where each candy can be bought again and again until budget is exhausted.
 *
 * Time Complexity: O(n * maxMoney)
 * Space Complexity: O(maxMoney) for memoization
 */
public class BuyingCandies {

    public static void main(String[] args) {
        // Sweetness per unit for each candy
        int[] sweetnessPerUnit = { 2, 4 };

        // Available quantity of each candy
        int[] quantityAvailable = { 3, 1 };

        // Cost per unit for each candy
        int[] costPerUnit = { 4, 5 };

        // Total money we can spend
        int maxMoney = 10;

        // Output the maximum sweetness achievable with the given money
        System.out.println(BuyingCandies.solve(sweetnessPerUnit, quantityAvailable, costPerUnit, maxMoney));
    }

    /**
     * Calculates the maximum sweetness possible with given budget.
     *
     * @param sweetnessPerUnit sweetness per unit of candy
     * @param quantityAvailable available quantity per candy
     * @param costPerUnit cost per unit of candy
     * @param maxMoney total money available
     * @return maximum total sweetness
     */
    public static int solve(int[] sweetnessPerUnit, int[] quantityAvailable, int[] costPerUnit, int maxMoney) {
        int n = sweetnessPerUnit.length;
        int[] totalSweetness = new int[n];

        // Step 1: Precompute total sweetness per candy type
        // Each candy type is treated as if it gives a fixed totalSweetness when bought once
        for (int i = 0; i < n; i++) {
            totalSweetness[i] = sweetnessPerUnit[i] * quantityAvailable[i];
        }

        // Step 2: DP array for memoization
        // dp[i] represents max sweetness achievable with 'i' money
        Integer[] dp = new Integer[maxMoney + 1];

        // Step 3: Recursive function to calculate max sweetness using memoization
        return getMaxSweetness(maxMoney, costPerUnit, totalSweetness, dp);
    }

    /**
     * Recursive helper function with memoization (Top-down DP).
     *
     * @param remainingMoney money left to spend
     * @param costPerUnit cost array
     * @param totalSweetness precomputed sweetness for each candy
     * @param dp memoization array
     * @return maximum sweetness achievable with remainingMoney
     */
    private static int getMaxSweetness(int remainingMoney, int[] costPerUnit, int[] totalSweetness, Integer[] dp) {
        // Base case: no money left
        if (remainingMoney <= 0) return 0;

        // Memoized result
        if (dp[remainingMoney] != null) return dp[remainingMoney];

        int maxSweetnessAtThisBudget = 0;

        // Try buying each candy and take the best result
        for (int i = 0; i < costPerUnit.length; i++) {
            if (remainingMoney >= costPerUnit[i]) {
                // Include this candy and recursively compute for remaining money
                int currentSweetness = totalSweetness[i] +
                        getMaxSweetness(remainingMoney - costPerUnit[i], costPerUnit, totalSweetness, dp);

                // Update the maximum sweetness for current budget
                maxSweetnessAtThisBudget = Math.max(maxSweetnessAtThisBudget, currentSweetness);
            }
        }

        // Store result in dp array
        dp[remainingMoney] = maxSweetnessAtThisBudget;
        return dp[remainingMoney];
    }
}
