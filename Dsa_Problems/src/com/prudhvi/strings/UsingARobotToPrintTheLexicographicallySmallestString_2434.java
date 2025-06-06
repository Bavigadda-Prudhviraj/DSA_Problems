package com.prudhvi.strings;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class UsingARobotToPrintTheLexicographicallySmallestString_2434 {

	public static void main(String[] args) {
		String s1 = "zza"; //azz
		String s2 = "bac"; //abc
		String s3 = "bdda"; //addb
		System.out.println(robotWithString(s1));

	}
	public static String robotWithString(String input) {
	    int length = input.length();

	    // Holds the smallest character from the current index to the end of the string
	    char[] minCharFromRight = new char[length];
	    minCharFromRight[length - 1] = input.charAt(length - 1);

	    // Fill the minCharFromRight array from right to left
	    for (int i = length - 2; i >= 0; i--) {
	        minCharFromRight[i] = (char) Math.min(input.charAt(i), minCharFromRight[i + 1]);
	    }

	    StringBuilder stack = new StringBuilder(); // Simulates the robot's stack
	    StringBuilder result = new StringBuilder(); // Final result string

	    for (int i = 0; i < length; i++) {
	        stack.append(input.charAt(i));

	        // Determine the smallest character to the right of the current position
	        char minCharAhead = (i == length - 1) ? minCharFromRight[i] : minCharFromRight[i + 1];

	        // Pop from stack while top of stack is less than or equal to the smallest char on the right
	        while (!stack.isEmpty() && stack.charAt(stack.length() - 1) <= minCharAhead) {
	            result.append(stack.charAt(stack.length() - 1));
	            stack.deleteCharAt(stack.length() - 1);
	        }
	    }

	    // Append remaining characters from the stack
	    while (!stack.isEmpty()) {
	        result.append(stack.charAt(stack.length() - 1));
	        stack.deleteCharAt(stack.length() - 1);
	    }

	    return result.toString();
	}
	public static String robotWithStringDeque(String input) {
	    int length = input.length();

	    // Array to keep track of the minimum character from index i to the end of the string.
	    // We use length + 1 to avoid index out-of-bounds and set the last char as '{' (greater than any lowercase letter).
	    char[] minCharFromRight = new char[length + 1];
	    //We use '{' at the length index to make comparisons safe during this line: !stack.isEmpty() && stack.peek() <= minCharFromRight[i + 1]
	    /*
	    This way, even when i == length - 1, i + 1 is length, and minCharFromRight[length] gives us '{', which:

		prevents popping from the stack when the input string has no more characters to compare,
			and avoids index out-of-bounds.
	     */
	    minCharFromRight[length] = '{'; // Sentinel character greater than 'z'

	    // Fill minCharFromRight array from the end of the string backwards
	    for (int i = length - 1; i >= 0; i--) {
	        // At each position, store the minimum character seen so far from that index to the end
	        minCharFromRight[i] = (char) Math.min(input.charAt(i), minCharFromRight[i + 1]);
	    }

	    // This stack holds characters temporarily (simulating robot's 't' string)
	    Deque<Character> stack = new ArrayDeque<>();
	    // This will store the final output string
	    StringBuilder result = new StringBuilder();

	    // Traverse the input string character by character
	    for (int i = 0; i < length; i++) {
	        char currentChar = input.charAt(i);

	        // Push the current character into the stack (t string)
	        stack.push(currentChar);

	        // Try to pop characters from the stack to result if they are <= the smallest char in the remaining input
	        // This ensures the result is lexicographically smallest
	        while (!stack.isEmpty() && stack.peek() <= minCharFromRight[i + 1]) {
	            result.append(stack.pop());
	        }
	    }

	    // Pop and append any remaining characters from the stack (if any)
	    while (!stack.isEmpty()) {
	        result.append(stack.pop());
	    }

	    // Return the final result string
	    return result.toString();
	}




}
