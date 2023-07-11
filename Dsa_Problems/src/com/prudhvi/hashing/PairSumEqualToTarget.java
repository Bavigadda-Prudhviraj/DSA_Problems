package com.prudhvi.hashing;

import java.util.HashMap;

public class PairSumEqualToTarget {
	/*
	Problem Description
			Given a sorted array of integers (not necessarily distinct) A and an integer B, 
			find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.
			Since the number of such pairs can be very large, return number of such pairs modulo (109 + 7).

	Problem Constraints
			1 <= |A| <= 100000
			1 <= A[i] <= 10^9
			1 <= B <= 10^9
	 */

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		int target=13;
		int ans= pairSum(arr,target);
		System.out.println(ans);

	}
	/*
 	The pairSum method takes an array arr and a target value target as inputs and returns the count of pairs whose sum is equal to the target.
	
	Time Complexity:
		The time complexity of this code is O(n), where n is the length of the input array. 
	
	Space Complexity:
		The space complexity is O(n) as well since the HashMap stores the frequencies of each element in the array.
	 */
	public static int pairSum(int[] arr,int target) {
		long countpair=0;//variable is initialized to keep track of the count of pairs.
		int mod=1000000007;//which is the modulo value used for the final result to prevent overflow.
		//why hash map Long reason is mentioned at the end 
		HashMap<Long, Long> hashMap=new HashMap<Long, Long>();
		//store the frequency of each element in the array.
		for(int i=0;i<arr.length;i++) {
			long ele=(long)arr[i];
			hashMap.put( ele,(Long)(hashMap.getOrDefault(ele,0L)+1) );
		}
		//The code checks if the target value is odd or even. Depending on that, it goes into the respective block
		//he loop iterates from 1 to target/2 (exclusive).
		if(target%2!=0) {
			for(int i=1;i<((long)target+1)/2;i++) {
				//For each iteration, it calculates the firstNumber and secondNumber based on the current iteration value.
				long firstNumber=target-i;
				Long secondNumber=target-firstNumber;
				//If firstNumber is equal to target/2, it means the pair consists of the same element. 
				if(firstNumber==target/2) {
					//The code checks if the firstNumber exists in the hashMap.
					if(hashMap.containsKey(firstNumber)) {
						//If it does, it calculates the combinations (nCr) using the frequency of that element and adds it to the countpair.
						long midValue=(long)hashMap.get(firstNumber);
						long nCr=(midValue*(midValue-1))/2;
						countpair=(countpair+nCr)%mod;
					}
				}
				//If firstNumber is not equal to target/2, it checks if both firstNumber and secondNumber exist in the hashMap. 
				//If they do, it calculates the product of their frequencies as the number of pairs and adds it to the countpair
				else if(hashMap.containsKey( firstNumber) && hashMap.containsKey(secondNumber)) {
					long pairs=(long)((hashMap.get(firstNumber)*hashMap.get(secondNumber))%mod);
					countpair+=pairs;
				}
			}
		}
		//In the case where the target value is even, the loop iterates from 1 to target/2 (inclusive).
		//The logic for counting pairs is similar to the odd case.
		else {
			for(int i=1;i<=((long)target+1)/2;i++) {
				long firstNumber=target-i;
				long secondNumber=target-firstNumber;
				if(firstNumber==target/2) {
					if(hashMap.containsKey(firstNumber)) {
						long midValue=(long)hashMap.get(firstNumber);
						long nCr=(midValue*(midValue-1))/2;
						countpair+=(nCr%mod);
					}
				}
				else if(hashMap.containsKey( firstNumber) && hashMap.containsKey(secondNumber)) {
					long pairs=(long)((hashMap.get(firstNumber)*hashMap.get(secondNumber))%mod);
					countpair+=pairs;
				}
			}
			
		}
		//The final result is obtained by taking the modulo of count pair with the mod value and converting it to an integer.
		return (int)(countpair%mod);
	}
	/*
	why hash map of long,long?
			The reason for using a HashMap<Long, Long> instead of a HashMap<Integer, Integer> in this code is to handle the situation where the elements in the input array arr may exceed the range of int data type.

			In the given code, the elements of the arr array are converted to long data type before storing them in the HashMap. 
			This is done to ensure that the code can handle larger values without potential integer overflow issues. 
			By using long data type for both the keys and values in the HashMap, the code can accommodate larger elements and frequencies.

			Using a HashMap<Long, Long> provides flexibility in handling larger inputs and prevents any potential errors that might arise due to integer overflow or loss of precision.

			However, if you are certain that the input array will always contain small integer values that can be handled by the int data type, 
			we can modify the code to use a HashMap<Integer, Integer> instead. This will provide a more memory-efficient implementation in that case.
	 */


}
