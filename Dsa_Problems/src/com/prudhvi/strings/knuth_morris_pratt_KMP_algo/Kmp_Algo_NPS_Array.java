package com.prudhvi.strings.knuth_morris_pratt_KMP_algo;

import java.util.Arrays;

public class Kmp_Algo_NPS_Array {

	public static void main(String[] args) {
		String str="ababdabacdababcabab";//ABABDABACDABABCABAB
		int[] lpsArray=lPS_array_construction(str);
		System.out.println(Arrays.toString(lpsArray));

	}
	/*
	The method lPS_array_construction takes a string str as input and returns an integer array LPS_arr, 
	which represents the Longest Proper Prefix which is also a Suffix (LPS) array for the given string.
	
	Time Complexity:
			The time complexity of the code is O(n), where n is the length of the input string str. 
			
	Space Complexity:
			The space complexity is O(n) since we create an array of the same length as the input string to store the LPS values.
	 */
	private static int[] lPS_array_construction(String str) {
		//Create an integer array LPS_arr with the same length as the input string str to store the LPS values.
		int[] LPS_arr=new int[str.length()];
		//Iterate through the characters of the string starting from the second character (index 1).
		for(int i=1;i<str.length();i++) {
			//Get the LPS value of the previous character LPS_arr[i-1] and assign it to the variable index.
			int index=LPS_arr[i-1]; // i-1 it doesn't work
			//Enter a while loop to find the LPS value for the current character.
			//Check if the character at the current index str.charAt(i) is equal to the character at the index  in the string.
			while (str.charAt(i)!=str.charAt(index)){
				if(index<=0) {
					index=-1;
					break;
				}
				//If they are not equal, move to the previous LPS value by assigning index with the LPS value of the previous index LPS_arr[index-1].
				 index=LPS_arr[index-1];
				 //Repeat this process until a match is found or the index becomes less than or equal to 0.
			}
			//If a match is found, assign index + 1 as the LPS value for the current character in LPS_arr.
			LPS_arr[i]=index+1;
		}
		return LPS_arr;
	}

}
  