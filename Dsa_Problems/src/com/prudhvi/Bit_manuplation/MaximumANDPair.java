package com.prudhvi.Bit_manuplation;

public class MaximumANDPair {
	/*
	Problem Description
		Given an array A. For every pair of indices i and j (i != j), find the maximum A[i] & A[j].

	Problem Constraints
		1 <= len(A) <= 10^2
		1 <= A[i] <= 10^9

	 */

	public static void main(String[] args) {
		int[] arr= {53, 39, 88};
		int answer=maximumAndPair(arr);
		System.out.println(answer);

	}
	/*
	The maximumAndPair method finds the maximum bitwise AND value among pairs of elements in the given array arr. 
	It iterates over the bits of the numbers from the most significant bit (MSB) to the least significant bit (LSB) and checks if there are at least two elements in the array with the bit set. 
	It then updates the answer by setting the corresponding bit in the result and replaces the elements that do not have the bit set with zero. 
	The method returns the maximum bitwise AND value. 
	 
	
	Time Complexity:
			The time complexity of this code is O(32 * N), where N is the length of the input array arr. 
			The outer loop iterates 32 times (for each bit position), and the inner loop iterates N times (for each element in the array).

	Space Complexity:
			The space complexity of this code is O(1), 
			as it uses a constant amount of additional space to store the answer, setBitCount, and temporary variables within the loops.
	 */
	public static int maximumAndPair(int[] arr) {
		// which will store the maximum bitwise AND value.
		int answer=0;
		//iterates over each bit position from the MSB (bit 31) to the LSB (bit 0) using the outer loop.
		for(int j=31;j>=0;j--) {
			//For each bit position, it initializes the setBitCount variable to 0, 
			//which will count the number of elements in the array with the bit set at the current position.
			int setBitCount=0;
			// loop iterates over each element in the array. 
			for(int i=0;i<arr.length;i++) {
				//For each element, it calls the checkBit method to determine if the bit at the current position is set.
				if(checkBit(arr[i],j)) {
					//If it is, it increments the setBitCount variable.
					setBitCount++;
				}
			}
			//After counting the set bits at the current position, the method checks if setBitCount is greater than or equal to 2. 
			//If it is, it means there are at least two elements in the array with the bit set at the current position.
			if(setBitCount>=2) {
				// it updates the answer by setting the bit at the current position using the bitwise left shift operation (1 << j).
				answer=answer+(1<<j);
				//It then goes through the array again and replaces the elements that do not have the bit set at the current position with zero.
				for(int i=0;i<arr.length;i++) {
					if(!checkBit(arr[i],j)) {
						arr[i]=0;
					}
				}
			}
		}
		//After processing all the bit positions, the method returns the answer, which represents the maximum bitwise AND value among pairs of elements in the array.
		return answer;
	}
	
	public static boolean checkBit(int num,int bit){	
		int maskBit=(1<<bit);
		if((maskBit&num)==0) {
			return false;
		}
		return true;
		
	}

}
