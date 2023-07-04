package com.prudhvi.two_pointers;

public class PairsWithGivenSumII {

	public static void main(String[] args) {
		int[] A = {1,2,6,6,7,9,9};
		int	  B = 13;
		int ans=searchPairSumB(A,B);
		System.out.println("answer: "+ans);
		

	}
	/*
	 the code finds all pairs of elements in the input array array whose sum equals the target. 
	 It takes into account duplicate elements separately while calculating the number of pairs. 
	 The code iterates through the array using two pointers p1 and p2, which move towards each other until they cross each other
	 
	 Time Complexity: 
	 		The time complexity of this code is O(n), where n is the length of the input array arr. 
	 		The while loop iterates at most n times, and each iteration performs constant time operations.
	 Space Complexity: 
	 		The space complexity of this code is O(1) as it uses a constant amount of extra space regardless of the input size. 
	 		The only additional space used is for variables like pairs, mod, p1, p2, sum, pairsCnt, p1Cnt, p2Cnt, p1Ele, and p2Ele, 
	 		which are all independent of the input size.
	 */
	public static int searchPairSumB(int[] arr,int target) {
		long pairs=0;
		int mod=1000000007;
		int p1=0;//p1 as a pointer starting from the beginning of the array. 
		int p2=arr.length-1;//p2 as a pointer starting from the end of the array.
		while (p1<p2){//while loop runs as long as p1 is less than p2.
			//calculates the sum of elements at indices p1 and p2 (arr[p1] + arr[p2]) and stores it in the variable sum
			long sum=arr[p1]+arr[p2];
			//If the sum is equal to target, it means we have found a pair whose sum matches the target. 
			//The code checks if the elements at p1 and p2 are the same or different.
			if(sum==target) {
				//If the elements at p1 and p2 are the same, it means there are duplicate elements present, and we have found all possible pairs with this value.
				if(arr[p1]==arr[p2]) {
					// stores the count of elements with the same value between the pointers p1 and p2.
					long pairsCnt=p2-p1+1;
					//calculates the number of pairs that can be formed with these duplicate elements using the combination formula C(n, 2) = n * (n - 1) / 2, where n is the number of duplicate elements.
					long nCr=(pairsCnt*(pairsCnt-1))/2;
					//The value of pairsCnt * (pairsCnt - 1) / 2 is added to the pairs, 
					pairs+=nCr;
					//and the loop is terminated using break since we have considered all the pairs with the same value.
					break;
				}
				//If the elements at p1 and p2 are different, it means they are distinct elements, and we need to count the pairs formed by them.
				else {
					//The code counts the occurrences of elements at p1 and p2 in the respective while loops, updating p1 and p2 accordingly.
					int p1Cnt=0;
					int p2Cnt=0;
					int p1Ele=arr[p1];
					int p2Ele=arr[p2];
					while (p1Ele==arr[p1]){
						p1Cnt++;
						p1++;	
					}
					while (p2Ele==arr[p2]){
						p2Cnt++;
						p2--;	
					}
					//The variable pairsCnt stores the number of pairs that can be formed using elements at p1 and p2, which is the product of their counts.
					long pairsCnt=(long)(p1Cnt*p2Cnt);
					//The value of pairsCnt is added to the pairs.
					pairs+=pairsCnt;
				}
				
			}
			//If the sum of elements at p1 and p2 is less than the target, the pointer p1 is incremented, moving towards larger values.
			else if(sum<target) {
				p1++;
			}
			//If the sum of elements at p1 and p2 is greater than the target, the pointer p2 is decremented, moving towards smaller values.
			else {
				p2--;
			}
			
		}
		//After the while loop, the code takes the modulo of pairs with mod to prevent integer overflow and returns it as the result.
		return (int)(pairs%mod);
	}

}
