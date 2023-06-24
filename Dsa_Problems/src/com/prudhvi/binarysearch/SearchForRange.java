package com.prudhvi.binarysearch;


import java.util.Arrays;

public class SearchForRange {
	/*
	 Given a sorted array of integers A (0-indexed) of size N, find the left most and the right most index of a given integer B in the array A.
	Return an array of size 2, such that 
          First element = Left most index of B in A
          Second element = Right most index of B in A.
	If B is not found in A, return [-1, -1].
	Note : Your algorithm's runtime complexity must be in the order of O(log n).
	
	Hint:
		Finding any element in the sorted array can be best done by binary search where we follow the three steps:
			1.Define the search space - In this question our complete array is the search space i.e. from index 0 to N-1.
			2.Check if middle element is the answer (let say left most index) - Think how to check when you find left most index of the given element.
			3.Decide whether to go in the left half or right half - Based on the value of middle element and given target try to decide this.
		Once you find the left most index then repeat the three steps to find the right most index of the given element. If the left most index is not found that means B is not present in the array, hence return [-1, -1].
	
	Approach:
		First, let’s try to find the leftmost index of the given integer B using Binary Search.Since the search space is from 0 to N-1, initialise a variable Left = 0 and Right = N-1.
		Find the middle index i.e. middle index =low+(high-low)/2

		How to check if middle index element is the answer?
			1.The middle index element is the answer if these two conditions are satisfied -
			2.The middle index element should be equal to the given integer B.
			3.The middle index should be the first element of the array i.e. middle index = 0 OR element on left of middle index i.e. A[middle_index - 1] should be less than B i.e. A[middle_index - 1] < B.

		How to decide whether to go in the left half or right half?
			1.If the middle index element is less than B then we should increase the number, hence go on right side i.e. Left = middle_index + 1
			2.If the middle index element is greater than B then we should decrease the number, hence go on left side i.e. Right = middle_index - 1
			3.If the middle index element is equal to B then we should find it’s first occurrence, hence go on left side i.e. Right = middle_index - 1
		Similarly, let’s try to find the rightmost index of the given integer B using Binary Search. (Try it!)
	 */
	public static void main(String[] args) {
		int[] arr= {1,1,2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 8, 9, 9, 10};
		int target=10;
		int[] ans=searchRange(arr,target);
		System.out.println(Arrays.toString(ans));
	}
	public static int[] searchRange(final int[] arr, int target) {
        if(arr.length==1 && arr[0]==target) {
            int[] ans={0,0};
            return ans;
		}
        else if(arr.length==1 && arr[0]!=target) {
            int[] ans={-1,-1};
            return ans;
		}
        //here we are searching for first Index
        int firstIndex=startIndex(arr,target);
        int[] ans=new int[2];
        ans[0]=firstIndex;
        //here we are searching for second Index
        int secondIndex=endIndex(arr,target); 
        ans[1]=secondIndex;
        if(firstIndex==-1 && secondIndex==-1){
            int[] ans1={-1,-1};
            return ans1;
        }
		return ans;
    }
	public static int startIndex(final int[] arr, int target){
        int ans = -1;
        int n = arr.length;
        int low =0;
        int high = n-1;
        while(low<=high){
            int mid = low+(high-low)/2;
            if(arr[mid] == target){
            	//make this index as answer
                ans = mid;
                //why high=m-1?
                //because we are searching for starting index there is chance that it can be present left side to present element [6,6][mid-1,mid]
                high = mid -1;     
            }
            else if(arr[mid]<target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
    return ans;
    }

    public static int endIndex(final int[] arr, int target){
        int ans = -1;
        int n = arr.length;
        int low =0;
        int high = n-1;
        while(low<=high){
            int mid = low+(high-low)/2;
            if(arr[mid] == target){
                ans = mid;
              //why low=m-1?
                //because we are searching for end index there is chance that it can be present right side to present element [6,6][mid,mid+1]
                low = mid +1;
            }
            else if(arr[mid]<target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
    return ans;
    }
}
