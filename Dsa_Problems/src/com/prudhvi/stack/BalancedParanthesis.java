package com.prudhvi.stack;

import java.util.Stack;

public class BalancedParanthesis {
	/*
	Problem Description
		Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in string.

	Problem Constraints
		1 <= |A| <= 100

	Output Format
		Return false if the parenthesis sequence is not balanced.
		Return true if the parenthesis sequence is balanced.
	 */

	public static void main(String[] args) {
		String string="{([])}"; 
		boolean answer=isBalancedParenthesis(string);
		System.out.println(answer);
	}
	/*
	The method function checks if the input string has balanced parentheses using a stack data structure. 
	It iterates through each character of the string, and whenever it encounters an opening parenthesis, it pushes it onto the stack.
 	When it encounters a closing parenthesis, it checks if the top of the stack contains the corresponding opening parenthesis.
 	If not, it returns false, indicating that the string is not balanced. 
 	If the stack is empty at the end of the iteration, it means all parentheses are balanced, and the method returns true.
	
	Time Complexity:
			The time complexity of the code is O(n), where n is the length of the input string. 
			In the worst case, we may need to process all characters in the string once.

	Space Complexity:
			The space complexity of the code is O(n) in the worst case. 
			This occurs when all characters in the string are opening parentheses, and they are pushed onto the paranthesisStack. 
			However, in the best case scenario, the space complexity is O(1) as the stack remains empty when the string does not contain any opening parentheses.
	 */
	public static boolean isBalancedParenthesis(String string) {
		//Create a new stack paranthesisStack to store opening parentheses encountered in the string.
		Stack<Character> paranthesisStack=new Stack<>();
		//Start iterating through the characters of the input string.
		for(int i=0;i<string.length();i++) {
			//Get the character parenthesis at the current index i of the string.
			char parenthesis=string.charAt(i);
			//Check if it's the first character of the string, and if it's a closing parenthesis ('}', ')', or ']'). 
			if(i==0 && (parenthesis==')' || parenthesis=='}' || parenthesis==']')) {
				return false;//If so, return false, as it indicates unbalanced parentheses.
			}
			//If the character parenthesis is an opening parenthesis ('(', '{', '['), push it onto the paranthesisStack.
			else if(parenthesis=='(' || parenthesis=='{' || parenthesis=='[') {
				paranthesisStack.push(parenthesis);				
			}
			//If the character parenthesis is a closing parenthesis ('}', ')', ']'), pop the top element from the stack and check if it matches the current closing parenthesis.
			//If not, return false as it indicates unbalanced parentheses.
			else if(parenthesis==')' || parenthesis=='}' || parenthesis==']'){
				char stackTopChar=paranthesisStack.pop();
				if(parenthesis=='}'){
					if(stackTopChar!='{') {
						return false;
					}
				}
				else if (parenthesis==')') {
					if(stackTopChar!='(') {
						return false;
					}
				}
				else if(parenthesis==']'){
					if(stackTopChar!='[') {
						return false;
					}		
				}
			}
		}
		//After processing all characters, check if there are any remaining elements in the paranthesisStack. 
		//If so, return false, as it indicates unbalanced parentheses.
		if(paranthesisStack.size()!=0) {
			return false;
		}
		//If the stack is empty and all parentheses are balanced, return true.
		return true;
	}

}
