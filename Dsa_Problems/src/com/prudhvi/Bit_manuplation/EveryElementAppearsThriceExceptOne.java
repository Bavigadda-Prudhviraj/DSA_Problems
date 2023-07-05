package com.prudhvi.Bit_manuplation;

import java.util.Iterator;

public class EveryElementAppearsThriceExceptOne {
	/*
	Problem Description
		Given an array of integers, every element appears thrice except for one, which occurs once.
		Find that element that does not appear thrice.
	NOTE: 
		Your algorithm should have a linear runtime complexity.
		Could you implement it without using extra memory?
	Problem Constraints
		2 <= A <= 5*106
		0 <= A <= INTMAX
	 */

	public static void main(String[] args){
		int[] arr= {1, 2, 4, 3, 3, 2, 2, 3, 1, 1};
		int answer=UniqueElement(arr);
		System.out.println(answer);
	}
	/*
	The function UniqueElement takes an array arr as input, which contains integers. 
	All elements in the array appear three times, except for one unique element that appears only once. 
	The goal of the function is to find and return that unique element.
	
	Time Complexity: 
			The time complexity of this code is O(32 * n) ≈ O(n), where n is the number of elements in the input array arr. 
			The outer loop iterates 31 times (for each bit position), and the inner loop iterates through all elements in arr, 
			which takes linear time.

	Space Complexity: 
			The space complexity of this code is O(1), as it uses a constant amount of extra memory, independent of the input size. 
			The additional space is used for the variables answer, str, setBitsCount, and LeftShiftnum, all of which require a constant amount of memory.
	
	 */
	public static int UniqueElement(int[] arr){
		int answer=0;//The function initializes a variable answer to 0. This variable will store the unique element.
		//The function then goes through 31 bits (0 to 30) because we need to consider each bit's contribution to the unique number.
		for(int i=0;i<=30;i++) {
			int setBitsCount=0;
			//For each bit position i, the function calculates the number of set bits at that position in the array arr. 
			//It iterates through the array arr and uses the helper function checkBit to determine if the bit is set to 1.
			for(int j=0;j<arr.length;j++) {
				if(checkBit(arr[j],i)==1) {
					setBitsCount++;
				}
			}
			//After counting the set bits at position i, the function checks if the count is not a multiple of 3. 
			//If it is not a multiple of 3, it means the bit at position i in the unique element is set to 1.
			//To set the corresponding bit in the answer, it uses the left shift operator (<<) to create the appropriate bitmask, (1 << i), and adds it to the answer
			if(setBitsCount%3!=0) {
				answer=answer+(1<<i);
			}
			//The function repeats this process for all 31 bits, and at the end of the loop, the answer will hold the unique element.
		}
		return answer;
	}
	//The checkBit function takes an integer num and a bit position bit as input and checks whether the bit at position bit in the number num is 1 or 0.
	public static int checkBit(int num, int bit) {
		////It creates a bitmask using the left shift operator 1 << bit, which has only the bit at position bit set to 1.
        int LeftShiftnum = 1 << bit;
        //it then performs a bitwise AND operation between num and the bitmask (LeftShiftnum).
        //If the result is 0, it means the bit at position bit in num is 0, and the function returns 0. 
        if((num & LeftShiftnum) == 0){
            return 0;
        }
        //Otherwise, it returns 1, indicating that the bit is 1.
        return 1;
    }
	/*
	  this will also work for check bit
	  if ( ( A & ( 1<<B ) )==(1<<B)){
             return 1;
         }
         return 0;
	 */

}
