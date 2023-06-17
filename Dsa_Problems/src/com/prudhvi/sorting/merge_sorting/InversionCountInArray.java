package com.prudhvi.sorting.merge_sorting;

public class InversionCountInArray {
	/*
	  	Given an array of integers A. If i < j and A[i] > A[j], then the pair (i, j) is called an inversion of A. Find the total number of inversions of A modulo (109 + 7)
	 	Return the number of inversions of A modulo (109 + 7).
	 	Input: A = [3, 4, 1, 2]
	 	output: 4
	 		Explanation:
	 			The pair (0, 2) is an inversion as 0 < 2 and A[0] > A[2]
				The pair (0, 3) is an inversion as 0 < 3 and A[0] > A[3]
				The pair (1, 2) is an inversion as 1 < 2 and A[1] > A[2]
				The pair (1, 3) is an inversion as 1 < 3 and A[1] > A[3]
	 	Hint: Can we use some sorting algorithm to find the number of inversions?
	 	Approach : Naive Approach Traverse through the array from start to end Find the count of elements smaller than the current number up to that index for every element using another loop. Sum up the count of inversion for every index. Print the count of inversions.

			Efficient Approach using Merge Sort
					Suppose we know the number of inversions in the left half and the right half of the array, lets call them inv_l and inv_r.
					Which inversions are not counted in inv_l+inv_r ? The elements in the left half which are greater than the elements in the right half. These are the inversions which are not counted.

					Lets assume that both left half and right half are sorted. Can we count the number of inversions now?
					We can just merge the two arrays and whenever we find that a[i] > a[j] (where i is the pointer of left half of the array and j is the pointer of the right half of the array) we simply increment the number of inversions.
					The final answer will be inv_l + inv_r + number of inversions found during merge step.

					Does this remind of a famous algorithm?
					Yes, thats right. It is Merge Sort with a slight modification.
	 
	 
	 */

	public static void main(String[] args) {
		int[] A = {3, 4, 1, 2};
		int ans=inversionCount(A,0,A.length-1);
		System.out.println(ans%1000000007);	
	}
	 private static int inversionCount(int[] a, int s, int e) {
			if(s==e) {
				return 0;
			}
			int mid=(s+e)/2;
			//gives count half elements inversion count with another half element
			int l=inversionCount(a,s,mid);
			//gives count half elements inversion count with another half element
			int r=inversionCount(a, mid+1, e);
			//before merging count the inversion count of elements
			int c=mergeSort(a,s,mid,e);
			return (l+r+c);
		}
	 public static int mergeSort(int[] arr,int start,int mid,int end) {
			int count=0;
			int[] ans=new int[end-start+1];
			int p1=start;
			int p2=mid+1;
			int i=0;
			while (p1<=mid && p2<=end) {
				if(arr[p1]<=arr[p2]) {
					ans[i]=arr[p1];
					i++;p1++;
				}
				else if(arr[p2]<arr[p1]) {
					// If arr[p2] > arr[p1], all elements from arr[p1] to arr[mid-1] are greater than arr[p2],
	                // so increment the inversion count by the number of remaining elements in the left sub array.
					count+=mid-p1+1;
					ans[i]=arr[p2];
					p2++;i++;
				}
			}
			// Copy any remaining elements from the left sub array
			while(p1<=mid) {
				ans[i]=arr[p1];
				i++;p1++;
				
			}
			// Copy any remaining elements from the right subarray
			while (p2<=end) {
				ans[i]=arr[p2];
				i++;p2++;
			}
			int index=0;
			// Copy the merged array back to the original array
			for(int k=start;k<=end;k++) {
				arr[k]=ans[index];
				index++;
			}
			return count%1000000007;
		}

}
