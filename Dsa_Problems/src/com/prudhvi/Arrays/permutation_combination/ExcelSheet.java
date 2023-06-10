package com.prudhvi.Arrays.permutation_combination;

import java.util.*;

/*
  Given a positive integer A, return its corresponding column title as it appears in an Excel sheet.

	For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    
    
    Algo:- (1-26 ) A-Z
    	   (27-52) AA-AZ
    	   (53-78) BA-BZ
    	   (79-104)CA-CZ
 */

public class ExcelSheet {

	public static void main(String[] args) {

		int num=980089;
		String ans=solver(num);
		System.out.println("final output:-  "+ans);

	}

	private  static String solver(int A) {
		
		 //If the number is 0, the loop doesn't run, so we need to take special care of it
		//The reason for this is that if the input number is 0, the loop won't run at all, resulting in an empty string. By adding 'A' at the beginning, we ensure that the output correctly represents the value of 0 in our number system.
		 if(A==1) {
			 return "A";
		 }
		//Create a hashmap to store digits as keys and characters as values.
		 HashMap<Integer,Character> letterCode=new HashMap<>();
	        char letter='A';
	        for(int i=0;i<26;i++){
	            letterCode.put(i,letter);
	            letter++;

	        }
	        //We are creating a string to store characters in reverse order. The purpose of this is because we need to add the characters in reverse order, just like when converting into a binary system. By storing the characters in reverse, we ensure that they are appended to the string in the correct order, mimicking the reversed nature of binary conversions.
	        String ans=new String();
	        
	        //We are subtracting 1 from the input value because the hashmap stores characters in a zero-based index format. For example, if the input value is 26, we subtract 1 to get 25. In this case, 25 corresponds to the character 'Z' in the Hash map, allowing us to retrieve the correct character based on the given input.
	        int num=A-1;
	        while(num>0) {
	        	//By calculating the remainder, we determine the index needed to access the corresponding character in the hashmap. Each remainder value represents a specific character in our number system. For example, if the remainder is 0, it corresponds to 'A' in the hashmap, while a remainder of 1 corresponds to 'B', and so on.
	        	int let=num%26;
	        	//Using the remainder as an index, we fetch the correct character from the hashmap. This character is then added to the result string. This process repeats until we have obtained the characters for all the remainders, effectively converting the number into its character representation based on the has hashmap
	        	char ch=letterCode.get(let);
	        	//By appending the character at the beginning of the string, we ensure that the characters are placed in the correct order, resembling the reversed nature of binary conversion. This approach allows us to generate the desired string representation for our number system conversion accurately.
	        	ans=ch+ans;
	        	//By dividing the number by 26, we effectively reduce its value. The purpose of this division is to adjust the number so that it aligns with the range of indices in the hashmap. After dividing, we subtract 1 to account for the zero-based indexing, which means the character 'A' corresponds to the index 0.
	        	num=(num/26)-1;
	        	//This condition is for handling corner cases where the remainder is 0 at the final step. If the remainder is 0, it means that the input number is completely divisible by the base (e.g., 26 in this case). In such scenarios, we need to add the character 'A' at the beginning of the result string.
	        	if(num==0) {
	        		ans="A"+ans;
	        	}
	        }
		
		return ans;
	}

}
