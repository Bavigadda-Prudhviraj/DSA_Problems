package com.prudhvi.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Problem:
 * Given N bags, each bag contains Bi chocolates. In one unit of time:
 * - The kid chooses a bag and eats all its chocolates.
 * - The magician refills the bag with floor(Bi / 2) chocolates.
 *
 * Find the maximum number of chocolates the kid can eat in A units of time.
 * Return the answer modulo 1e9+7.
 *
 * Approach:
 * - Use a max heap to always select the bag with the most chocolates.
 * - After eating, push floor(Bi / 2) back into the heap.
 *
 * Time Complexity: O(A log N), where N = number of bags.
 * Space Complexity: O(N) for the heap.
 */
public class MagicianAndChocolates {

    public static void main(String[] args) {
        // Example run
        MagicianAndChocolates solver = new MagicianAndChocolates();
        ArrayList<Integer> chocolates = new ArrayList<>();
        Collections.addAll(chocolates, 6, 5);
        System.out.println(solver.nchoc(3, chocolates)); // Expected: 14
    }

    /**
     * Calculates the maximum chocolates eaten in A units of time.
     *
     * @param A The number of time units available.
     * @param B The list of chocolates in each bag.
     * @return  The maximum chocolates eaten modulo 1e9+7.
     */
    public int nchoc(int A, ArrayList<Integer> B) {
        long maxChocolates = 0; // Stores total chocolates eaten
        final int MOD = 1_000_000_007; // Modulo value

        // Max heap to get the bag with most chocolates quickly
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(B);

        // Process A time units
        for (int i = 0; i < A; i++) {
            if (!maxHeap.isEmpty()) {
                // Take the bag with the most chocolates
                int currentCandies = maxHeap.poll();

                // Eat chocolates and update the total (mod applied at each step)
                maxChocolates = (maxChocolates + currentCandies) % MOD;

                // Magician refills the bag with floor(current / 2) chocolates
                maxHeap.add(currentCandies / 2);
            }
        }

        return (int) maxChocolates;
    }
}
