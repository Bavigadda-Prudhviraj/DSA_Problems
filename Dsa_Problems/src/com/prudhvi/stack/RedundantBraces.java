package com.prudhvi.stack;

import java.util.Stack;

public class RedundantBraces {
	/*
	Problem Description
			Given a string A denoting an expression. It contains the following operators '+', '-', '*', '/'.
			Check whether A has redundant braces or not.
			NOTE: A will be always a valid expression and will not contain any white spaces.

	Problem Constraints
			1 <= |A| <= 105
	
	Output Format
			Return true if A has redundant braces else, return false.
	 */

	public static void main(String[] args) {
		String string="a+b";//(a+(a+b))
		boolean answer=ContainsUnnecessaryOrExtraBrackets(string);
		System.out.println(answer);
	}
	/*
	The given code aims to check whether a given string containing arithmetic expressions has unnecessary or extra brackets. 
	The method function, ContainsUnnecessaryOrExtraBrackets, takes a string as input and returns a boolean value, indicating whether the string contains unnecessary or extra brackets.
	
	Time Complexity:
			The time complexity of the method is O(N), where N is the length of the input string. 
			This is because the method iterates through the entire string once and performs constant-time operations on the stack for each character.

	Space Complexity:
			The space complexity of the method is O(N), as it uses a stack to store characters encountered during iteration. 
			In the worst case, the stack may contain all the characters of the input string if no unnecessary brackets are found.
	 */
	private static boolean ContainsUnnecessaryOrExtraBrackets(String string) {
		//Create an empty stack to keep track of opening brackets.
		Stack<Character> stack=new Stack<>();
		//Iterate through each character in the input string.
		for(int i=0;i<string.length();i++) {
			char character =string.charAt(i);
			//If the current character is an opening bracket ( or an arithmetic operator (+, -, *, /), push it onto the stack.
			if(character!=')' && (character=='(' || character=='+' || character=='-' || character=='*' || character=='/')) {
				stack.push(character);
			}
			//If the current character is a closing bracket:
			else if(character==')'){
				//a. If the top of the stack contains an opening bracket, then there is an unnecessary bracket, and the function returns true.
				if(stack.peek()=='(') {
					return true;
				}
				//b. If not, keep popping elements from the stack until an opening bracket is encountered and remove it as well.
				else {
					while(!stack.isEmpty()&&stack.peek()!='('){
						stack.pop();
					}
					if(!stack.isEmpty()) {
						stack.pop();
					}
				}
			}
		}
		//After iterating through all characters, check whether the stack is empty or if it contains any arithmetic operators. 
		//If so, there are extra brackets, and the function returns false. 
		if(stack.isEmpty() || (stack.contains('+') || stack.contains('-') || stack.contains('*') || stack.contains('/'))) {
			return false;
		}
		//Otherwise, return true as there are unnecessary brackets or missing closing brackets.
		else{
			return true;
		}
		
	}

}
