package com.prudhvi.hashing;

import java.util.HashMap;

public class MinumumSubString {
	/*
	Problem Description
		Given a string A and a string B, find the window with minimum length in A, which will contain all the characters in B in linear time complexity.
		Note that when the count of a character c in B is x, then the count of c in the minimum window in A should be at least x.
	Note:
		If there is no such window in A that covers all characters in B, return the empty string.
		If there are multiple such windows, return the first occurring minimum window ( with minimum start index and length )

	Problem Constraints
		1 <= size(A), size(B) <= 106
	 */

	public static void main(String[] args) {
		String A = "ADOBECODEBANC";
		String B = "ABC";
		String answerArray=minimumSubStringOfString(A, B);
		System.out.println(answerArray);
		String answerHashMap=minWindow(A, B);
		System.out.println(answerHashMap);
		

	}
	/*
	 			*** Best Approach ***
	The minimumSubStringOfString method takes two strings, string and subString, as inputs. 
	It finds the minimum substring of string that contains all the characters from subString. 
	It uses a sliding window approach to find the minimum substring and returns it as a result.
	
	The checkFrequencyOfTwoArrays method is a helper method that checks if the frequencies of characters in subString are present in string. 
	The main method uses a sliding window approach to find the minimum substring
	
	Time Complexity:
			The time complexity of this code is O(n + m), where n is the length of the string and m is the length of the subString. 
			The code iterates over both strings once, and the sliding window approach takes linear time.
	
	Space Complexity:
			The space complexity of the code is O(1) because the frequency arrays have a fixed size of 256, 
			which does not depend on the input size.
	 */
	public static String minimumSubStringOfString(String string,String  subString){
		//create two integer arrays to store the frequency count of characters in the string and subString. 
		//Each array has a length of 256, assuming ASCII character set.
		int[] stringFrequency = new int[256];
        int[] subStringFrequency = new int[256];
        //iterates over each character in subString and updates the frequency count in the subStringFrequency array.
        for (char c : subString.toCharArray()) {
            subStringFrequency[c]++;
        }
        //These variables keep track of the start and end indices of the minimum substring found so far, along with its size.
        int start = 0;
        int end = 0;
        int size = Integer.MAX_VALUE;
        //These variables are pointers that represent the sliding window. 
        //pointerI represents the start of the window, and pointerJ represents the end of the window.
        int pointerI = 0;
        int pointerJ = 0;
        //that continues until the end of the string is reached.
        while (pointerJ < string.length()) {
        	//the code updates the frequency count of the character at pointerJ in the stringFrequency array.
            int jCharacter =string.charAt(pointerJ);
            stringFrequency[jCharacter]++;
            //which checks if the current window contains all the characters of subString.
            while(checkFrequencyOfTwoArrays(subStringFrequency, stringFrequency)) {
            	//If the condition is true, the code checks if the current window's size is smaller than the previously found minimum size. 
            	//If so, it updates the start, end, and size variables accordingly.
                if ((pointerJ - pointerI + 1) < size) {
                    start = pointerI;
                    end = pointerJ;
                    size = pointerJ - pointerI + 1;
                }
                //The code then decreases the frequency count of the character at pointerI in the stringFrequency array 
                //and moves the pointerI to the right, effectively shrinking the window from the left.
                int iCharacter =string.charAt(pointerI);
                stringFrequency[iCharacter]--;
                pointerI++;
            }
            //After exiting the inner while loop, pointerJ is incremented to move the window to the right.
            pointerJ++;
        }
        //Once the while loop finishes, the code checks if a minimum substring was found by checking if the size variable is still Integer.MAX_VALUE. 
        //If so, it means no valid substring was found, and an empty string is returned.
        if (size == Integer.MAX_VALUE) {
            return "";
        }
        //he code uses the substring method to extract the minimum substring from the string using the start and end indices, and returns it.
        return string.substring(start, end + 1);
	}
	/*
	The checkFrequencyOfTwoArrays method is a helper method that checks if the frequency count of characters in subString is present in string. 
	It iterates over the arrays and compares the counts. If any character in string has a lower frequency than the corresponding character in subString, it returns false. 
	Otherwise, it returns true.
	
	Note:
		The checkFrequencyOfTwoArrays method also has a time complexity of O(1) as it iterates over fixed-sized arrays.
	 */
    public static boolean checkFrequencyOfTwoArrays(int[] subString, int[] string) {
        for (int i = 0; i<subString.length; i++) {
            if (string[i]<subString[i]) {
                return false;
            }
        }
        return true;
    }
    //another way using HashMap uses Space complexity is O(subString length)
    public static String minWindow(String string, String subString) {
    	// if the length of A is less than the length of B. If it is, it means that A cannot contain all the characters of B, so an empty string is returned.
        if (string.length() < subString.length())
            return "";
        //store the count of characters in subString
        HashMap < Character, Integer > counts = new HashMap <> ();
        //loop iterates over the characters in B and updates the count in the counts HashMap:
        for (int i = 0; i < subString.length(); i++) {
            if (counts.get(subString.charAt(i)) == null) {
                counts.put(subString.charAt(i), 1);
            } else {
                counts.put(subString.charAt(i), counts.get(subString.charAt(i)) + 1);
            }
        }
        //variables to keep track of the starting index, length, and total count of characters in subString that have been found in string
        int start = 0;
        int length = 0;
        int total = 0;
        //loop iterates over the characters in A using two pointers, head and tail, to find the minimum window:
        for (int head = 0, tail = 0; tail < string.length(); tail++) {
        	//checks if the current character at tail is present in subString. 
        	//If it is not, the code continues to the next character in string.
        	//Otherwise, it decreases the count for that character in counts and checks if the count is still positive or zero:
        	// If this character is not present in B at all, 
            // we don't care about this character. 
            if (counts.get(string.charAt(tail)) == null) {
                continue;
            }
            counts.put(string.charAt(tail), counts.get(string.charAt(tail)) - 1);
            // We check if the current character represented by tail caused
            // a character to be included which is relevant to B and is still
            // in deficit. 
            // For example, if B has 3 As, then the first 3 A are relevant to us
            // but the 4th one is not. 
            if (counts.get(string.charAt(tail)) >= 0) {
                total++;
            }
            // check if we have all characters in B covered. 
            if (total == subString.length()) {
                // Now move the head pointer till popping out those characters 
                // still makes sure that all characters in B are covered. 
                while (counts.get(string.charAt(head)) == null || counts.get(string.charAt(head)) < 0) {
                    if (counts.get(string.charAt(head)) != null) counts.put(string.charAt(head), counts.get(string.charAt(head)) + 1);;
                    head++;
                }
                // Now [head - 1, tail] is a valid substring. Update the answer. 
                if (length == 0 || tail - head + 1 < length) {
                    length = tail - head + 1;
                    start = head;
                }
            }
        }
        return string.substring(start, start + length);
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
