package com.prudhvi.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Problem Description

You are managing grocery items with expiration deadlines and profits.
You want to buy items one at a time to maximize profit, 
ensuring each is bought before its expiration.

Input:
- A[i]: expiration deadline (time left before expiration)
- B[i]: profit for ith item

Output:
- Maximum profit achievable modulo 10^9 + 7.
*/

public class FlipkartsChallengeInEffectiveInventoryManagement {

    public static void main(String[] args) {
       
    	ArrayList<Integer> expiration = new ArrayList<>(Arrays.asList(1, 3, 2, 3, 3));
    	ArrayList<Integer> profits = new ArrayList<>(Arrays.asList(5, 6, 1, 3, 9));

        FlipkartsChallengeInEffectiveInventoryManagement solution = new FlipkartsChallengeInEffectiveInventoryManagement();
        int maxProfit = solution.solve(expiration, profits);
        System.out.println("Maximum Profit: " + maxProfit); // Expected: 20
    }

    public int solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        final int MOD = 1_000_000_007;

        // Step 1: Pair each item with its expiration and profit
        ArrayList<Pair> items = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            items.add(new Pair(A.get(i), B.get(i)));
        }

        // Step 2: Sort items by expiration time ascending
        // We want to consider items with earlier deadlines first
        items.sort(Comparator.comparingInt(p -> p.expire));

        // Step 3: Use a min-heap (priority queue) to store chosen profits
        // The smallest profit is at the top, which we can remove if needed
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Step 4: Iterate over sorted items
        for (Pair item : items) {
            // Add the current item's profit to the heap
            minHeap.offer(item.profit);

            // If the number of chosen items exceeds the current item's deadline,
            // it means we can't buy all chosen items in time,
            // so remove the smallest profit item to maximize total profit
            if (minHeap.size() > item.expire) {
                minHeap.poll();
            }
        }

        // Step 5: Sum all profits left in the min-heap, modulo MOD
        long totalProfit = 0;
        for (int profit : minHeap) {
            totalProfit = (totalProfit + profit) % MOD;
        }

        return (int) totalProfit;
    }

    // Helper class to hold expiration and profit for each item
    static class Pair {
        int expire;
        int profit;

        public Pair(int expire, int profit) {
            this.expire = expire;
            this.profit = profit;
        }
    }
}
