package com.prudhvi.hashing;

import java.util.HashMap;

public class ShaggyAndDistances {
	/*
	Shaggy has an array A consisting of N elements. We call a pair of distinct indices in that array a special if elements at those indices in the array are equal.
	Shaggy wants you to find a special pair such that the distance between that pair is minimum. Distance between two indices is defined as |i-j|. If there is no special pair in the array, then return -1.

	Problem Constraints
		1 <= |A| <= 105
	 */

	public static void main(String[] args) {
		int[] A = {7, 1, 3, 4, 1, 7};
		int ans=sameElementsWithLessDistanceBetweenThem(A);
		System.out.println(ans);
	}
	//the sameElementsWithLessDistanceBetweenThem function takes an array of integers as input and finds the minimum distance between two occurrences of the same element in the array. 
	//If there are no duplicates, it returns -1. Otherwise, it returns the minimum distance found
	public static int sameElementsWithLessDistanceBetweenThem(int[] arr){
		if (arr.length == 1) {
            return -1;
        }
		//This variable answer is used to store the minimum distance found between two occurrences of the same element in the array array. 
		//It is initialized with the maximum possible value of an integer so that any valid distance found during the traversal will be less than this initial value.
		int ans=Integer.MAX_VALUE;
		//A HashMap is created to store elements of the array as keys and their indices as values. 
		//This data structure allows for efficient lookup of previously encountered elements and their positions.
		HashMap<Integer,Integer> hMap=new HashMap<>();
		for(int i=0;i<arr.length;i++) {
			
			// Checks if the current element arr[i] is already present in the HashMap. If it is, it means we have found another occurrence of the same element.
			if(hMap.containsKey(arr[i])) {
				//Calculates the absolute difference between the current index i and the previously stored index of the same element arr[i]. This difference represents the distance between two occurrences of the same element.
				int difference=Math.abs(hMap.get(arr[i])-i);
				//Updates the answer variable by taking the minimum between the current answer value and the calculated difference. This ensures that answer holds the smallest distance found between two occurrences of the same element in the array.
				ans=Math.min(ans, difference);
			}
			// If the current element is not present in the HashMap, it means it's the first occurrence of the element.
			else {
				//Adds the current element arr[i] to the HashMap with its index i as the value.
				//This way, we can remember the position of the first occurrence of each element.
				hMap.put(arr[i],i);
			}
		}
		//After the loop, we check if the answer value has been updated during the traversal. 
		//If answer still holds its initial value (Integer.MAX_VALUE), it means that there were no duplicate elements in the array. 
		//In this case, the function returns -1, indicating that no two elements are the same.
		if(ans==Integer.MAX_VALUE){
            return -1;
        }
		//If the answer value has been updated, it means that there were duplicate elements in the array, and their minimum distance is stored in answer. 
		//The function returns answer, which represents the minimum distance between two occurrences of the same element in the array
		return ans;
		
	}

}
