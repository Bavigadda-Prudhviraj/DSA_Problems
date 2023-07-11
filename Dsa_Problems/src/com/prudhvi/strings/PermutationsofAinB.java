package com.prudhvi.strings;



public class PermutationsofAinB {
	/*
	Problem Description
		You are given two strings, A and B, of size N and M, respectively.
		You have to find the count of all permutations of A present in B as a substring.
		You can assume a string will have only lower case letters.
 
	Problem Constraints
		1 <= N < M <= 105
	 */

	public static void main(String[] args) {
		String  A= "abc";
		String	B = "abcbacabc";
		int answer=permutaionOfAinB(A,B);
		System.out.println(answer);
	}
	/*
	The method permutaionOfAinB takes two strings as input, text and message, and returns the count of permutations of text in message. 
	The method checkArr is a helper method to compare the frequency arrays.
	
	Time Complexity:
		The time complexity of the code is O(n), where n is the length of the message string.
	Space Complexity:
	 	The space complexity is O(1) since the size of the frequency arrays is fixed.
	 */
	private static int permutaionOfAinB(String text, String message) {
		//a count variable permutaionsOfAinBCount to keep track of the count of permutations.
		int permutaionsOfAinBCount=0;
		//Create an integer array textFrequencyArr of size 26 to store the frequency of characters in the text string.
		int[] textFrequencyArr=new int[26];
		//Iterate through the text string and update the frequency array textFrequencyArr accordingly.
		for(int i=0;i<text.length();i++) {
			textFrequencyArr[text.charAt(i)-'a']++;
		}
		//Create another integer array compareFrequecyArr of size 26 to store the frequency of characters in the message string.
		int[] compareFrequecyArr=new int[26];
		//Iterate through the first text.length() characters of the message string and update the frequency array compareFrequecyArr.
		for(int i=0;i<text.length();i++){
			compareFrequecyArr[message.charAt(i)-'a']++;
		}
		//Check if the frequency arrays textFrequencyArr and compareFrequecyArr are equal using the checkArr method. 
		if(checkArr(textFrequencyArr,compareFrequecyArr)) {
			//If they are equal, increment the permutaionsOfAinBCount variable.
			permutaionsOfAinBCount++;
		}
		int pointerI=1;
		int pointerJ=text.length();
		//Iterate through the remaining characters of the message string using a sliding window approach.
		while (pointerJ<message.length()){
			//Update the frequency array compareFrequecyArr by decrementing the count of the character at pointerI-1 and incrementing the count of the character at pointerJ.
			compareFrequecyArr[message.charAt(pointerI-1)-'a']--;
			compareFrequecyArr[message.charAt(pointerJ)-'a']++;
			//Check if the frequency arrays textFrequencyArr and compareFrequecyArr are equal using the checkArr method. 
			if(checkArr(textFrequencyArr, compareFrequecyArr)) {
				//If they are equal, increment the permutaionsOfAinBCount variable.
				permutaionsOfAinBCount++;
			}
			//Move the pointers pointerI and pointerJ to the next position.
			pointerI++;
			pointerJ++;
		}
		//Return the permutaionsOfAinBCount as the result.
		return permutaionsOfAinBCount;
	}
	public static boolean checkArr(int[] text,int[] message) {
		for(int i=0;i<text.length;i++) {
			if(text[i]!=message[i]) {
				return false;
			}
		}
		return true;
		
	}
	

}
