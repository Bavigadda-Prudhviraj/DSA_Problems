package com.prudhvi.heaps;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Problem:
 * --------
 * Given an integer array A of size N, we need to find the product of the 
 * three largest integers from the subarray A[0..i] for each index i.
 * 
 * Rules:
 * - If there are fewer than 3 elements in the range (i < 2), 
 *   we return -1 for that position in the result array.
 * - Otherwise, we return the product of the top 3 largest numbers seen so far.
 * 
 * Example:
 * --------
 * A = [1, 2, 3, 4, 5]
 * Output: [-1, -1, 6, 24, 60]
 * 
 * Explanation:
 * i = 0 → [1] → less than 3 elements → -1
 * i = 1 → [1, 2] → less than 3 elements → -1
 * i = 2 → [1, 2, 3] → product = 1*2*3 = 6
 * i = 3 → [1, 2, 3, 4] → top 3 = [2, 3, 4] → product = 24
 * i = 4 → [1, 2, 3, 4, 5] → top 3 = [3, 4, 5] → product = 60
 */
public class ProductOf3 {

    public static void main(String[] args) {
        // Example run
        ArrayList<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);

        ProductOf3 obj = new ProductOf3();
        ArrayList<Integer> output = obj.solve(input);
        System.out.println(output); // Expected: [-1, -1, 6, 24, 60]
    }

    /**
     * Computes the product of the 3 largest numbers seen so far for each index.
     *
     * @param A the input list of integers
     * @return a list of integers where each element corresponds to the product 
     *         of the 3 largest integers up to that index, or -1 if less than 3 elements.
     */
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        ArrayList<Integer> result = new ArrayList<>();

        // Min-heap (PriorityQueue in ascending order) to keep track of the top 3 numbers.
        // The smallest of the top 3 will be at the root, making removal O(log k).
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Iterate over each number in the input array
        for (int i = 0; i < A.size(); i++) {

            // Step 1: Add the current number into the heap
            minHeap.offer(A.get(i));

            // Step 2: Ensure heap size is at most 3
            // If more than 3 numbers are in the heap, remove the smallest one
            if (minHeap.size() > 3) {
                minHeap.poll();
            }

            // Step 3: If less than 3 elements processed so far, result is -1
            if (i < 2) {
                result.add(-1);
            } 
            // Step 4: Else, compute the product of the 3 largest numbers
            else {
                long product = 1; // Use long to handle potential overflow
                for (int num : minHeap) {
                    product *= num; // Multiply each of the top 3 numbers
                }
                result.add((int) product); // Cast back to int if within safe range
            }
        }

        return result;
    }
}
