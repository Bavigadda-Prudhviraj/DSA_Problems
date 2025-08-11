package com.prudhvi.heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AthLargestElement {

    /*
    Problem Description:
        Given an integer array B of size N.
        You need to find the A-th largest element in the subarray [1 to i],
        where i varies from 1 to N.

        In other words, find the A-th largest element in:
        [1 : 1], [1 : 2], [1 : 3], ...., [1 : N]

        NOTE: 
        - If the subarray [1 : i] has less than A elements, output -1 for that i.
    
    Example:
        B = [1, 2, 3, 4, 5, 6], A = 4
        Output = [-1, -1, -1, 1, 2, 3]
    */

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)); // input array
        int k = 4; // k-th largest element to find
        
        ArrayList<Integer> answerArrayList = KthLargestEleInArr(arr, k);
        System.out.println(answerArrayList); // Expected: [-1, -1, -1, 1, 2, 3]
        ArrayList<Integer> answerOptmisedArrayList = KthLargestEleInArr(arr, k);
        System.out.println(answerOptmisedArrayList);
    }

    private static ArrayList<Integer> KthLargestEleInArr(ArrayList<Integer> arr, int k) {
        ArrayList<Integer> answer = new ArrayList<>(arr.size());

        // Min-Heap to store the largest k elements seen so far
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < arr.size(); i++) {

            // Add the current element into the heap
            minHeap.add(arr.get(i));

            // If heap size exceeds k, remove the smallest element
            // This way, heap will always have at most k largest elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }

            // If we haven't seen at least k elements yet, answer is -1
            if (minHeap.size() < k) {
                answer.add(-1);
            } 
            else {
                // The smallest element in the heap is the k-th largest in the subarray
                answer.add(minHeap.peek());
            }
        }

        return answer;
    }
    /**
     * Finds the k-th largest element at each point in a stream of numbers.
     * For each prefix of the input list, returns:
     *  - The k-th largest element so far, if we have at least k elements.
     *  - -1 if fewer than k elements have been seen.
     *
     * Time Complexity: O(n log k) where n = arr.size()
     * Space Complexity: O(k) for the min-heap
     *
     * @param arr List of integers (input stream)
     * @param k   The "k" in k-th largest
     * @return    List of integers representing the k-th largest at each step
     */
    private static List<Integer> kthLargestInStream(List<Integer> arr, int k) {
        List<Integer> answer = new ArrayList<>();          // Stores the result for each step
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k); // Min-heap to store top k elements

        for (int num : arr) {
            // Add the current number to the heap
            minHeap.offer(num);

            // If heap size exceeds k, remove the smallest element
            // This ensures heap always stores the k largest seen so far
            if (minHeap.size() > k) {
                minHeap.poll();
            }

            // If fewer than k elements seen, output -1
            // Else, the root of the min-heap is the k-th largest element
            answer.add(minHeap.size() < k ? -1 : minHeap.peek());
        }

        return answer;
    }


}
