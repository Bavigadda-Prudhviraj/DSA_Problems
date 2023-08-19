package com.prudhvi.heaps;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
public class ConnectRopes {
	/*
	Problem Description
		You are given an array A of integers that represent the lengths of ropes.
		You need to connect these ropes into one rope. The cost of joining two ropes equals the sum of their lengths.
		Find and return the minimum cost to connect these ropes into one rope.
	Problem Constraints
		1 <= length of the array <= 100000
		1 <= A[i] <= 1000
	 */
	public static void main(String[] args) {
		ArrayList<Integer> ropes=new ArrayList<>(List.of(1,2,3,4,5));
		int minimumCost=connectRopesWithMinCost(ropes);
		System.out.println(minimumCost);
	}
	/*
	
	
	Time Complexity:
			Note:-backEnd:The constructor will need to build the heap from the provided array. Building a heap from an array takes O(n) time complexity, where 'n' is the number of elements in the array.
			general:Creating the initial min heap from the ropes list takes O(n * log n) time due to the construction of the heap.// when we insert one by one
			The loop runs n-1 times, and each iteration involves inserting a new element and removing two elements from the heap, which takes O(log n) time.
		Therefore, the overall time complexity of the function is O(n * log n).
	Space Complexity:
			The space complexity is determined by the storage used for the priority queue (heap) and a constant amount of extra space used for variables and temporary storage.
			The space used by the heap is proportional to the number of ropes, which is O(n).
		Therefore, the overall space complexity is O(n).
	 */
	private static int connectRopesWithMinCost(ArrayList<Integer> ropes) {
		/*
		Note:The optimization lies in the way you initialize the priority queue using the constructor that accepts a collection, such as a list. 
		     The constructor will directly build the min heap from the provided list of rope lengths, which can be more efficient than adding each rope length one by one.
		     Time Complexity will be O(n) 
		 */
		PriorityQueue<Integer> heap=new PriorityQueue<>(ropes);// Create a min-heap from the input ropes
		int totalSum=0;// Initialize the total sum of connecting ropes
		// Continue connecting ropes until there's only one left in the heap
		while(heap.size()>1){
			int firstMin=heap.poll() ;// Extract the smallest rope from the heap
			int secondMin=heap.poll();// Extract the second smallest rope from the heap
			int currentSum=firstMin+secondMin;// Connect the two ropes
			totalSum+=(currentSum);// Update the total cost
			heap.add(currentSum);// Insert the connected rope back into the heap
		}
		return totalSum;// Return the minimum total cost of connecting all ropes
	}

}
