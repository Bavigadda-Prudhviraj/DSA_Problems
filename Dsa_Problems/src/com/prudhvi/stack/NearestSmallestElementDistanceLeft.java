package com.prudhvi.stack;

import java.util.Arrays;
import java.util.Stack;

public class NearestSmallestElementDistanceLeft {

	public static void main(String[] args) {
		int[] arr= {4, 5, 2, 10, 8};
		int[] answerArr=nerestSmallestElementDistance(arr);
		System.out.println(Arrays.toString(answerArr));

	}
	/*
	The method nearestSmallestElementDistance takes an integer array as input and returns an array nearestSmallestElementDistance of the same size, 
	where nearestSmallestElementDistance[i] is the distance from the current element array[i] to the nearest smaller or equal element on the left side.
	If there is no such element, the value is set to -1.
	
	Time Complexity:
			The time complexity of this algorithm is O(n), where n is the size of the input array. 
			The for loop iterates through the array once, and each element is pushed and popped from the minValues stack at most once.

	Space Complexity:
			The distanceStack stack both use additional space proportional to the size of the input array.
			The space complexity of this algorithm is O(n), where n is the size of the input array. 
			The nearestSmaller array and the minValues stack both use additional space proportional to the size of the input array.
	
	 */
	private static int[] nerestSmallestElementDistance(int[] arr) {
		//An array nearestSmallestElementDistance of the same size as array is created to store the distances to the nearest smaller or equal elements.
		int[] nerestSmallestElementDistance=new int[arr.length];
		//A stack distanceStack is used to keep track of potential elements that could be the nearest smaller or equal elements on the left side.
		Stack<Integer> distanceStack=new Stack<>();
		//The for loop iterates through the array from left to right.
		for(int i=0;i<arr.length;i++) {
			//the code uses a while loop to pop elements from the distanceStack stack until the top element becomes smaller than the current element arr[i].
			//This step ensures that we keep track of elements on the left that are smaller or equal to the current element.
			while(!distanceStack.isEmpty() && arr[distanceStack.peek()]>=arr[i]){
				distanceStack.pop();
			}
			//After the while loop, if the distanceStack stack is not empty, 
			//it means there is a nearest smaller or equal element on the left, and the distance from the current element arr[i] to this nearest element is i - distanceStack.peek(). 
			//So, the value at index i in the nearestSmallestElementDistance array is set accordingly.
			if(!distanceStack.isEmpty()) {
				nerestSmallestElementDistance[i]=i-distanceStack.peek();
			}
			//If the distanceStack stack is empty, it means there is no smaller or equal element on the left, so the value at index i in the nearestSmallestElementDistance array is set to -1.
			else {
				nerestSmallestElementDistance[i]=-1;
			}
			//Finally, the current index i is pushed onto the distanceStack stack to be considered for the nearest smaller or equal elements of future elements.
			distanceStack.push(i);
		}
		return nerestSmallestElementDistance;
	}

}
