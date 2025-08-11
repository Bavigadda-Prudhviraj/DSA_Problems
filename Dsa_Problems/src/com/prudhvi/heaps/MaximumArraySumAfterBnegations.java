package com.prudhvi.heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumArraySumAfterBnegations {
	/*
	Problem Description
			Given an array of integers A and an integer B. You must modify the array exactly B number of times. In a single modification, we can replace any one array element A[i] by -A[i].
			You need to perform these modifications in such a way that after exactly B modifications, sum of the array must be maximum.
	Problem Constraints
			1 <= length of the array <= 5*105
			1 <= B <= 5 * 106
			-100 <= A[i] <= 100
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<>(List.of(-43,-1,19,87,-45));//57, 3, -14, -87, 42, 38, 31, -7, -28, -61-> 362
		int nagations=5;
		int maxSum=maxSumAfterBnegation(arr,nagations);
		int sum=anotherMethod(arr, nagations);
		System.out.println(maxSum+" "+sum+" "+optimized(arr,nagations));
		
		

	}
    public static int optimized(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(A);
        for(int i = 1; i <= B; i++){
            minHeap.add(-minHeap.poll());
        }
        int max = 0;
        for(int num : minHeap){
            max += num;
        }
        return max;
    }
	/*
	Method find the maximum possible sum of the elements in the array after performing a specific number of negations (changing the sign of certain elements). 
	The approach used here is to utilize a min-heap to maintain the smallest elements and manage the negations effectively.
	
	Time Complexity:
			Adding all elements from the array to the min heap takes O(N * log N) time, where N is the number of elements in the array.
			The loop that processes negations runs at most N times.
			Calculating the total sum takes O(N) time, as it involves summing up all elements in the min heap.
		Therefore, the overall time complexity of the function is O(N * log N).
	Space Complexity:
			The space complexity is determined by the storage used for the priority queue (minHeap), which can hold a maximum of N elements.
			Additionally, a constant amount of extra space is used for variables and temporary storage.
		Therefore, the overall space complexity is O(N).

	 */
	private static int maxSumAfterBnegation(ArrayList<Integer> arr, int nagations) {
		//Create a min-heap (PriorityQueue) named minHeap and initialize it with the elements from the input ArrayList arr. This heap will store the elements in ascending order.
		PriorityQueue<Integer> minHeap=new PriorityQueue<>(arr);
		//Initialize an integer variable count with the value of nagations. This variable will keep track of the remaining allowed negations.
		int count=nagations;
		//Enter a loop that executes as long as the smallest element in the heap (minHeap.peek()) is negative and there are remaining negations (count > 0).
		while(minHeap.peek()<0 && count>0){
			//Remove the smallest negative element from the heap using minHeap.poll().
			//Convert the element to its absolute value using Math.abs() and add it back to the heap.
			minHeap.add(Math.abs(minHeap.poll()));
			//Decrement the count variable to keep track of used negations.
			count--;
		}
		int totalSum=count%2==0?(minHeap.poll()):minHeap.peek()>0?-(minHeap.poll()):+(minHeap.poll());
		/*
		Calculate the initial value of totalSum based on the remaining count and the elements in the heap:
				If count is even, Add the smallest element from the heap (minHeap.poll()) to totalSum.
				If count is odd and the smallest element in the heap is positive, negate the smallest element from the heap (-(minHeap.poll())).
				If count is odd and the smallest element in the heap is non-positive, take the smallest element as-is (+(minHeap.poll())).
		 */
		//Enter a loop that continues until the heap is empty.
		while(!minHeap.isEmpty()){
			//Remove the smallest element from the heap using minHeap.poll() and add it to the totalSum.
			totalSum+=(minHeap.poll());
		}
		//Return the final calculated totalSum as the result of the method.
		return totalSum;
	}
	 public static int anotherMethod(ArrayList<Integer> A, int B) {
	        int ans=0;
	        PriorityQueue<Integer> pq=new PriorityQueue<>();
	        for(int i=0;i<A.size();i++) 
	        	pq.add(A.get(i));
	        for(int i=1;i<=B;i++) 
	        	pq.add(-pq.poll());
	        while(!pq.isEmpty()) 
	        	ans+=pq.poll();
	        return ans;
	    }

}

