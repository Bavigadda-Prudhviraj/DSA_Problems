package com.prudhvi.stack;

import java.util.Arrays;
import java.util.Stack;

public class NearestSmallerElementLeft {
	/*
	Problem Description
			Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.
			More formally,
				G[i] for an element A[i] = an element A[j] such that 
				j is maximum possible AND
				j < i AND
				A[j] < A[i]
			Elements for which no smaller element exist, consider the next smaller element as -1.

	Problem Constraints
			1 <= |A| <= 100000
			-109 <= A[i] <= 109
	 */

	public static void main(String[] args) {
		int[] arr= {4, 5, 2, 10, 8};
		int[] answerArr=nerestSmallestElement(arr);
		System.out.println(Arrays.toString(answerArr));

	}
	/*
	The method nearestSmallestElement takes an integer array  as input and returns an integer array nearestSmaller, 
	where each element at index i represents the nearest smaller element to the left of array[i]. 
	If no such smaller element exists, the corresponding element in nearestSmaller is set to -1.
	
	Time Complexity:
			The time complexity of this algorithm is O(n), where n is the size of the input array. 
			The for loop iterates through the array once, and each element is pushed and popped from the minValues stack at most once.

	Space Complexity:
			Space Complexity is O(N) because of stack
			The space complexity of this algorithm is O(n), where n is the size of the input array. 
			The nearestSmaller array and the minValues stack both use additional space proportional to the size of the input array.
	
	 */
	public static int[] nerestSmallestElement(int[] arr) {
		//An empty integer array nearestSmaller of the same length as the input array is initialized to store the nearest smaller elements.
		int[] nearestSmaller=new int[arr.length];
		//A Stack called minValues is used to keep track of potential smaller elements while traversing the array .
        Stack<Integer> minValues=new Stack<>();
        //The for loop iterates through each element of the array.
        for(int i=0;i<arr.length;i++){
        	//The code uses a while loop to pop elements from the minValues stack until the top element becomes less than the current element array[i].
        	//This step ensures that we keep track of elements to the left that are smaller than the current element.
            while(!minValues.isEmpty() && minValues.peek()>=arr[i]){
                minValues.pop();
            }
            //After the while loop, if the minValues stack is not empty, it means there is a nearest smaller element to the left, and it is at the top of the stack. 
            //So, the value at index i in the nearestSmaller array is set to the top element of the minValues stack.
            if(!minValues.isEmpty()){
                nearestSmaller[i]=minValues.peek();
            }
            //If the minValues stack is empty, it means there is no smaller element to the left, so the value at index i in the nearestSmaller array is set to -1.
            else{
                nearestSmaller[i]=-1;

            }
            //Finally, the current element arr[i] is pushed onto the minValues stack to be considered for the nearest smaller elements of future elements.
            minValues.push(arr[i]);
        }
        return nearestSmaller;
	}

}
