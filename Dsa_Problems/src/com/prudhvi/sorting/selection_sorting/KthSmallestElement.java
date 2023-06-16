package com.prudhvi.sorting.selection_sorting;
/*
	Find the Bth smallest element in given array A .
	NOTE: Users should try to solve it in less than equal to B swaps.
		Hint 1:-You want to find the Bth element in the array.
			Selection sort is the best algorithm to use here to do it in less than equal B swaps.
		Hint 2:The time complexity of selection sort is O(N^2) in the usual case.
			Try to modify the algorithm for this problem such that the complexity is reduced to O(B * N).
		Approach:The algorithm of selection sort should be used.
				 The selection sort algorithm sorts an array by repeatedly
				 finding the minimum element (considering ascending order)
				 from unsorted part and putting it at the beginning.
				 You need to get the minimum element to the beginning of the array
				 only B times as you require the Bth element.
*/
public class KthSmallestElement {
	public static void main(String[] args) {
		int[] A= {8,16,80,55,32,8,38,40,65,18,15,45,50,38,54,52,23,74,81,42,28,16,66,35,91,36,44,9,85,58,59,49,75,20,87,60,17,11,39,62,20,17,46,26,81,92};
		int kthElement=9;
		int[] ans=new int[A.length];
		for(int i=0;i<A.length;i++){
			ans[i]=A[i];
		}
		int[] sort=SelectionSorting(ans,kthElement);
		System.out.println(sort[kthElement-1]);
	}
	public static int[] SelectionSorting(int[] arr,int b){
        for(int i=0;i<b;i++){
            int minValues=arr[i];
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<minValues){
                    minValues=arr[j];
                    minIndex=j; 
                }
            }
            int temp=arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;
        }
        return arr;
    }

}
