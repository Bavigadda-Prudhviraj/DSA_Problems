package com.prudhvi.binarysearch;

import java.util.*;

public class AggressiveCows {
	/*
	 Farmer John has built a new long barn with N stalls. Given an array of integers A of size N where each element of the array represents the location of the stall and an integer B which represents the number of cows.
	His cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, John wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?

	Problem Constraints
		2 <= N <= 100000
		0 <= A[i] <= 109
		2 <= B <= N
	 */

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5};
		int cows=3;
		int ans=solve(arr, cows);
		System.out.println(ans);

	}
	 public static int solve(int[] A, int B) {
	        Arrays.sort(A);
	        int minDistance=Integer.MAX_VALUE;

	        int min=Integer.MAX_VALUE;
	        int max=Integer.MIN_VALUE;

	        for(int i=A.length-1;i>=0;i--){
	        	//The conditions min > A[i] and max < A[i] are used to update the values of min and max respectively, ensuring that they store the correct minimum and maximum elements in the array.
	            if(min>A[i]){
	                 min=A[i];
	            }
	            if(max<A[i]){
	                max=A[i];
	            }
	            //The if condition i > 0 is used to skip the calculation of minDistanceBetweenElements for the first iteration since there is no previous element to compare with.
	            if(i>0){
	            	//minDistanceBetweenElements is calculated as the difference between the current element A[i] and the previous element A[i - 1]
	                int minDistanceBetweenElements=A[i]-A[i-1];
	                if(minDistanceBetweenElements<minDistance){
	                	//If minDistanceBetweenElements is smaller than the current minDistance, it means that a smaller distance between elements has been found, so minDistance is updated with this new value.
	                    minDistance=minDistanceBetweenElements;
	                } 
	            }
	            
	        }//maxDistance is calculated as the difference between the maximum element (max) and the minimum element (min)
	        int maxDistance=max-min;
	        //low is set to minDistance, representing the lower bound of the search range
	        int low=minDistance;
	        //high is set to maxDistance, representing the upper bound of the search range
	        int high=maxDistance;
	        int ans=0;//ans is initialized to 0, representing the variable to store the final answer
	        //The code enters a while loop that continues as long as low is less than or equal to high. This signifies a binary search iteration.
	        while(low<=high){
	            int mid=low+(high-low)/2;
	            //The  condition checks if check(mid, B, A) returns true, indicating that the current distance mid between cows is valid for the given number of cows B
	            if(placeBcowsWithDistanceMid(mid,B,A)) {
	            	//If the condition is true, it means that the current mid value is a potential answer, 
	            	//so it updates ans with this value and adjusts the lower bound low to mid + 1 to continue searching for larger valid distances.
	            	ans=mid;
	            	low=mid+1;
	            	
	            }
	            //If the condition is false, it means that the current mid value is not valid, so it adjusts the upper bound high to mid - 1 to search for smaller distances.
	            else {
	            	high=mid-1;
	            }
	        }
	        return ans;
	    }
	 public static boolean placeBcowsWithDistanceMid(int currentDistance,int noOfCows,int[] arr) {
		 int currentCowDistance=arr[0];
		 int cowsCount=1;//cowsCount is initialized to 1, representing the count of cows that have been placed so far.
		 for (int i = 1; i < arr.length; i++) {
			 int distanceBetweenCows=arr[i]-currentCowDistance;//distanceBetweenCows is calculated as the difference between the current element (arr[i]) and the distance of the previously placed cow (currentCowDistance).
			if(distanceBetweenCows>=currentDistance) {
				//If distanceBetweenCows is greater than or equal to currentDistance, it means that the current distance is valid for placing another cow. 
				cowsCount++;
				//Hence, cowsCount is incremented, currentCowDistance is updated to the distance of the current element, and if cowsCount is equal to noOfCows, 
				currentCowDistance=arr[i];
				if(cowsCount==noOfCows) {
					//it means that all the cows have been placed successfully, so the method returns true.
					return true;
				}
				
			}
		}
		 //If the for loop completes without returning true, it means that the given currentDistance is not valid for placing all the cows, so the method returns false.
		 return false;
		
	}

}
