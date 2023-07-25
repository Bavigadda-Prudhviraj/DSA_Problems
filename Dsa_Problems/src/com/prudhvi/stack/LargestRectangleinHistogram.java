package com.prudhvi.stack;


import java.util.Stack;

public class LargestRectangleinHistogram {
	/*
	Problem Description
		Given an array of integers A.
		A represents a histogram i.e A[i] denotes the height of the ith histogram's bar. Width of each bar is 1.
		Find the area of the largest rectangle formed by the histogram.

	Problem Constraints
		1 <= |A| <= 100000
		1 <= A[i] <= 10000
	 */

	public static void main(String[] args) {
		int[] arr= {2, 1, 5, 6, 2, 3};
		int area=largestRectangleArea(arr);
		System.out.println(area);
		

	}
	/*
	The method largestRectangleArea takes an integer array as input and returns the largest rectangle area that can be formed from the given histogram represented by the array.
	
	Time Complexity:
			The time complexity of this algorithm is O(n), where n is the size of the input array. 
			The algorithm processes the array twice: once from left to right and once from right to left. 
			Each iteration involves constant time operations for stack manipulations.
	Space Complexity:
			The space complexity of this algorithm is O(n), where n is the size of the input array. 
			The algorithm uses additional space to store the leftNearest, rightNearest arrays, and the stack stack, 
			all of which depend on the size of the input array.
	
	 */
	public static int largestRectangleArea(int[] arr) {
		//The first conditional statement checks if the input array has only one element. 
		//If it is true, it means the largest rectangle area is just the height of the single element, and we return that height as the answer.
        if(arr.length==1){
            return arr[0];
        }
        // Step 1: Finding the left nearest smaller elements for each element in the array.
        //An array leftNearest is created to store the indices of the left nearest smaller elements for each element in the array.
        int[] leftNearest=new int[arr.length];
        //A stack stack is used to keep track of potential elements that could be the left nearest smaller elements.
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<arr.length;i++){
        	//Inside the loop, the code uses a while loop to pop elements from the stack stack until the top element becomes smaller than the current element arr[i]. 
        	//This step ensures that we keep track of elements on the left that are smaller than the current element.
            while(!stack.isEmpty() && arr[stack.peek()]>=arr[i]){
                stack.pop();
            }
            //After the while loop, if the stack stack is not empty, it means there is a left nearest smaller element, and its index is stored in the leftNearest array. 
            if(!stack.isEmpty()){
                leftNearest[i]=stack.peek();
            }
            //If the stack stack is empty, it means there is no smaller element on the left, so -1 is stored in the leftNearest array.
            else{
                leftNearest[i]=-1;
            }
            stack.push(i);
        }
        //The stack stack is cleared to reuse it for finding the right nearest smaller elements.
        //The stack stack is cleared to reuse it for finding the right nearest smaller elements.
        stack.clear();
       //Step 2: Finding the right nearest smaller elements for each element in the array.
        
      
        // Step 2: Finding the right nearest smaller elements for each element in the array.
        //An array rightNearest is created to store the indices of the right nearest smaller elements for each element in the array.
        int[] rightNearest=new int[arr.length];
        //The second loop iterates through the array from right to left.
        for(int i=arr.length-1;i>=0;i--){
        	//Inside the loop, the code uses a while loop to pop elements from the stack stack until the top element becomes smaller than the current element arr[i].
        	//This step ensures that we keep track of elements on the right that are smaller than the current element.
            while(!stack.isEmpty() && arr[stack.peek()]>=arr[i]){
                stack.pop();
            }
            //After the while loop, if the stack stack is not empty, it means there is a right nearest smaller element, and its index is stored in the rightNearest array. 
            //If the stack stack is empty, it means there is no smaller element on the right, so the length of the array (arr.length) is stored in the rightNearest array.
            if(!stack.isEmpty()){
                rightNearest[i]=stack.peek();
            }
            else{
                rightNearest[i]=arr.length;
            }
            stack.push(i);
        }
        
        //Step 3: Calculating the maximum area by iterating through each element in the array.
        //The final loop iterates through the array.
        int answer=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
        	//For each element, the code calculates the height of the rectangle, which is the element's value arr[i].
            int height=arr[i];
            //The width of the rectangle is calculated as rightNearest[i] - leftNearest[i] - 1, which represents the number of bars that can be included in the rectangle.
            int width=rightNearest[i]-leftNearest[i]-1;
            //The area of the rectangle is calculated as height * width, and 
            int area=(height*width);
            //the maximum area is updated if a larger area is found during the iteration.
            answer=Math.max(area,answer);
        }
        return answer;
    }

}
