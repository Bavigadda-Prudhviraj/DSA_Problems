package com.prudhvi.binarysearch;

public class SingleElementinSortedArray {
	/*
	 Given a sorted array of integers A where every element appears twice except for one element which appears once, find and return this single element that appears only once.
	Elements which are appearing twice are adjacent to each other.
	NOTE: Users are expected to solve this in O(log(N)) time.
	
	Hint1:
		One simple solution is take XOR of all elements in the array and you will get the unique element.
		But, the time complexity of this solution is O(N) and we are not using the fact that duplicates are adjacent to each other.

		Take an example - [2, 2, 7, 7, 5, 5, 8, 9, 9, 11, 11]
		And observe the indices of first occurrence of every element. Did you observe something?

		For all the elements, which are present before the unique element, their first occurrence is present at even index.
		For all the elements, which are present after the unique element, their first occurrence is present at odd index.
		So, on the basis of this information, can we think of applying binary search?
	
	Approach:
		Lets say the position of the element occurring once is x.
		What property do you observe for the elements which are towards the left of x?

		For any i from [0,x) ,we can say that
			if i is even A[i]==A[i+1]
    		if i is odd  A[i]==A[i-1]
		This cannot be said for elements from [x+1,n). Because in that case if i is even A[i]==A[i-1] and vice versa.

		Therefore we just have to find the last index ‘j’ such that it satisfies the property of index from [0,x).
		If we get j , then A[j+1] would be our answer.
	 */

	public static void main(String[] args) {
		int[] arr= {2, 3, 3};
		 int ans=searchSingleElement(arr);
		 System.out.println(ans);

	}
	public static int searchSingleElement(int[] arr) {
		 if(arr.length==1){
	            return arr[0];
	        }
	        if(arr[arr.length-1]!=arr[arr.length-2]){
	            return arr[arr.length-1];
	        }
	        if(arr[0]!=arr[1]){
	            return arr[0];
	        }
	        int low=0;
	        int high=arr.length-1;
	        int ans=-1;
	        while(low<=high){
	            int mid=low+((high-low)/2);
	            if(arr[mid]!=arr[mid+1] && arr[mid]!=arr[mid-1]){
	                ans=arr[mid];
	                break;
	            }
	            if(arr[mid]==arr[mid-1]){
	                 mid=mid-1;
	            }
	            if(mid%2==0){
	                low=mid+2;
	             }
	            else{
	                 high=mid-1;
	            }
	        }
	        return ans;
		
	}
	//Optimized way
	public static int solve(int[] A) {
        int ans = 0, n = A.length;
        int low = 0, high = n-1;
        while(low <= high){
            int mid = (high - low)/2 + low;
            if(mid == n - 1) 
                return A[n-1];
            if(A[mid] == A[mid+1]) 
                mid++;
            // if all elements with index < mid are occurring twice then mid should be odd
            if(mid%2 == 1)
                low = mid + 1;
            else{
                ans = mid;
                high = mid - 1;
            }
        }
        return A[ans];
    }

}
