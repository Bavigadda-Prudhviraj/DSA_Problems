package com.prudhvi.binarysearch;
interface MountainArray {
	      public int get(int index);
	      public int length();
	  }
/*1095. Find in Mountain Array
	(This problem is an interactive problem.)
	You may recall that an array arr is a mountain array if and only if:
	arr.length >= 3
	There exists some i with 0 < i < arr.length - 1 such that:
		1.arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
		2.arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
	Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
	You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
		1.MountainArray.get(k) returns the element of the array at index k (0-indexed).
		2.MountainArray.length() returns the length of the array.
	Note: Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 */
public class FindMountainArray {
	public int findInMountainArray(int target, MountainArray mountainArr) {
	        int peakIndex = findPeakIndex(mountainArr);
	        int left = binarySearchLeft(0, peakIndex, mountainArr, target);
	        if (left != -1) {
	            return left;
	        }
	        int right = binarySearchRight(peakIndex, mountainArr.length() - 1, mountainArr, target);
	        return right;
	        
	    }
	public int findPeakIndex(MountainArray arr) {
		int left = 0;
	    int right = arr.length() - 1;
	    while (left < right) {
	        int mid = left + (right - left) / 2;
	        if (arr.get(mid) < arr.get(mid + 1)) {
	            left = mid + 1;
	        } else {
	            right = mid;
	        }
	    }
	    return left;
	}
	
	public int binarySearchLeft(int left, int right, MountainArray arr, int target) {
	    while (left <= right) {
	        int mid = left + (right - left) / 2;
	        int midValue = arr.get(mid);
	        if (midValue == target) {
	            return mid;
	        } else if (midValue > target) {
	            right = mid - 1;
	        } else {
	            left = mid + 1;
	        }
	    }
	    return -1;
	}
	
	public int binarySearchRight(int left, int right, MountainArray arr, int target) {
	    while (left <= right) {
	        int mid = left + (right - left) / 2;
	        int midValue = arr.get(mid);
	        if (midValue == target) {
	            return mid;
	        } else if (midValue > target) {
	            left = mid + 1;
	        } else {
	            right = mid - 1;
	        }
	    }
	    return -1;
	}
}
/*
This code is an implementation of a search algorithm to find a target value in a "Mountain Array." A Mountain Array is an array that has a peak element, where the elements first increase and then decrease. The code performs a binary search on this special type of array to find the target value.

Let's break down the code and its functions:

1. `findInMountainArray(int target, MountainArray mountainArr)`:
   - This is the main function that searches for the `target` value in the Mountain Array.
   - It first finds the index of the peak element using the `findPeakIndex` function.
   - Then, it performs two binary searches: one on the left side of the peak and another on the right side, using `binarySearchLeft` and `binarySearchRight`, respectively.
   - If the value is found on the left side, it returns the index; otherwise, it returns the index from the right side.

2. `findPeakIndex(MountainArray arr)`:
   - This function finds the index of the peak element in the Mountain Array.
   - It uses a binary search approach to find the peak.
   - It initializes `left` and `right` pointers and repeatedly narrows down the search until it finds the peak.
   - It compares the element at the `mid` point with the next element. If the current element is smaller, the peak must be on the right, so it adjusts `left`. Otherwise, it adjusts `right`.
   - The loop continues until `left` and `right` converge, at which point it returns the `left` index as the peak.

3. `binarySearchLeft(int left, int right, MountainArray arr, int target)`:
   - This function performs a binary search on the left side of the peak to find the `target`.
   - It operates similarly to a standard binary search but adapts based on comparisons between the target and the middle element.
   - If the middle element equals the target, it returns the middle index.
   - If the middle element is greater than the target, it adjusts `right`.
   - If the middle element is less than the target, it adjusts `left`.
   - The function continues until `left` is greater than `right`, indicating that the target was not found.

4. `binarySearchRight(int left, int right, MountainArray arr, int target)`:
   - This function performs a binary search on the right side of the peak to find the `target`.
   - Similar to `binarySearchLeft`, it uses a binary search approach but adapts based on comparisons.
   - If the middle element equals the target, it returns the middle index.
   - If the middle element is greater than the target, it adjusts `left`.
   - If the middle element is less than the target, it adjusts `right`.
   - The function continues until `left` is greater than `right`, indicating that the target was not found.

In summary, the code leverages the properties of a Mountain Array and performs binary searches on both sides of the peak to efficiently locate the target value. The time complexity for this code is O(log n), where n is the size of the Mountain Array.
 */

