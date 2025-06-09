package com.prudhvi.strings;

/**
 * Solution to LeetCode #440: Kth Smallest in Lexicographical Order (Hard).
 * Given integers n and k, find the kth smallest number in the lexicographical order
 * of all numbers from 1 to n. This solution uses a prefix-based tree traversal approach
 * to efficiently navigate the lexicographical number tree without generating all numbers.
 *
 * Time Complexity: O(log n * log n)
 * - The while loop in findKthNumber runs at most O(log n) times, as each iteration either
 *   moves to a sibling (currentPrefix += 1) or a child (currentPrefix *= 10), and the
 *   depth of the tree is bounded by log n (since n <= 10^9).
 * - Within each iteration, calculateSteps runs a loop up to O(log n) times (based on the
 *   number of digits in n), performing constant-time operations.
 * - Total: O(log n * log n).
 *
 * Space Complexity: O(1)
 * - The algorithm uses a constant amount of extra space for variables (currentPrefix, k,
 *   steps, etc.), regardless of input size.
 * - No additional data structures (e.g., arrays or lists) are used.
 */
public class K_thSmallestInLexicographicalOrder_440 {

    /**
     * Main method to test the findKthNumber function with sample inputs.
     * Example: For n = 30 and k = 25, it finds the kth number in lexicographical order.
     */
    public static void main(String[] args) {
        // Test case: Find the 25th number in lexicographical order up to 30
        System.out.println(findKthNumber(30, 25)); // Expected output: 4
    }

    /**
     * Finds the kth smallest number in lexicographical order from 1 to n.
     * The algorithm treats numbers as nodes in a lexicographical tree, where each node
     * represents a prefix (e.g., 1, 10, 100, etc.). It navigates the tree by deciding
     * whether to move to a sibling node (e.g., 1 to 2) or a child node (e.g., 1 to 10)
     * based on the number of valid numbers under the current prefix.
     *
     * @param n The upper bound of the range [1, n].
     * @param k The position of the number to find (1-based).
     * @return The kth smallest number in lexicographical order.
     */
    public static int findKthNumber(int n, int k) {
        // Initialize currentPrefix to 1, the root of the lexicographical tree (first number)
        int currentPrefix = 1;
        // Decrement k by 1 because we've already considered the first number (1)
        // k now represents the number of steps remaining to reach the kth number
        k = k - 1;

        // Continue until k steps are exhausted (k = 0 means we've found the kth number)
        while (k > 0) {
            // Calculate the number of valid numbers (steps) under the current prefix
            // and the next sibling prefix (e.g., between 1 and 2, or 10 and 20)
            int steps = calculateSteps(currentPrefix, currentPrefix + 1, n);

            // If the number of steps under the current prefix is less than or equal to k,
            // we can skip all numbers under this prefix (they come before the kth number)
            if (steps <= k) {
                // Move to the next sibling node (e.g., from 1 to 2, or 10 to 11)
                // This is because the kth number lies beyond the current prefix's subtree
                currentPrefix += 1;
                // Reduce k by the number of steps skipped, as we've accounted for those numbers
                k -= steps;
            } else {
                // If steps > k, the kth number is within the current prefix's subtree
                // Move to the first child node by appending a 0 (e.g., 1 to 10, or 10 to 100)
                currentPrefix *= 10;
                // Account for visiting the current node itself, so decrement k by 1
                k -= 1;
            }
        }

        // Return the kth number, which is the current prefix after k steps are exhausted
        return currentPrefix;
    }

    /**
     * Calculates the number of valid numbers between two prefixes (currentPrefix and nextPrefix)
     * within the limit n. This counts all numbers in the lexicographical tree under the
     * current prefix (e.g., for prefix 1, it counts 1, 10, 100, etc., up to n).
     * Uses long to prevent integer overflow for large prefixes.
     *
     * @param currentPrefix The starting prefix (e.g., 1 for 1, 10, 100, etc.).
     * @param nextPrefix The next sibling prefix (e.g., 2 for 2, 20, 200, etc.).
     * @param limit The upper bound n, ensuring numbers don't exceed n.
     * @return The number of valid numbers under currentPrefix up to n.
     */
    private static int calculateSteps(long currentPrefix, long nextPrefix, int limit) {
        // Initialize steps to count valid numbers under the current prefix
        long steps = 0;

        // Continue while the current prefix is within the limit n
        while (currentPrefix <= limit) {
            // Calculate numbers at the current level between currentPrefix and nextPrefix
            // This counts all numbers from currentPrefix up to (but not including) nextPrefix
            steps += nextPrefix - currentPrefix;

            // Cap nextPrefix at (limit + 1) to ensure we don't count numbers beyond n
            // (limit + 1) is used because we want to include n if it's in range
            // This adjustment applies to the next iteration, ensuring accurate counts at deeper levels
            nextPrefix = Math.min(nextPrefix, (long) limit + 1);

            // Move to the next level of the tree by appending a 0 to both prefixes
            // (e.g., from 1 to 10, 2 to 20, or 10 to 100, 11 to 110)
            // This explores deeper levels of the lexicographical tree
            currentPrefix *= 10;
            nextPrefix *= 10;
        }

        // Return the total number of valid numbers as an integer
        // (steps is safe to cast because it's bounded by n)
        return (int) steps;
    }

    /**
     * Example Explanation: n = 30, k = 25
     * This section explains how the algorithm finds the 25th number in lexicographical order
     * for n = 30, resulting in 4. The lexicographical order of numbers from 1 to 30 is:
     * 1, 10, 11, 12, ..., 19, 2, 20, 21, ..., 29, 3, 30, 4, 5, ..., 9.
     * The 25th number is 4.
     *
     * Lexicographical Tree Diagram (simplified for n = 30):
     * ```
     *         root
     *        / | \
     *       1  2  3 ... 9
     *      /|  /|       |
     *     10 11-19 20-29 30
     *    /|
     *   100...
     * ```
     * Each node represents a prefix. Children of 1 are 10, 11, ..., 19; children of 2 are 20, 21, ..., 29.
     * The algorithm traverses this tree to find the kth number efficiently.
     *
     * Step-by-Step Walkthrough:
     * 1. Initialize: currentPrefix = 1, k = 25 - 1 = 24 (account for 1).
     * 2. Loop 1:
     *    - calculateSteps(1, 2, 30):
     *      - Level 1: steps += 2 - 1 = 1 (number 1)
     *      - Level 2: nextPrefix = min(20, 31) = 20, steps += 20 - 10 = 10 (10 to 19)
     *      - Level 3: nextPrefix = min(200, 31) = 31, steps += 31 - 100 = 0 (100 > 30, stop)
     *      - Total steps = 1 + 10 = 11
     *    - steps (11) <= k (24): Skip prefix 1's subtree, currentPrefix = 2, k = 24 - 11 = 13
     * 3. Loop 2:
     *    - calculateSteps(2, 3, 30):
     *      - Level 1: steps += 3 - 2 = 1 (number 2)
     *      - Level 2: nextPrefix = min(30, 31) = 30, steps += 30 - 20 = 10 (20 to 29)
     *      - Level 3: nextPrefix = min(300, 31) = 31, steps += 31 - 200 = 0 (200 > 30, stop)
     *      - Total steps = 1 + 10 = 11
     *    - steps (11) <= k (13): Skip prefix 2's subtree, currentPrefix = 3, k = 13 - 11 = 2
     * 4. Loop 3:
     *    - calculateSteps(3, 4, 30):
     *      - Level 1: steps += 4 - 3 = 1 (number 3)
     *      - Level 2: nextPrefix = min(40, 31) = 31, steps += 31 - 30 = 1 (30)
     *      - Level 3: nextPrefix = min(400, 31) = 31, steps += 31 - 300 = 0 (300 > 30, stop)
     *      - Total steps = 1 + 1 = 2
     *    - steps (2) <= k (2): Skip prefix 3's subtree, currentPrefix = 4, k = 2 - 2 = 0
     * 5. k = 0: Return currentPrefix = 4
     *
     * Result: The 25th number is 4, as the sequence reaches 3, 30, then 4.
     * The tree diagram shows prefixes 1, 2, 3, etc., with their subtrees, and the algorithm
     * skips subtrees (e.g., 1's 11 numbers, 2's 11 numbers) to efficiently reach the kth number.
     */
}