package com.prudhvi.Arrays.gcd_hcf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
	Given an array of integers A of size N containing GCD of every possible pair of elements of another array.

	Find and return the original numbers used to calculate the GCD array in any order. 
	For example, if original numbers are {2, 8, 10} then the given array will be {2, 2, 2, 2, 8, 2, 2, 2, 10}.
 */
public class GCD {

	public static void main(String[] args) {
		int[] arr= {2, 2, 2, 2, 8, 2, 2, 2, 10};
		int[] allGCDPair=AllGcdPair(arr);
		System.out.println(Arrays.toString(allGCDPair));

	}
	/*
	**Time Complexity:**
			- Sorting the array `A` takes O(n * log(n)) time.
			- Counting the occurrences of elements in the array takes O(n) time.
			- The outer loop iterates over the sorted array once, taking O(n) time.
			- The inner loop iterates over the `ans` list, which can have at most `n` elements, and calculates GCD values. This also takes O(n) time in the worst case.
			- Copying elements from the `ans` list to the `ansArray` takes O(n) time.
		The overall time complexity is O(n * log(n)) due to the sorting, which dominates the other operations.
		
	**Space Complexity:**
			- Additional space is used for the `ans` list, `map`, and the `ansArray`.
			- The `ans` list can have at most `n` elements.
			- The `map` can also have at most `n` unique elements.
			- The `ansArray` has a space complexity of O(n).
		The overall space complexity is O(n) due to these data structures.
	 */
	private static int[] AllGcdPair(int[] A) {
		int n = A.length - 1;//`n` is set to the length of the array `A` minus 1.
		Arrays.sort(A);//Sort the array A in ascending order.
		ArrayList<Integer> ans = new ArrayList<>();//Create an `ArrayList<Integer>` called `ans` to store the final result.
		HashMap<Integer, Integer> map = new HashMap<>();//Create a HashMap<Integer, Integer> called map to count the occurrences of each number in the array A.
		//Iterate through the elements in the sorted array A:
		for (int val : A) {
			//Update the count of each unique value in the map using map.put(val, map.getOrDefault(val, 0) + 1).
			map.put(val, map.getOrDefault(val, 0) + 1);
		}
		//Initially, add the largest element (last element in the sorted array) to the ans list.
		ans.add(A[n]);
		//Decrement the count of the largest element in the map by 1.
		map.put(A[n], map.get(A[n]) - 1);
		int i = n - 1;
		//Iterate from the second largest element to the smallest element in the sorted array:
		while (i > -1) {
			//Check if the count of the current element in the map is greater than 0.
			if (map.get(A[i]) > 0) {
				for (Integer ansVal : ans) {
					//For each element in the ans list, calculate the GCD of the current element and the elements in the ans list.
					int gcdVal = gcd(A[i], ansVal);
					//Update the count of the calculated GCD value in the map by subtracting 2 (since both elements are used).
					map.put(gcdVal, map.get(gcdVal) - 2);
				}
				//Add the current element to the ans list.
				ans.add(A[i]);
				//Decrement the count of the current element in the map by 1.
				map.put(A[i], map.get(A[i]) - 1);
			}
			//Repeat this process for all elements from the second largest to the smallest.
			i--;
		}
		//Create an integer array `ansArray` to store the results.
		int ansArray[] = new int[ans.size()];
		//Copy the elements from the `ans` list to the `ansArray`.
		for (int j = 0; j < ans.size(); j++) {
			ansArray[j] = ans.get(j);
		}
		//Return `ansArray`, which contains all the numbers forming pairs with the highest GCD.
		return ansArray;
	}

	static int gcd(int X, int Y) {
		if (X == 0) {
			return Y;
			}
		return gcd(Y % X, X);
	}
	//Another way
	public int[] solve(int[] A) {

        int orignalN =(int) Math.sqrt(A.length);//size of original array
        int[] ans=new int[orignalN]; // original array to return
        int max=0;
        int j =0;
        for(int i =0; i<orignalN;i++){
            for( ;j<(i+1)*orignalN;j++){
                max=Math.max(max,A[j]);
            }
           
            ans[i]=max;
            max=0;
        }
        return ans;
    }
		

	

}
// Logic
// Create Frequency Map i.e. Map with key as number and value as frequency of number in array.
// GCD of highest number with itself would highest number only so just add it in ans ArrayList
// Traverse every element and GCD'ed with ans element and reduce count of GCD by two
// As (8,10) and(10,8) would be contributing two 2's in GCD array
// Note every element with frequency > 1 would in ans list
// as GCD'd of element with itself would be that number only. So Add it in ans list and reduce it's count by 1
