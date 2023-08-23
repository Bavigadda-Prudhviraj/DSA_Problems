package com.prudhvi.greedy;

import java.util.ArrayList;
import java.util.List;

public class DistributeCandy {
	/*
	Problem Description
			N children are standing in a line. Each child is assigned a rating value.
			You are giving candies to these children subjected to the following requirements:
					1.Each child must have at least one candy.
					2.Children with a higher rating get more candies than their neighbors.
			What is the minimum number of candies you must give?
	Problem Constraints
			1 <= N <= 105
			-109 <= A[i] <= 109
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arrayList=new ArrayList<>(List.of(1,6,3,1,10,12,20,5,2));
		int totalCandies=distributeCandies(arrayList);
		System.out.println(totalCandies);
	}
	/*
	problem where you need to distribute candies among a group of people based on their ratings, such that higher-rated individuals get more candies than their neighbors. 
	The goal is to distribute the minimum number of candies while satisfying the rating constraints
	
	Time Complexity:
			The code iterates through the array multiple times with linear loops. 
			Each iteration takes O(N) time, where N is the size of the input array.
	
	Space Complexity:
			The space complexity is mainly determined by the candies ArrayList, which stores the number of candies for each person. 
			This takes O(N) space, where N is the size of the input array. Other variables used in the code have constant space complexity.
	 */
	public static int distributeCandies(ArrayList<Integer> arr) {
		ArrayList<Integer> candies=new ArrayList<>();//Initialize an ArrayList candies to store the number of candies given to each person.
		int totalCandies=0;//a variable totalCandies to keep track of the total number of candies distributed.
		//The loop initializes each person's candies to 1 in the candies ArrayList.
		for(int i=0;i<arr.size();i++) {
			candies.add(1);
		}
		//The first loop traverses from the second person to the last
		for(int i=1;i<candies.size();i++) {
			//It checks if the rating of the current person is higher than the previous person. 
			//If so, it updates the number of candies for the current person to be one more than the previous person's candies.
			if(arr.get(i-1)<arr.get(i)) {
				candies.set(i,candies.get(i-1)+1);
			}
		}
		//The second loop traverses from the second-to-last person to the first
		for(int i=arr.size()-2;i>=0;i--) {
			//It checks if the rating of the current person is higher than the next person and if the current person's candies are less than or equal to the next person's candies. 
			//If so, it updates the current person's candies to be one more than the next person's candies.
			if(arr.get(i)>arr.get(i+1) && candies.get(i)<=candies.get(i+1)) {//
				candies.set(i, candies.get(i+1)+1);
			}
		}
		//The last loop calculates the totalCandies by summing up the candies for each person.
		for(int i=0;i<candies.size();i++) {
			totalCandies+=candies.get(i);
		}
		return totalCandies;
	}

}
