package com.prudhvi.stack;

import java.util.Stack;

public class DoubleCharacterTrouble {
	/*
	Problem Description
		You have a string.
	To transform the string, you should perform the following operation repeatedly:
		1.Identify the first occurrence of consecutive identical pairs of characters within the string.
		2.Remove this pair of identical characters from the string.
		3.Repeat steps 1 and 2 until there are no more consecutive identical pairs of characters.
		4.The final result will be the transformed string.

	Problem Constraints
		1 <= |A| <= 100000
	 */

	public static void main(String[] args) {
		String string="abccbc";
		String answerString=doubleCharacterTrouble(string);
		System.out.println(answerString);

	}
	/*
	The given code takes a string as input and returns a new string with consecutive duplicate characters removed (doubling removed). 
	If two same consecutive characters appear, only one of them will be retained in the output string. 
	For example, "aaabbcddd" will be converted to "abc".
	
	Time Complexity:
		The time complexity of the code is O(n), where n is the length of the input string string. 
		In the worst case, we may need to process all characters in the string once.

	Space Complexity:
		The space complexity of the code is O(n) in the worst case. 
		This occurs when all characters in the string are unique, and they are pushed onto the charactersStack. 
		In the best case scenario, the space complexity is O(1) when there are no consecutive duplicates in the input string, and the stack remains empty.
		Example:aabbccdd
	 */
	public static String doubleCharacterTrouble(String string){
		//Create a new stack charactersStack to store characters encountered in the input string.
		Stack<Character> charactersStack=new Stack<>();
		//Start iterating through the characters of the input string string.
		for(int i=0;i<string.length();i++) {
			//Get the character character at the current index i of the string.
			char character=string.charAt(i);
			//Check if the charactersStack is empty. If so, push the current character character onto the stack.
			if(charactersStack.isEmpty()) {
				charactersStack.push(character);
			}
			//If the stack is not empty, check the character at the top of the stack (the stackTopCharacter).
			else {
				char stackTopCharacter=charactersStack.peek();
				//If the stackTopCharacter is the same as the current character character, it means we have found consecutive duplicate characters. 
				//In this case, pop the stackTopCharacter from the stack to remove the duplicates.
				if(stackTopCharacter==character) {
					charactersStack.pop();
				}
				//If the stackTopCharacter is different from the current character character, it means we have found a new character, so push it onto the stack.
				else {
					charactersStack.push(character);
				}
			}
		}
		//Create a new StringBuilder named answerStringBuilder to construct the resulting string
		StringBuilder answerStringBuilder=new StringBuilder();
		//While the charactersStack is not empty, pop the characters from the stack and append them to the answerStringBuilder. 
		//This step ensures that the characters are retrieved in the correct order.
		while(!charactersStack.isEmpty()){
			//get the last character from the stack
			char stackTopCharacter=charactersStack.peek();
			//append that character to the string builder String  
			answerStringBuilder.append(stackTopCharacter);
			//remove that character from the stack.
			charactersStack.pop();
		}
		//Finally, reverse the answerStringBuilder and convert it to a string using toString().
		return answerStringBuilder.reverse().toString();
		
	}

}
