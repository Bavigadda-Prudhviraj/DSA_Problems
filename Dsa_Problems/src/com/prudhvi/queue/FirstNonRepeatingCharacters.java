package com.prudhvi.queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FirstNonRepeatingCharacters {
	/*
	Problem Description
			Given a string A denoting a stream of lower case alphabets, you have to make a new string B.
			B is formed such that we have to find the first non-repeating character each time a character is inserted to the stream and append it at the end to B. 
			If no non-repeating character is found, append '#' at the end of B.
	Problem Constraints
			1 <= |A| <= 100000
	 */
	public static void main(String[] args) {
		String input="jyhrcwuengcbnuchctluxjgtxqtfvrebveewgasluuwooupcyxwgl";//jjjjjjjjjjjjjjjjjjjjjyyyyyyyyyyyyyyyyyyyyyyyyyyyqqqqq //iiiiiiiiii//abadbc->aabbdd //abcabc->aaabc#
		String answer=firstNonRepeatingCharacter(input);
		System.out.println(answer);
	}
	/*
	method that takes an input string and returns a new string containing the first non-repeating character at each step while iterating through the input string. 
	The code uses a combination of a HashMap to track character frequencies and a Queue to maintain the order of characters encountered.
	
	Time Complexity:
			The code iterates through each character in the input string exactly once, performing constant-time operations within the loop. 
			Both the put operation on the hashMap and the queue operations (add and remove) are constant time on average. 
			Therefore, the time complexity is O(N), where N is the length of the input string.

	Space Complexity:
			The space complexity is determined by the storage used for the hashMap, queue, and string. 
			The maximum space required is proportional to the size of the character set used in the input string. 
			Therefore, in the worst case, the space complexity is O(N), where N is the size of the character set (usually constant and small). 
			The string and other local variables add constant space overhead.
	 */
	private static String firstNonRepeatingCharacter(String input) {
		//This HashMap is used to store the frequency of each character encountered in the input string.
		HashMap<Character,Integer> hashMap=new HashMap<>();
		//This Queue is used to store the characters in the order they are encountered in the input string.
		Queue<Character> queue=new LinkedList<>();
		//This StringBuilder is used to build the output string containing the first non-repeating character at each step.
		StringBuilder string=new StringBuilder();
		//The loop iterates through each character in the input string:
		for(int i=0;i<input.length();i++) {
			char character=input.charAt(i);
			//The frequency of the character is updated in the hashMap.
			hashMap.put(character,hashMap.getOrDefault(character,0)+1);
			//If the frequency becomes 1 (indicating it's the first occurrence), the character is added to the queue.
			if(hashMap.get(character)==1) {
				queue.add(character);
			}
			//The queue is checked: characters are removed from the front of the queue if their frequency is greater than 1 (indicating they are no longer non-repeating).
			while(!queue.isEmpty() && hashMap.get(queue.peek())>1){
				queue.remove();
				
			}
			//If the queue is not empty, the first character in the queue (the first non-repeating character) is appended to the string.
			if(!queue.isEmpty()){
				string.append(queue.peek());
			}
			// If the queue is empty, '#' is appended to the string.
			else {
				string.append('#');
			}
		}
		//Finally, the method returns the constructed string as the result.
		return string.toString();
	}

}
