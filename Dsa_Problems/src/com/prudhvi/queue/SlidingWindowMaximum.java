package com.prudhvi.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {
	/*
	Problem Description
		Given an array of integers A. There is a sliding window of size B, moving from the very left of the array to the very right. 
		You can only see the B numbers in the window. 
		Each time the sliding window moves rightwards by one position. 
		You have to find the maximum for each window.
	
	Return an array C, where C[i] is the maximum value in the array from A[i] to A[i+B-1].

	Refer to the given example for clarity.
		NOTE: If B > length of the array, return 1 element with the max of the array.

	Problem Constraints
		1 <= |A|, B <= 106
	 */

	public static void main(String[] args) {
		int[] arr= {1, 2, 3, 4, 2, 7, 1, 3, 6};
		int windowSize=6;
		int[] answerArr=SlidingWindowMaximum(arr,windowSize);
		System.out.println(Arrays.toString(answerArr));
		
	}
	/*
	 the SlidingWindowMaximum method uses a dequeue to efficiently find the maximum elements in each sliding window of size windowSize in the input array.
	 It iterates through the array to build the initial window and then slides the window to find the maximum elements for the rest of the windows
	
	Time Complexity
			The time complexity of this function is O(n) in both the best-case and worst-case scenarios. 
			The function iterates through the entire input array once, and the operations within each iteration are constant time.

	Space Complexity
			 the queue may also store up to windowSize elements, but not exceeding the total number of elements in array. 
			 Therefore, the overall space complexity is O(k).
	 */
	private static int[] SlidingWindowMaximum(int[] arr, int windowSize) {
		//It initializes an integer array answer to store the maximum elements for each window position. 
		//The length of the answer array will be arr.length - windowSize + 1, as that's the number of windows that can be formed from the input array.
		int[] answer=new int[arr.length-windowSize+1];
		//It creates a deque (double-ended queue) called deque using ArrayDeque.
		//The deque will be used to store the elements within the current sliding window and maintain the maximum element at the front of the deque.
		Deque<Integer> deque=new ArrayDeque<>();
		//The method starts processing the first window of size windowSize. For each element from index 0 to windowSize - 1 in the input array.
		for(int i=0;i<windowSize;i++) {
			//It retrieves the current element ele from the input array.
			int ele=arr[i];
			//While the deque is not empty and the last element in the deque (back of the deque) is less than the current element ele, it removes elements from the back of the deque. 
			//This is because the elements in the deque represent candidates for being the maximum element of the current window, and any element that is less than the current element cannot be the maximum for the current or any future window.
			while(!deque.isEmpty() && deque.getLast()<ele){ //****deque.getLast()<=element while taking the indexes****
				deque.removeLast();
			}
			//After removing elements from the back of the deque, the current element ele is added to the back of the deque.
			deque.addLast(ele);
		}
		//After processing the first window, the method initializes an index variable index to 0. 
		int index=0;
		//It then sets the first element of the answer array as the front element of the deque, which represents the maximum element in the first window.
		answer[index]=deque.getFirst();
		index++;
		//The method then proceeds to process the rest of the windows. It starts from index windowSize and goes up to arr.length - 1. For each index i
		for(int i=windowSize;i<arr.length;i++) {
			//It retrieves the current element ele from the input array.
			int ele=arr[i];
			// If the element at index index - 1 in the input array (arr[index-1]) is equal to the front element of the deque (deque.getFirst()), 
			//it means that the front element of the deque is no longer part of the current window.
			//Therefore, it removes the front element from the deque.
			if(arr[index-1]==deque.getFirst()) {
				deque.removeFirst();
			}
			//While the deque is not empty and the last element in the deque is less than the current element ele, it removes elements from the back of the deque. 
			//This step is similar to what was done for the first window.
			while(!deque.isEmpty() && deque.getLast()<ele){//****Note: deque.getLast()<=element while talking the indexes****
				deque.removeLast();
			}
			//After removing elements from the back of the deque, the current element ele is added to the back of the deque.
			deque.addLast(ele);
			//The front element of the deque is the maximum element for the current window. It is stored in the answer array at index index.
			answer[index]=deque.getFirst(); 
			index++;
		}
		//Finally, the method returns the answer array containing the maximum elements for each sliding window of size windowSize.
		return answer;
	}

}
