package com.prudhvi.sorting.merge_sorting;

import java.util.Arrays;

public class MaximumAndMinimumMagic {
	/*
	  Given an array of integers A of size N where N is even.
	Divide the array into two subsets such that
		1.Length of both subset is equal.
		2.Each element of A occurs in exactly one of these subset.
		Note:-Magic number = sum of absolute difference of corresponding elements of subset.
 				You can reorder the position of elements within the subset to find the value of the magic number.

		For Ex:- 
				subset 1 = {1, 5, 1}, 
				subset 2 = {1, 7, 11},
				Magic number = abs(1 - 1) + abs(5 - 7) + abs(1 - 11) = 12
				Return an array B of size 2, where B[0] = maximum possible value of Magic number modulo 109 + 7, B[1] = minimum possible value of a Magic number modulo 10^9 + 7
				
				input: A = [3, 11, -1, 5].
				output=[14,10]
	  
	 	Hint1: Observe that the ordering of the elements affects the final answer, and we can reorder our array.
				You can try sorting the array first. This will help you formulate your solution easier maybe make the thought process clearer.
	 	Hint2: Initially, sort the array in ascending order.
				For the minimum magic, find the sum of the difference between adjacent elements in pairs of two.
					for i=1 ; i<n ; i+=2
    					min_magic+=a[i]-a[i-1]
				For the maximum magic, find the sum of the difference between the two elements equidistant from the front and back of the array.
					for i=0 ; i<n/2 ; i++
    				max_magic+=a[n/2+i]-a[i]
	 		* */

	public static void main(String[] args) {
		int[] arr= {3, 11, -1, 5};
		int[] ans=magicNums(arr);
		System.out.println(Arrays.toString(ans));
	}
	public static int[] magicNums(int[] A) {
		//here we need to sort to find the magic number by subtracting one half with second half,second magic number  by subtracting alternate numbers
        sort(A,0,A.length-1);
        int mod=1000000007;
        int p1=A.length/2;
        int mid=A.length/2;
        long halfsums=0;
        for(int i=0;i<p1;i++){
            int ele=Math.abs(A[i]-A[mid]);
            halfsums=(halfsums+ele)%mod;
            mid++;  
        }
        long alterSum=0;
        for(int i=0;i<A.length;i=i+2){
            int ele=Math.abs(A[i]-A[i+1]);
            alterSum=(alterSum+ ele)%mod;
        }
       int[] ans={(int)halfsums%mod,(int)alterSum%mod};
       return ans;
	}
	 public static void sort(int[] arr,int start,int end){
		 //for sorting we are implementing merge sorting, in this we mainly use divide and concur method by calling function recursively
	        if(start==end){
	            return;
	        }
	        //finding the mid part
	        int mid=(start+end)/2;
	        //saying divide the array into first half from start to mid
	        sort(arr,start,mid);
	       //saying divide the array into second  half from mid+1 to end
	        sort(arr,mid+1,end);
	        //after dividing the array merge two array in correct order
	        merge(arr,start,mid,end);
	    }
	 public static void merge(int[] a,int s,int m,int e){
		 	//creating array to add elements in sorted order of given array
	        int[] arr=new int[e-s+1];
	        //placing one pointer at start
	        int p1=s;
	        //placing second pointer at mid+1
	        int p2=m+1;
	        //this i is for indexing purpose
	        int i=0;
	        //this loop will run until any array reaches to end index,larger array indexes are left in array it self
	        while(p1<=m && p2<=e){
	            //if element at p1 index in original array is smaller or equals then p2 index element in input array.
	        	if(a[p1]<=a[p2]){
	        		//add the element at ith index of sorting array( arr ) and increment the p1 and index values
	                arr[i]=a[p1];
	                i++;p1++;
	            }
	        	//if element at p2 index in original array is smaller than p1 index element in input array.
	            else if(a[p2]<a[p1]){
	            	//add the element at ith index of sorting array( arr ) and increment the p2 and index values
	                arr[i]=a[p2];
	                i++;p2++;
	            }
	        }
	        //all the p2 elements are added to the array & p2 is mid to end length is less. 
	        //if array reaches this point means p2 array is smaller add all the elements left in p1 array to end of the array
	        while(p1<=m){
	            arr[i]=a[p1];
	            i++;p1++;

	        }
	      //all the p2 elements are added to the array & p1 is start to mid length is less. 
	        //if array reaches this point means p2 array is smaller add all the elements left in p1 array to end of the array
	        while(p2<=e){
	            arr[i]=a[p2];
	            i++;p2++;

	        }
	        int index=0;
	        //replace all the elements with the sorted order back to the original array
	        for(int j=s;j<=e;j++){
	            a[j]=arr[index];
	            index++;
	        }
	    }

}
