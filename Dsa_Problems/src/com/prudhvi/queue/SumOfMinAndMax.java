package com.prudhvi.queue;

import java.util.ArrayDeque;
import java.util.Deque;


public class SumOfMinAndMax {
	/*
	Problem Description
			Given an array A of both positive and negative integers.Your task is to compute the sum of minimum and maximum elements of all sub-array of size B.
			NOTE: Since the answer can be very large, you are required to return the sum modulo 109 + 7.
	Problem Constraints
			1 <= size of array A <= 105
			-109 <= A[i] <= 109
			1 <= B <= size of array
	Input Format
			The first argument denotes the integer array A.
			The second argument denotes the value B
	Output Format
			Return an integer that denotes the required value.
	Example Input
		Input 1:A = [2, 5, -1, 7, -3, -1, -2]
 				B = 4
		Output :18
	 */
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		int windowSize = 2;
		int answer=sumOfAllSubArrayMinMax(arr,windowSize);
		System.out.println(answer);

	}
	/*
	The sumOfAllSubArrayMinMax method calculates the sum of the minimum and maximum elements of all sub arrays of a given array, with a specified windowSize. 
	The method uses two Deques (maxQueue and minQueue) to efficiently keep track of the maximum and minimum elements within the sliding window as it moves through the array.
	
	Time Complexity:
			The time complexity of the sumOfAllSubArrayMinMax method is O(N), where N is the size of the input array. 
			The method iterates through the array once, and each element is added and removed from the Deques at most once.
	
	Space Complexity:
			The space complexity is O(windowSize) 
			The method uses two Deques of size windowSize to store the minimum and maximum elements of the current window.
	 */
	private static int sumOfAllSubArrayMinMax(int[] arr, int windowSize) {
		long mod = 1000000007;//The mod variable is used to take the modulo of the final answer to avoid integer overflow.
		//Two Deques are used to keep track of the maximum and minimum elements in the current window of size windowSize
        Deque<Long> maxQueue=new ArrayDeque<>();
		Deque<Long> minQueue=new ArrayDeque<>();
		long answer=0;//The answer variable will store the final sum of minimum and maximum elements of all sub arrays.
		// Initialize the Deques with a sentinel value to handle the edge cases where the window is empty.
		maxQueue.add(Long.MIN_VALUE);
		minQueue.add(Long.MAX_VALUE);
		//The for loop is used to process the first window of size windowSize.
		for(int i=0;i<windowSize;i++) {
			long ele=arr[i];
			//Maintain a non-increasing order of elements in the minQueue. 
			//Remove elements that are greater than the current element to keep the smallest element at the front.
			while(!minQueue.isEmpty() && minQueue.getLast()>ele ){
				minQueue.removeLast();
			}
			//Maintain a non-decreasing order of elements in the maxQueue. 
			//Remove elements that are smaller than the current element to keep the largest element at the front.
			while(!maxQueue.isEmpty() && maxQueue.getLast()<ele ){
				maxQueue.removeLast();
			}
			//Add the current element to the minQueue and maxQueue.
			minQueue.addLast(ele);
			maxQueue.addLast(ele);
		}
		//Update the answer by adding the sum of the first elements of minQueue and maxQueue to handle negative numbers and then take the modulo mod of the result.
		answer=(answer+((minQueue.getFirst()+maxQueue.getFirst()+(2*mod))%mod))%mod;
		//The second for loop starts from index windowSize and processes the remaining elements of the array.
		for(int i=windowSize;i<arr.length;i++) {
			long ele=arr[i];
			// If the element leaving the window is the smallest or largest element in the current window, remove it from the minQueue and maxQueue.
			if(arr[i-windowSize]==minQueue.getFirst()) {
				minQueue.remove();
			}
			if(arr[i-windowSize]==maxQueue.getFirst()) {
				maxQueue.remove();
			}
			//Maintain a non-increasing order of elements in the minQueue. 
			//Remove elements that are greater than the current element to keep the smallest element at the front.
			while(!minQueue.isEmpty() && minQueue.getLast()>ele ){
				minQueue.removeLast();
			}
			//Maintain a non-decreasing order of elements in the maxQueue. 
			//Remove elements that are smaller than the current element to keep the largest element at the front.
			while(!maxQueue.isEmpty() && maxQueue.getLast()<ele ){
				maxQueue.removeLast();
			}
			//Add the current element to the minQueue and maxQueue.
			minQueue.addLast(ele);
			maxQueue.addLast(ele);
			////Update the answer by adding the sum of the first elements of minQueue and maxQueue to handle negative numbers and then take the modulo mod of the result.
			answer=(answer+((minQueue.getFirst()+maxQueue.getFirst()+(2*mod))%mod))%mod; 
		}
		//Finally, cast the answer to integer and return it.
		return (int)(answer);
	}

}
