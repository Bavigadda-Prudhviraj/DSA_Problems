package com.prudhvi.heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AthLargestElement {
	/*
	Problem Description
		Given an integer array B of size N.		
		You need to find the Ath largest element in the subarray [1 to i], where i varies from 1 to N. In other words, find the Ath largest element in the sub-arrays [1 : 1], [1 : 2], [1 : 3], ...., [1 : N].		
		NOTE: If any subarray [1 : i] has less than A elements, then the output should be -1 at the ith index.

	Problem Constraints
		1 <= N <= 100000
		1 <= A <= N
		1 <= B[i] <= INT_MAX
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<>(List.of(15, 20, 99, 1));
		int k=2;
		ArrayList<Integer> answerArrayList=KthLargestEleInArr(arr,k);
		System.out.println(answerArrayList);

	}

	private static ArrayList<Integer> KthLargestEleInArr(ArrayList<Integer> arr, int k) {
		ArrayList<Integer> answer=new ArrayList<>(arr.size());
		PriorityQueue<Integer> minHeap=new PriorityQueue<>();
		for(int i=0;i<arr.size();i++) {
			if(minHeap.size()<k) {
				minHeap.add(arr.get(i));
				answer.add(-1);
			}
				if(minHeap.peek()<arr.get(i)) {
					minHeap.poll();
					minHeap.add(arr.get(i));
					answer.add(minHeap.peek());
				}
			
		}
		return answer;
	}

}
