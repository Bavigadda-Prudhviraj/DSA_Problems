package com.prudhvi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KPlacesApart {
	/*
	Problem Description
		N people having different priorities are standing in a queue.
		The queue follows the property that each person is standing at most B places away from its position in the sorted queue.
		Your task is to sort the queue in the increasing order of priorities.
		NOTE:
			No two persons can have the same priority.
			Use the property of the queue to sort the queue with complexity O(NlogB).
		Problem Constraints
			1 <= N <= 100000
			0 <= B <= N
	 */

	public static void main(String[] args) {
		int awayFromIndex=1;
		ArrayList<Integer> arr=new ArrayList<>(List.of(2, 1, 17, 10, 21, 95));
		ArrayList<Integer> sortedArray=kPlaceAPart(arr,awayFromIndex);
		System.out.println(sortedArray);
	}
	/*
	
	Time Complexity:
			Inserting an element into a min-heap takes O(log K), where K is the current size of the heap.
			The loop iterates through the array of size N, and for each iteration, there might be a heap insertion (O(log K)) and a heap removal (O(log K)).
			The final loop that empties the heap also takes O(K * log K) time in the worst case.
		Therefore, the overall time complexity is O(N * log K), where K represents awayFromIndex.

	Space Complexity:
			The space complexity is determined by the minHeap and the sortedArray.
			In the worst case, the heap can have at most awayFromIndex elements, so the space complexity due to the heap is O(K).
			The space complexity due to the sortedArray is O(N), where N is the size of the input array.
		Therefore, the overall space complexity is O(N + K), which simplifies to O(N) if K is relatively small compared to N.
	 */
	private static ArrayList<Integer> kPlaceAPart(ArrayList<Integer> arr, int awayFromIndex) {
		ArrayList<Integer> sortedArray=new ArrayList<>();// Initialize the sorted array
		PriorityQueue<Integer> minHeap=new PriorityQueue<>();// Create a min-heap
		// Iterate through the array
		for(int i=0;i<arr.size();i++) {
			minHeap.add(arr.get(i));// Add the current element to the min-heap
			// If the min-heap size exceeds the specified awayFromIndex, add the smallest element to the sorted array
			if(minHeap.size()>awayFromIndex) {
				sortedArray.add(minHeap.poll());// Remove the smallest element and add it to the sorted array
			}
		}
		// After the loop, add the remaining elements from the min-heap to the sorted array
		while(!minHeap.isEmpty()){
			sortedArray.add(minHeap.poll());
		}
		return sortedArray;
	}

	

}
