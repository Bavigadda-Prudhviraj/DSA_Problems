package com.prudhvi.maths.modular_arithmetic;

import java.util.HashMap;

public class PairSumDivisibleByTarget {
	/*
	Problem Description
		Given an array of integers A and an integer B, find and return the number of pairs in A whose sum is divisible by B.
		Since the answer may be large, return the answer modulo (109 + 7).
		Note: 
			Ensure to handle integer overflow when performing the calculations.

	Problem Constraints
		1 <= length of the array <= 100000
		1 <= A[i] <= 109
		1 <= B <= 106
	 */

	public static void main(String[] args) {
		int[] arr= {6,7,5,11,19,20,9,15,14,13,12,23};
		int target=6;
		int paiersCount=pairsCountpairSumDivisibleByTarget(arr,target);
		System.out.println(paiersCount);

	}
	/*
	Time Complexity:
			The time complexity of this code is O(n), where n is the length of the input array arr. 
			The code iterates over the array once to calculate the remainders and update the remainders HashMap. 
			HashMap operations, such as get, put, and getOrDefault, have an average time complexity of O(1).
			
	Space Complexity:
		The space complexity of the code is O(target), as it uses a HashMap (remainders) to store the frequencies of remainders. 
		The size of the HashMap can be at most target, depending on the number of distinct remainders in the array.
	 */

	private static int  pairsCountpairSumDivisibleByTarget(int[] arr, int target) {
		/*find  (  A  + B   )%m =0
			    ( A%m + B%m )%m =0, ranges [0,m-1],[0,m-1]
			    [  1  + m-1 ]%m =0 ranges 
			    [  2  + m-2 ]%m =0 ranges
			    [  3  + m-3 ]%m =0 ranges  
			    [  0  + 0   ]%m =0 ranges 	we will calculate nCr=(n*n-1)/2			
			    [ m/2 + m/2 ]%m =0 range this time also we will calculate the nCr=(n*n-1)/2 r=2 because we have to calculate 2 elements pairs
				*/
		//remainders to store the frequency of remainders when each element in the array is divided by the target.
		 HashMap<Integer, Integer> remainders=new HashMap<>();
		 //loop iterates over each element in the array and updates the frequency count of remainders in the remainders HashMap:
			for(int i=0;i<arr.length;i++) {
				int modWithEle=arr[i]%target;
				remainders.put(modWithEle,remainders.getOrDefault(modWithEle,0)+1);
			}
			//pairsCount to keep track of the count of pairs whose sum is divisible by the target.
			//[  0  + 0   ]%m =0 ranges 	we will calculate nCr=(n*n-1)/2		
		    long pairsCpunt=0;
		    //If the remainder 0 exists in the remainders HashMap, the code calculates the count of pairs with 0 remainder and adds it to pairsCount:
			if(remainders.containsKey(0)) {
				long zeroRemainderEle=remainders.get(0);
	            pairsCpunt+=((zeroRemainderEle*(zeroRemainderEle-1))/2);
			}
			//If the target is even and the remainder target/2 exists in the remainders HashMap, 
			//the code calculates the count of pairs with remainder target/2 and adds it to pairsCount.
			//[ m/2 + m/2 ]%m =0 range this time also we will calculate the nCr=(n*n-1)/2 r=2 because we have to calculate 2 elements pairs
			if(target%2==0){
				if(remainders.containsKey(target/2)) {
					long midRemainderEle=remainders.get(target/2);
		            pairsCpunt+=( (midRemainderEle*(midRemainderEle-1))/2);
					
				}
			}
			//loop iterates from 1 to (target + 1) / 2 - 1 to check for other remainders:
			for(int i=1;i<(target+1)/2;i++) {
				//the code checks if both the current remainder (anotherRemainder) and its complement (target - anotherRemainder) exist in the remainders HashMap. 
				//If they do, it calculates the count of pairs and adds it to pairsCount.
				int anotherRemainder=target-i;
				if(remainders.containsKey(anotherRemainder) && anotherRemainder!=target/2) {
					if(remainders.containsKey(target-anotherRemainder)) {
						pairsCpunt+=((long)remainders.get(target-anotherRemainder)*remainders.get(anotherRemainder));
					}
				}
			}
			// returns the pairsCount modulo 1000000007 as an integer.
			return (int)(pairsCpunt%1000000007);
	}
	//another way
	public static int solve(int[] a, int k) {
        int n = a.length;
        long mod = (long)(1e9 + 7);
        long cnt[] = new long[k];
        // cnt[i] stores the count of elements such that their modulo k equals i
        for(int x : a)    cnt[x % k]++;
        long ans = cnt[0] * (cnt[0] - 1) / 2;
        for(int i = 1, j = k - 1; i <= j; i++, j--) {
            if(i == j)    
                ans = (ans + cnt[i] * (cnt[i] - 1) / 2) % mod;
            else    
                ans = (ans + cnt[i] * cnt[j]) % mod;
        }
        return (int)ans;
    }

}
