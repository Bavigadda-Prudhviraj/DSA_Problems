package com.prudhvi.sorting.merge_sorting;

public class MinimumAbsoluteDifference {
	/*
	 	Given an array of integers A, find and return the minimum value of | A [ i ] - A [ j ] | where i != j and |x| denotes the absolute value of x
	 	Input=A = [5, 17, 100, 11];
	 	output=6.
	 	
	 	Hint:Think of sorting the array.
			 Can you find the minimum value in O(N) after sorting?
		Hint:Sort the array.
			 We can observe that the answer will be the minimum value of absolute difference between the adjacent elements.
			 We can iterate over the sorted array and maintain the minimum value of absolute difference.
			 Return the answer.
	 */

	public static void main(String[] args) {
		int[] arr={5, 17, 100, 11};
		//here we are sorting the array using merge sorting implementation
		 mergeSorting(arr,0,arr.length-1);
		 int mindiff=Integer.MAX_VALUE;
	      for(int i=0;i<arr.length-1;i++){
	    	  //after sorting we can find the difference minimum difference easy by iterating the array from 0 th index to array length
	           int diff=Math.abs(arr[i]-arr[i+1]);
	           if(diff<mindiff){
	        	   //if the difference is less then the answer we are making it as answer
	               mindiff=diff;
	           }
	       }
	        System.out.println(mindiff);
	}
	//explained about this merge sorting in detail Maximum And Minimum problem
	public static void mergeSorting(int[] arr,int start,int end){
        if(start==end)
            return;
        int mid=(start+end)/2;
        mergeSorting(arr,start,mid);
        mergeSorting(arr,mid+1,end);
        mergeSortImple(arr,start,mid,end);
    }
	 public static void mergeSortImple(int[] arr,int start,int mid,int end){
	        int p1=start;
	        int p2=mid+1;
	        int index=0;
	        int[] copyArr=new int[end-start+1];
	        while(p1<=mid && p2<=end){
	            if(arr[p1]<=arr[p2]){
	                copyArr[index]=arr[p1];
	                p1++;index++;
	            }
	            else if(arr[p2]<arr[p1]){
	                copyArr[index]=arr[p2];
	                p2++;index++;
	            }
	        }
	        while(p1<=mid){
	             copyArr[index]=arr[p1];
	                p1++;index++;
	        }
	        while(p2<=end){
	             copyArr[index]=arr[p2];
	                p2++;index++;
	        }
	        int ind=0;
	        for(int i=start;i<=end;i++){
	            arr[i]=copyArr[ind];
	            ind++;
	        }
	         
	    }

}
