package com.prudhvi.Bit_manuplation;

import java.util.Arrays;

public class EveryElementApperersTwiceFindTwoUniqueElements {
	/*
	Given an array of positive integers A, two integers appear only once, and all the other integers appear twice.
	Find the two integers that appear only once.
	Note: Return the two numbers in ascending order.

	Problem Constraints
		2 <= |A| <= 100000
		1 <= A[i] <= 109
	 */

	public static void main(String[] args) {
		int[] A = {186,256,102,377,186,377};
		int[] ans=findTwoUniqueElements(A);//102 256
		System.out.println(Arrays.toString(ans));
		int[] ans1=anotherMEthod(ans);
		System.out.println(Arrays.toString(ans1));

	}
	/*
	The code finds the two unique elements in the input array using bitwise XOR operations. 
	It first finds the XOR of all elements to get the XOR of the two unique elements. 
	Then, it identifies the rightmost set bit in the XOR result, which helps segregate the two unique elements. 
	Finally, it calculates the XOR of elements with the set and unset bit at the identified position to find the two unique elements.

	Time Complexity: 
			O(n), where n is the number of elements in the input array arr. 
			The code loops through the array twice, which is still linear time complexity.

	Space Complexity: O(1). 
			The code uses a constant amount of extra space to store variables, and the size of the ans array is fixed at 2.
	 */
	public static int[] findTwoUniqueElements(int[] arr) {
		//Initialize a variable to store the XOR of all elements in the input array. XOR of an element with itself is 0, and XOR of any number with 0 is the number itself.
		int xorOfTwoNumbers=0;
		//Loop through the input array to calculate the XOR of all elements.
		for(int i=0;i<arr.length;i++) {
			//xorOfTwoNumbers = (xorOfTwoNumbers ^ arr[i]);: XOR each element of the array with the previous XOR result.
			xorOfTwoNumbers=(xorOfTwoNumbers^arr[i]);
		}
		//Initialize a variable to store the position of the rightmost set bit (1) in the xorOfTwoNumbers.
		int setBitPosition=0;
		//Start a loop from the 30th bit to the 0th bit (31 bits in total, assuming integers are 32-bit in Java).
		for(int i=30;i>=0;i--) {
			//Check if the bit at position i in xorOfTwoNumbers is set (1).
			if(checkBit(xorOfTwoNumbers,i)==1) {
				// If the bit is set, store the position and break the loop.
				setBitPosition=i;
				break;
			}
		}
		//Initialize two variables to store XOR results for elements with the set bit and unset bit at the setBitPosition.
		int setBitXor=0;
		int UnSetBitXor=0;
		for(int i=0;i<arr.length;i++) {
			//Check if the bit at position setBitPosition in the current element is set (1).
			if(checkBit(arr[i],setBitPosition)==1) {
				//If the bit is set, XOR the element with setBitXor.
				setBitXor=(setBitXor^arr[i]);
			}
			//If the bit is not set, XOR the element with UnSetBitXor.
			else {
				UnSetBitXor=(UnSetBitXor^arr[i]);
			}
		}
		//Initialize an array to store the two unique elements.
		int[] ans=new int[2];
		//we had to store the elements in ascending order so we are considering the the extra steps.
		if(setBitXor-UnSetBitXor!=0) {
			int x = Math.min(setBitXor, UnSetBitXor), y = Math.max(setBitXor, UnSetBitXor);
	        ans[0] = x;
	        ans[1] = y;
		}
		else {
			ans[0]=setBitXor;ans[1]=UnSetBitXor;
		}
		return ans;
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
		
		
		
	//another method
		public static int[] anotherMEthod(int[] A) {
	        int aXorb = 0; 
	        for (int item: A)
	            aXorb ^= item;
	        int lastBit = (aXorb & (aXorb - 1)) ^ aXorb; 
	        int intA = 0, intB = 0;
	        for (int item: A) {
	            
	            if ((item & lastBit) != 0)
	                intA = intA ^ item;
	            else
	                intB = intB ^ item;
	        }
	        int x = Math.min(intA, intB), y = Math.max(intA, intB);
	        int[] ans = new int[2];
	        ans[0] = x;
	        ans[1] = y;
	        return ans;
	    }

}
