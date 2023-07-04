package com.prudhvi.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SortArrayInGivenOrder {
	/*
	Given two arrays of integers A and B, Sort A in such a way that the relative order among the elements will be the same as those are in B.
	For the elements not present in B, append them at last in sorted order.
	Return the array A after sorting from the above method.
	NOTE: Elements of B are unique.
	
	Problem Constraints
		1 <= length of the array A <= 100000
		1 <= length of the array B <= 100000
		-10^9 <= A[i] <= 10^9
	 */

	public static void main(String[] args) {
		int[] A = {20,14 ,8,18,6}; //15,5,10,6,14
		int[] B = {1,16,7,6,17,3,13,8,19,20};//     8,16,6,2,13,1,12,3,14
		//6 8 20 14 18 //6 14 5 10 15
		int[] ans=sortElementsBasedonBArray(A,B);
		System.out.println(Arrays.toString(ans));

	}
	/*
	he function takes two arrays (arr1 and arr2), sorts arr1, and then creates a new array (ans) by arranging the elements of arr1 as per their appearance in arr2. 
	Elements in arr1 that are not present in arr2 are appended at the end of the ans array. 
	The function returns the sorted array (ans).
	 */
	private static int[] sortElementsBasedonBArray(int[] arr1, int[] arr2) {
		//hashMap to store the frequency of elements in arr1.
		HashMap<Integer, Integer> hashMap=new HashMap<>();
		//The first loop (for loop) iterates through arr1, and for each element, it checks if it is already present in the hashMap.
        for(int i=0;i<arr1.length;i++) {
        	//If the element is not present in the hashMap, it adds the element as a key with a value of 1, representing its frequency.
            if(!hashMap.containsKey(arr1[i])) {
                hashMap.put(arr1[i],1);
            }
            //If the element is already present in the hashMap, it increments its frequency.
            else {
                int frequency=hashMap.get(arr1[i]);
                hashMap.put(arr1[i],frequency+1);
            }
        }
        //The Arrays.sort(arr1) statement sorts the arr1 array in ascending order. 
        //This is done to ensure that the elements from arr1 are added to the arrayList in ascending order.
        Arrays.sort(arr1);
        //A new ArrayList named arrayList is initialized to store the sorted elements.
        ArrayList<Integer> arrayList=new ArrayList<>();
        //The second loop (for loop) iterates through the elements of arr2.
        for(int i=0;i<arr2.length;i++) {
        	//frequencyCount is used to store the frequency of the current element in arr2 found in the hashMap
            int frequencyCount=0;
            //If the current element from arr2 is present in the hashMap, 
            //it means it also exists in arr1, and we need to add it to the arrayList as per its frequency.
            if(hashMap.containsKey(arr2[i])) {
                frequencyCount=hashMap.get(arr2[i]);
                //The while loop adds the current element arr2[i] to the arrayList repeatedly based on its frequency until its frequency becomes zero.
                while (frequencyCount>0) {
                    arrayList.add(arr2[i]);
                    frequencyCount--;
                }
                //After adding the element to the arrayList, the element is removed from the hashMap to avoid duplicates.
                hashMap.remove(arr2[i]);
                
            }
        }
        //The third loop (for loop) iterates through the elements of arr1
        for(int i=0;i<arr1.length;i++) {
        	//For each element of arr1, if it is still present in the hashMap, 
        	//it means it was not included in the arrayList earlier (elements that are not present in arr2).
            if(hashMap.containsKey(arr1[i])) {
            	//So, the remaining elements of arr1 that are not present in arr2 are added to the arrayList.
                arrayList.add(arr1[i]);
            }
        }
        //After adding all the elements to the arrayList in the required order, the ans array is created with the same size as the arrayList.
        int[] ans=new int[arrayList.size()];
        //The elements are copied from the arrayList to the ans array.
        for(int i=0;i<arrayList.size();i++) {
            ans[i]=arrayList.get(i);
        }
        //he sorted ans array is returned.
        return ans;
	}

}
