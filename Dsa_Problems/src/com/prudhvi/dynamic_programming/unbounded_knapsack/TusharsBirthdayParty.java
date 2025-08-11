package com.prudhvi.dynamic_programming.unbounded_knapsack;

/**
 * Problem: Tushar's Birthday Party
 * 
 * Description:
 * Tushar is hosting a party and needs to satisfy his friends' eating capacities using various dishes.
 * Each dish has a filling capacity and a cost. Each friend must reach their exact eating capacity,
 * and dishes cannot be shared. Dishes can be used unlimited times. The goal is to find the minimum
 * total cost to satisfy all friends.
 * 
 * Why Dynamic Programming (DP)?
 * - The problem involves making optimal choices (selecting dishes) to achieve exact capacities for each friend.
 * - For each capacity, we need to find the minimum cost by trying all possible dishes, which leads to
 *   overlapping subproblems (e.g., computing the minimum cost for a capacity multiple times).
 * - DP avoids recomputing these subproblems by storing results in a DP array, reducing time complexity.
 * - The unbounded nature (dishes can be used multiple times) makes it an unbounded knapsack problem.
 * 
 * Constraints:
 * - |A| <= 1000 (number of friends)
 * - |B| <= 1000 (number of dish types)
 * - |C| <= 1000 (costs of dishes)
 */
public class TusharsBirthdayParty {

    public static void main(String[] args) {
        // Test case: Example input to verify the solution
        int[] A = {2, 4, 6}; // Eating capacities of friends
        int[] B = {2, 1, 3}; // Filling capacities of dishes
        int[] C = {2, 5, 3}; // Costs of dishes
        System.out.println(solve(A, B, C)); // Expected output: 12
    }

    /**
     * Solves the minimum cost to satisfy all friends using dynamic programming.
     * 
     * @param A Array of friends' eating capacities
     * @param B Array of dishes' filling capacities
     * @param C Array of dishes' costs
     * @return Minimum total cost to satisfy all friends
     * 
     * Why DP Array?
     * - We use a DP array to store the minimum cost to achieve each capacity from 0 to maxCapacity.
     * - This allows us to reuse solutions for subproblems (e.g., cost for capacity 2) when computing
     *   costs for larger capacities or multiple friends.
     * - Without DP, recursive calls would recompute the same capacities multiple times, leading to
     *   exponential time complexity.
     */
    public static int solve(final int[] A, final int[] B, final int[] C) {
        // Step 1: Find the maximum eating capacity among all friends
        // Why? We need to size the DP array to handle the largest capacity required.
        // This ensures we can compute costs for all possible capacities up to the maximum.
        int maxCapacity = Integer.MIN_VALUE;
        for (int eatingCapacity : A) {
            maxCapacity = Math.max(maxCapacity, eatingCapacity);
        }

        // Step 2: Initialize DP array for memoization
        // - dp[i] stores the minimum cost to achieve exactly capacity i.
        // - Why Integer[] with null? Using Integer allows us to distinguish uncomputed states (null)
        //   from computed ones, avoiding the need to initialize with a specific value.
        Integer[] dp = new Integer[maxCapacity + 1];

        // Step 3: Compute minimum cost for each possible capacity using unbounded knapsack
        // - We call the helper function to fill the DP array for all capacities up to maxCapacity.
        // - This ensures we have the minimum cost for each friend's capacity.
        unboundedKnapsack(maxCapacity, B, C, dp);

        // Step 4: Sum the minimum costs for each friend's capacity
        // - For each friend, we look up the minimum cost to satisfy their capacity in dp[].
        // - The problem guarantees a solution exists (due to a dish with filling capacity 1).
        int minTotalCost = 0;
        for (int i = 0; i < A.length; i++) {
            minTotalCost += dp[A[i]];
        }

        return minTotalCost;
    }

    /**
     * Helper method to compute minimum cost for a given capacity using unbounded knapsack.
     * 
     * @param capacity CurrentJon Current capacity to achieve
     * @param filling Array of dishes' filling capacities
     * @param cost Array of dishes' costs
     * @param dp Memoization array
     * @return Minimum cost to achieve禁止
     * 
     * Why Unbounded Knapsack?
     * - The problem allows unlimited use of each dish, similar to the unbounded knapsack problem.
     * - We need to select a combination of dishes (with filling capacities and costs) to achieve
     *   the exact capacity for each friend at minimum cost.
     * - Unbounded knapsack is suitable because we can reuse dishes, and we need to optimize the cost.
     */
    private static int unboundedKnapsack(int capacity, int[] filling, int[] cost, Integer[] dp) {
        // Base case: If capacity is 0, no cost is needed (no dishes required)
        // Why? This is the stopping condition for recursion when the target capacity is fully met.
        if (capacity == 0) {
            return 0;
        }

        // Memoization check: Return cached result if already computed
        // Why? To avoid redundant recursive calls for the same capacity, improving efficiency.
        if (dp[capacity] != null) {
            return dp[capacity];
        }

        // Initialize minimum cost to Integer.MAX_VALUE
        // Why Integer.MAX_VALUE? It acts as a sentinel value to indicate an invalid solution.
        // - If no valid combination of dishes can achieve the capacity, minCost remains Integer.MAX_VALUE.
        // - The problem guarantees a solution exists, so this is mainly for robustness.
        int minCost = Integer.MAX_VALUE;

        // Iterate through each dish to find the minimum cost
        for (int i = 0; i < filling.length; i++) {
            // Condition: Check if the current dish can be used (capacity >= filling[i])
            // Why? We can only use a dish if its filling capacity doesn't exceed the remaining capacity.
            // - This prevents negative capacities, which are invalid.
            if (capacity - filling[i] >= 0) {
                // Recursively compute the cost for the remaining capacity
                // Why? We try to build the current capacity by including the i-th dish and solving
                // for the reduced capacity (capacity - filling[i]).
                int subCost = unboundedKnapsack(capacity - filling[i], filling, cost, dp);
                // Check if subCost is valid (not Integer.MAX_VALUE)
                // Why? We only update minCost if a valid solution exists for the subproblem.
                // - This handles cases where certain capacities might not be achievable (though
                //   the problem guarantees a solution due to the existence of a dish with filling 1).
                if (subCost != Integer.MAX_VALUE) {
                    minCost = Math.min(minCost, cost[i] + subCost);
                }
            }
        }

        // Cache the result in dp[capacity]
        // Why? To store the minimum cost for this capacity for future lookups, preventing recomputation.
        dp[capacity] = minCost;
        return dp[capacity];
    }

    /*
     * Time Complexity (TC):
     * - Let N be the number of friends (length of A).
     * - Let M be the number of dish types (length of B and C).
     * - Let K be the maximum eating capacity (max(A)).
     * - The unboundedKnapsack function takes O(K * M) time because:
     *   - There are K states in the DP array (0 to maxCapacity).
     *   - For each state, we iterate over M dishes to find the minimum cost.
     * - Memoization ensures each state is computed only once.
     * - Finding maxCapacity takes O(N).
     * - Summing costs for N friends takes O(N).
     * - Total TC: O(K * M + N).
     * 
     * Space Complexity (SC):
     * - DP array requires O(K) space for memoization.
     * - Recursion stack depth is O(K) in the worst case.
     * - Total SC: O(K).
     * 
     * Edge Cases Handled:
     * 1. Empty A array: Returns 0 (no friends to satisfy).
     * 2. Capacity 0: Handled by base case returning 0.
     * 3. No valid solution for a capacity: dp[capacity] remains Integer.MAX_VALUE
     *    (though problem guarantees a solution due to a dish with filling capacity 1).
     * 4. Large capacities: Handled by sizing DP array to maxCapacity.
     * 5. Negative capacities or costs: Not applicable due to problem constraints.
     * 6. Single dish type: Loop over filling array handles this naturally.
     * 7. All friends with same capacity: Summing dp[A[i]] handles this efficiently.
     */
}