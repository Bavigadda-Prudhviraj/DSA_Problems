package com.prudhvi.binarysearch;

public class FindPeakElement {
	/*
	 Given an array of integers A, find and return the peak element in it.
	An array element is considered a peak if it is not smaller than its neighbors. For corner elements, we need to consider only one neighbor.
	*we can return any element which is greater than or equal to both neighbor elements.
	NOTE:
		It is guaranteed that the array contains only a single peak element.
		Users are expected to solve this in O(log(N)) time. The array may contain duplicate elements.
		
	Hint:
		Observation: 
			Given the condition that there is only a single peak element, We can observe that the array can be one of 3 types:
			1) The whole array is decreasing with at most one pair of equal adjacent elements.
			2) The whole array is non-increasing with at most one pair of equal adjacent elements.
			3) The array is increasing first, then decreasing.
		Note that if there are three or more adjacent elements equal to each other, then there can be more than one peak element. Hence, no three adjacent elements in the array are pairwise equal.
		Also, there can be at most one pair of adjacent equal elements (i, i+1), and if they exist, one of these elements must be a peak element.
		You need to find the index of the peak element.
		
	Let us apply binary search on the index. Note that this is a classic binary search.
		ALGORITHM:
			1) Initially let l = 0 and high = arr.size()-1
			2) Repeat steps 3-4 while l<=r
			3) Set mid=low+((high-low)/2)
			4) If A[m] >= A[m-1] and A[m] >= A[m+1], A[m] is the peak element. Set answer = A[m] and exit the loop or return the element there only
				Else if array[m] > array[mid-1] we know that the peak element has to be on the right side of array[mid]. Hence, we set low =mid+1
				Else if array[m] < array[mid-1] we know that the peak element has to be on the left side of array[mid]. Hence, we set high=mid-1.
			5) Return answer
			Take extra care of edge cases, where the first or last element is the peak element.
			Look for its implementation. There are multiple ways to do this.
			Remember that the index starts from 0.
		
		
		
	 */

	public static void main(String[] args) {
		int[] arr= {0,1,2,4,2,5,2,1,0};
		int ans=binarySearch(arr);
		System.out.println(ans);

	}
	//we can return any peak element not an particular element
	//in question  peak should not less than adjacent element so, it can be (adjacent element== present== adjacent element) is also peak
	public static int binarySearch(int[] arr) {
		int n=arr.length-1;
		//here in case array have only one element we can return that element as peak element
		if(arr.length==1){
            return arr[0];
        }
		//if first element is grater than or equal to second element then it is also answer
        if(arr[0]>=arr[1]){
            return arr[0];
        }
       //if last element is grater than or equal to last second element then it is also answer
        if(arr[n]>=arr[n-1]){
            return arr[n];
        }
        int low=0;
        int high=n;
        int mid=low+((high-low)/2);
        int ans=0;
        while(low<=high){
        	//if both neighbors elements are equal to mid element,then it is also answer return than element [0,8,8,8,0]
            if(arr[mid-1]<=arr[mid] && arr[mid]>=arr[mid+1]){
            	//make that element as answer and break we found answer
            	ans=arr[mid];
                break;
            }
            //present element is greater then mid element and mid is less than mid element [5,5,4] [mid-1,mid,mid+1]
            else if(arr[mid]>arr[mid+1] && arr[mid]<arr[mid-1]){
            	//we are moving left side because
            	//if we go with right side we will get decreasing values only we find minimum value not peak values.
                high =mid-1;
                mid=low+((high-low)/2); 
            }
          //present element is greater then mid element and mid is less than mid element [3,4,4] [mid-1,mid,mid+1]
            else {//if(array[mid+1]>array[mid]) we can add this condition even not mention this on also will work
            	//we are moving right side because
            	//if we go with left side we will get decreasing values only we find minimum value not peak values.
                low=mid+1;
                mid=low+((high-low)/2);
            }
        }
        //Finally return answer
        return ans;
	}

	
	
	
}
