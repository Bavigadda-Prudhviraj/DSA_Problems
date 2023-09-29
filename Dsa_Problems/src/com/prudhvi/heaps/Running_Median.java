package com.prudhvi.heaps;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
/*
	Problem Description
		Given an array of integers, A denoting a stream of integers. New arrays of integer B and C are formed.
		Each time an integer is encountered in a stream, append it at the end of B and append the median of array B at the C.
		Find and return the array C.
		NOTE:
			If the number of elements is N in B and N is odd, then consider the median as B[N/2] ( B must be in sorted order).
			If the number of elements is N in B and N is even, then consider the median as B[N/2-1]. ( B must be in sorted order).
	Problem Constraints
		1 <= length of the array <= 100000
		1 <= A[i] <= 109
 */
public class Running_Median {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5};
		int[] medianArr=runningMedian(arr);
		System.out.println(Arrays.toString(medianArr));

	}
	/*
	The provided code is for finding running medians of elements in an array using two heaps: a max-heap and a min-heap. Let's analyze the time and space complexity of this code:
	
	**Time Complexity:**
		1. **Initialization:** Initializing the max-heap and min-heap with the first element of the array takes constant time, O(1).
		2. **Loop:** The code iterates through the array from the second element to the last element (n-1 times). In each iteration, it performs the following operations:
		   - Adding an element to either the max-heap or min-heap (logarithmic time, O(log n) for each insertion).
		   - Balancing the heaps by ensuring that the difference in the sizes of max-heap and min-heap is at most 1 (logarithmic time, O(log n) for balancing).
		   - Calculating the median (constant time, O(1)).
		   The total time complexity for the loop is O(n * log n) because each iteration involves O(log n) operations.
		3. **Returning the Result:** Creating the `medianArr` array and returning it takes linear time, O(n).
		The overall time complexity of the `runningMedian` function is dominated by the loop, so it is O(n * log n).
	**Space Complexity:**
		1. **Data Structures:** The code uses two priority queues (max-heap and min-heap) to store elements. The space complexity for these priority queues is O(n) because, in the worst case, they can each store all n elements of the input array.
		2. **Other Variables:** The code uses a few other variables, such as `medianArr`, `i`, and temporary variables, which require negligible space compared to the heaps.
		The overall space complexity of the `runningMedian` function is O(n) due to the space used by the priority queues.
		
	In summary, the `runningMedian` function has a time complexity of O(n * log n) and a space complexity of O(n), where 'n' is the number of elements in the input array. It efficiently computes running medians by maintaining a balanced max-heap and min-heap.
	 */
	private static int[] runningMedian(int[] arr) {
		int n=arr.length;
		int[] medianArr=new int[n];// to store the running medians
		//maxHeap to store the first half of the elements (the smaller half).all less than median elements will be in the heap
		PriorityQueue<Integer> maxHeap=new PriorityQueue<>(Collections.reverseOrder());
		//minHeap to store the second half of the elements (the larger half).all greater than median elements will be in this heap
		PriorityQueue<Integer> minHeap=new PriorityQueue<>();
		//Add the first element of the input array to maxHeap, and set the first element of medianArr to be the same value. This initializes the running median calculation.
		maxHeap.add(arr[0]);
		medianArr[0]=arr[0];
		//Iterate through the rest of the elements in array starting from the second element (index 1).
		for (int i = 1; i < arr.length; i++) {
			//Compare the current element with the top element of maxHeap (the largest element in the smaller half).
			//If element is greater than the top element of maxHeap, add it to minHeap. Otherwise, add it to maxHeap.
			if(maxHeap.peek()<arr[i]) {
				minHeap.add(arr[i]);
			}else {
				maxHeap.add(arr[i]);
			}
			//Balance the heaps to maintain the condition that the size of maxHeap is either equal to or one more than the size of minHeap. 
			//This balancing ensures that the median can be easily calculated.
			
			//If the size of maxHeap is less than the size of minHeap, move the minimum element from minHeap to maxHeap.
			if(maxHeap.size()<minHeap.size()) {
				maxHeap.add(minHeap.poll());
			}
			//If the size of maxHeap is more than one greater than the size of minHeap, move the maximum element from maxHeap to minHeap.
			else if(maxHeap.size()-minHeap.size()>1) {
				minHeap.add(maxHeap.poll());
			}
			//Calculate and store the running median:
			//If the total number of elements processed so far is even (i.e., (i + 1) % 2 == 0), then there is an equal number of elements in both heaps, and the median is the average of the maximum element in maxHeap and the minimum element in minHeap.
			if(i+1%2==0) {
				medianArr[i]=(maxHeap.peek()+minHeap.peek())/2;
			}
			//If the total number of elements processed so far is odd, then the median is the maximum element in maxHeap.
			else {
				medianArr[i]=maxHeap.peek();
			}
		}
		//return median array which contains all medians.
		return medianArr;
	}

}
