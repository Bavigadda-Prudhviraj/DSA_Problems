package com.prudhvi.binarysearch;



public class MatrixSearch {
	/*
	 Given a matrix of integers A of size N x M and an integer B. Write an efficient algorithm that searches for integer B in matrix A.
	This matrix A has the following properties:
		1.Integers in each row are sorted from left to right.
		2.The first integer of each row is greater than or equal to the last integer of the previous row.
		Return 1 if B is present in A, else return 0.
	NOTE: Rows are numbered from top to bottom, and columns are from left to right.
	
	Hint 1:
			If you write down the numbers of row 1 followed by numbers in row2, row3, and so on.
			Do you think the Flattern resultant array would be sorted?  
			If yes, how do you search for a number efficiently in a sorted array?
	Hint 2:
			Assume the matrix as a Flattened sorted array.
			Apply Binary search on the flattened array.
			Translate the middle index to the corresponding matrix row and column.
			Use the formulas: row = int(mid / n) and col = int(mid % n).
			Compare the middle element with the target number.
			Update the search space based on the comparison.
			Repeat until the target is found or search space is exhausted.
			Return 1 if the target is found, otherwise return 0.
			
	Approach:
			Consider the matrix as a flattened array by concatenating the rows. This array will have m*n elements, where m is the number of rows and n is the number of columns.
			Since the matrix is sorted, we can apply a binary search algorithm on this flattened array to efficiently search for the target number.
				1.Initialize the search space by setting the low index as 0 and the high index as m*n - 1.
				2.Enter a loop that continues until the low index is no longer less than or equal to the high index.
				3.Calculate the middle index as the average of the low and high indices.
				4.Translate the middle index into the corresponding row and column in the matrix.
				5.Use the formulas: row = int(mid / n) and col = int(mid % n).
				6.Compare the element at the middle index in the flattened array with the target number.
				7.If the middle element is equal to the target number, return 1 to indicate that the target is found.
				8.If the middle element is greater than the target number, update the high index to mid - 1. This narrows down the search space to the lower half.
				9.If the middle element is lesser than the target number, update the low index to mid + 1. This narrows down the search space to the upper half.
				10.Repeat until either the target number is found or the search space is exhausted.
			If the search terminates without finding the target number, return 0 to indicate that the target is not present in the matrix
	 */

	public static void main(String[] args) {
		int[][] arr= {
		             {1,   3,  5,  7},
		             {10, 11, 16, 20},
		             {23, 30, 34, 50}};
		int target=3;
		int ans=0;
		//iterating over the two D array
        for(int i=0;i<arr.length;i++){
        	//here we are passing single row as 1d array to binary search function
        	int ele=TwoDBinaraySearch(arr[i] ,target);
        	if(ele!=-1) {
        		ans=ele;
        		break;
        	}
       }
       System.out.println(ans); 

	}
	// go through notes
	 public static int TwoDBinaraySearch(int[] arr,int target){
	        int ans=-1;
	        int low=0;
	        int high=arr.length-1;
	        while (low<=high){
	        	int mid=low+((high-low)/2);
	        	if(arr[mid]==target) {
	        		ans=target;
	        		break;
	        	}
	        	else if(arr[mid]<target) {
	        		low=mid+1;
	        	}
	        	else {
	        		high=mid-1;
	        	}
				
			}
	       
	        return ans;
	    }
	 //Optimized method
	 public static int searchMatrix(int[][] A, int B) {
	        int n = A.length, m = A[0].length;
	        //assume all elements are added to a list and after that it is sorted
	        //last index will be n * m - 1
	        int low = 0, high = n * m - 1, ans = -1;
	        while(low <= high){
	            int mid = (high - low) / 2 + low;
	            int row = mid / m, col = mid % m;
	            if(A[row][col] > B) 
	                high = mid - 1;
	            else{
	                ans = mid;
	                low = mid + 1;
	            }
	        }
	        if(ans == -1 || A[ans / m][ans % m] != B) 
	            return 0;
	        return 1;
	    }

}
