package com.prudhvi.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class NPerfectNumbersWith12 {
	/*
	Problem Description
		Given an integer A, you have to find the Ath Perfect Number.
		A Perfect Number has the following properties:
			1.It comprises only 1 and 2.
			2.The number of digits in a Perfect number is even.
			3.It is a palindrome number.
			For example, 11, 22, 112211 are Perfect numbers, where 123, 121, 782, 1 are not.

	Problem Constraints
		1 <= A <= 100000
	 */

	public static void main(String[] args) {
		int num=4;
		String string=perfectumber(num);
		System.out.println(string);
		

	}
	/*
	
	The time complexity of this function is O(number) in both the best-case and worst-case scenarios. The while loop runs number times,
	and each iteration involves constant-time operations like adding and removing elements from the queue and concatenating strings.

	The space complexity is also O(number) in both scenarios because the queue stores number perfect numbers. 
	The StringBuilder reversingNthNumber occupies constant space, so it does not affect the overall space complexity significantly.
	 */

	private static String perfectumber(int num) {
		//Create a queue queue using the ArrayDeque class to store strings of perfect numbers.
		Queue<String> queue=new ArrayDeque<>();
		//Add the strings "1" and "2" to the queue. These are the starting points for generating perfect numbers.
		queue.add("1");
		queue.add("2");
		int count=1;//Initialize a variable count to 1. This variable keeps track of the number of perfect numbers generated.
		//Use a while loop to generate number perfect numbers. The loop runs until count becomes equal to number.
		while(count<num){
			//Inside the loop, and remove the front element from the queue using poll().
			String frontString=queue.poll();
			//Add two new strings to the queue: one by appending "1" to the front element, and the other by appending "2" to the front element.
			queue.add(frontString+"1");
			queue.add(frontString+"2");
			count++;//Increment the count variable to indicate that a new perfect number has been generated.
			
		}
		//After the loop completes, the queue contains number perfect numbers. 
		//Peek the front element from the queue, which will be the nth perfect number.
		
		//Create a StringBuilder called reversingNthNumber and reverse the characters of the nth perfect number.
		StringBuilder reversingNthNumber= new StringBuilder(queue.peek());
		reversingNthNumber.reverse();
		//Return the nth perfect number concatenated with its reversed version.
		return queue.peek()+reversingNthNumber;
	}

}
