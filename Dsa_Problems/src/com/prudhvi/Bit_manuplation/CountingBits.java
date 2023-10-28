package com.prudhvi.Bit_manuplation;

import java.util.Arrays;

public class CountingBits {

	public static void main(String[] args) {
		int num=5;
		int[] bitsArr=countBits(num);
		System.out.println(Arrays.toString(bitsArr));

	}
	/*
	**Time Complexity:**
		1. In the `countBits` function, there's a loop that iterates from `0` to `n`. Within this loop, there's a call to the `countSetBits` function for each integer from `0` to `n`.
		2. In the `countSetBits` function, there's a loop that iterates from `31` down to `0`. This loop iterates 32 times (assuming a 32-bit integer representation).
			The time complexity of the code can be analyzed as follows:
				- The outer loop in `countBits` runs `n` times.
				- The inner loop in `countSetBits` runs 32 times (constant).
		So, the overall time complexity of the code is O(n) because the number of iterations is determined by the input `n`.

	 **Space Complexity:**
		The space complexity of the code is determined by the space used for the `bits` array in the `countBits` function. The size of this array is `n + 1`, so the space complexity is O(n).
		The `countSetBits` function does not use any additional space that grows with the input `n`, so it has a constant space complexity, O(1).
	In summary, the space complexity is O(n), and the time complexity is O(n) because it depends linearly on the input `n`.
	 */
	public static int[] countBits(int n) {
		//Create an integer array bits with a size of n + 1. This array will store the count of set bits for each number from 0 to n.
        int[] bits=new int[n+1];
        //Iterate from 0 to n using the variable i in a for loop
        for(int i=0;i<=n;i++){
        	//call the countSetBits(int num) function to count the set bits in the binary representation of the current integer i
            int setBitsCount=countSetBits(i);
            bits[i]=setBitsCount;//Assign this count to the bits[i] array element.
        }
        return bits;
    }
	private static int countSetBits(int num) {
		int setBits=0;//Initialize setBits to 0. This variable will keep track of the count of set bits.
		//Iterate from 31 down to 0 using the variable i in a for loop. 
		//This loop checks each bit of the integer from the most significant bit (MSB) to the least significant bit (LSB).
		for(int i=31;i>=0;i--) {
			//Calculate a bitmask for the i-th bit using int bitMask = (1 << i).
			int bitMask=(1<<i);
			//Check if the i-th bit of the integer num is set by performing a bitwise AND operation with the bitmask: (num & bitMask) >= 1. 
			//If the result is greater than or equal to 1, it means the i-th bit is set, so increment the setBits count.
			if((num&bitMask)>=1){
                setBits++;
            }
		}
		return setBits;
	}

}
