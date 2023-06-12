package com.prudhvi.maths.permutation_combination;

import java.util.HashMap;

public class ExcelSheetStringToNum {
	/*
	 	Given a column title as appears in an Excel sheet, return its corresponding column number.
	 	Problem Constraints
			1 <= length of the column title <= 5
	 	
	 	Algo:-
	 			1.Initialize a variable result to 0 to store the column number.
				2.Iterate through each character in the column title string.
				3.Convert the current character into its corresponding column number value. In Excel, column titles consist of upper case letters A to Z, where A represents 1, B represents 2, and so on.
				4.You can convert a character to its corresponding column number value by subtracting the ASCII value of 'A' from it and adding 1.
				5.For example, if the current character is 'C', the corresponding column number value would be ord('C') - ord('A') + 1 = 3.
				6.Multiply the column number value by 26 raised to the power of its position from the right.
				8.For example, if the current character is 'C', the column number value would be result * 26 + (c - 'A' + 1);.
				9.Add the column number value to the result.
				10.Repeat steps 3-5 for each character in the column title.
				11.Return the final result as the column number.
				
				Time complexity=O(1) because of Constraints
				Space complexity=O(1)
	 
	 
	 */
	public static void main(String[] args) {
		//pass only caps letters only
		String columnString="AA";
		int ans=titleToNumber(columnString);
		System.out.println(ans);

	}
	public static int titleToNumber(String A) {
        //Initialize a variable result to 0 to store the column number.
        int value = 0;
	    // Iterate through each character in the column title string,
	    for(int i = 0; i < A.length(); i++){
	    	//Convert the current character into its corresponding column number value. In Excel, column titles consist of upper case letters A to Z, where A represents 1, B represents 2, and so on.
	        char c = A.charAt(i);
	        //You can convert a character to its corresponding column number value by subtracting the ASCII value of 'A' from it and adding 1.
	        //Multiply the column number value by 26 raised to the power of its position from the right.
	        value = value * 26 + (c - 'A' + 1);
	    }
	    //return answer
	    return value;
    }
	public static int anotheWay(String A) {
		 HashMap<Character,Integer> charValues=new HashMap<>();
         char letter='A';
         for(int i=1;i<27;i++){
             charValues.put(letter,i);
             letter++;
         }
         int pow=0;
         int ans=0;
         for(int i=A.length()-1;i>=0;i--){
             int valueOfChar=charValues.get(A.charAt(i));
             ans=ans+(int)(valueOfChar*Math.pow(26,pow));
             pow++;

         }
         return ans;
		
	}

}
