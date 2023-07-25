package com.prudhvi.stack;

import java.util.Arrays;
import java.util.Stack;

public class NearestGreaterElementLeft {

	public static void main(String[] args) {
		int[] arr= {17,18,5,4,6,1};
		int[] answerArr=nerestGratestElement(arr);
		System.out.println(Arrays.toString(answerArr));

	}
	/*
	The method nearestGreatestElement takes an integer array  as input and returns an array nearestGreatestElement of the same size,
 	where nearestGreatestElement[i] is the nearest greater element to the left of array[i]. 
	If there is no greater element to the left, the value is set to -1.
	
	Time Complexity:
			The time complexity of this algorithm is O(n), where n is the size of the input array. 
			The for loop iterates through the array once, and each element is pushed and popped from the minValues stack at most once.

	Space Complexity:
			Space Complexity is O(N) because of stack
			The space complexity of this algorithm is O(n), where n is the size of the input array. 
			The nearestSmaller array and the minValues stack both use additional space proportional to the size of the input array.
	
	 */
	private static int[] nerestGratestElement(int[] arr) {
		//An array nearestGreatestElement of the same size as array is created to store the nearest greater elements.
		int[] nearestGratestelement=new int[arr.length];
		//A stack greatestElementStack is used to keep track of potential nearest greater elements.
		Stack<Integer> gratestElementStack=new Stack<>();
		//The for loop iterates through the array  from left to right.
		for(int i=0;i<arr.length;i++) {
			//the code uses a while loop to pop elements from the greatestElementStack stack until the top element becomes greater than the current element array[i]. 
			//This step ensures that we keep track of elements to the left that are greater than the current element.
			while(!gratestElementStack.isEmpty() && gratestElementStack.peek()<=arr[i]){
				gratestElementStack.pop();
			}
			//After the while loop, if the greatestElementStack stack is not empty, it means there is a nearest greater element to the left, and it is at the top of the stack. 
			//So, the value at index i in the nearestGreatestElement array is set to the top element of the greatestElementStack stack.
			if(!gratestElementStack.isEmpty()) {
				nearestGratestelement[i]=gratestElementStack.peek();
			}
			//If the greatestElementStack stack is empty, it means there is no greater element to the left, so the value at index i in the nearestGreatestElement array is set to -1.
			else {
				nearestGratestelement[i]=-1;
			}
			//Finally, the current element array[i] is pushed onto the greatestElementStack stack to be considered for the nearest greater elements of future elements.
			gratestElementStack.push(arr[i]);
		}
		return nearestGratestelement;
	}

}
