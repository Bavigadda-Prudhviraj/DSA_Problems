package com.prudhvi.stack;

import java.util.Stack;

public class PassingGame {

	public static void main(String[] args) {
		/*
		A = 10
 		B = 23
 		C = [86, 63, 60, 0, 47, 0, 99, 9, 0, 0] answer=63
		 */
		int[] arr= {23,0,2,0,39,28,19,0,0,0};
		int A=10;
		int B=38;
		int answer=passingGame(A,B,arr);
		System.out.println(answer);

	}
	/*
	he given code simulates a passing game where a pass is thrown from one person to another. 
	The game starts with a person having the pass (guyWithPassId), and a list of passes (passesId) represents the sequence of who passes the ball to whom. 
	A pass to "0" indicates a dropped pass.
	
	Time Complexity:
			The time complexity of the code is O(n), where n is the number of elements in the passesId array. 
			In the worst case, we need to process all elements of the passesId array once.

	Space Complexity:
			The space complexity of the code is O(n) in the worst case. 
			This occurs when all elements in the passesId array are valid passes (not "0"), and they are pushed onto the passes stack. 
			In the best case scenario, the space complexity is O(1) when all passes are dropped (all elements are "0"), and the stack remains empty.
	
	
	 */
	public static int passingGame(int A, int guyWithPassId, int[] passesId) {
		//Create a new stack passes to keep track of the people who have the pass.
	       Stack<Integer> passes=new Stack<>();
	       //Start iterating through each element of the passesId array.
	       for(int i=0;i<passesId.length;i++){////Get the current element passId at index i of the passesId array.
	    	  // If the passId is equal to "0", it means the pass is dropped.
	    	  //In this case, check if the passes stack is not empty (there is someone with the pass), and if so, remove the top person from the passes stack using passes.pop(), simulating the drop.
	           if(passesId[i]==0){
	               if(!passes.isEmpty()){
	                   passes.pop();
	               }
	           }
	           //If the passId is not equal to "0", it means a successful pass is made. 
	           //In this case, push the passId onto the passes stack, representing that the person now has the pass.
	           else{
	               passes.push(passesId[i]);
	           }
	       }
	       //After processing all the passes in the passesId array, if the passes stack is empty, 
	       //it means no one has the pass, so return the initial guyWithPassId.
	       if(passes.isEmpty()){
	           return guyWithPassId;
	       }
	       //Otherwise, return the passId of the top person in the passes stack, as that person currently has the pass.
	       return passes.peek();
	}
}
