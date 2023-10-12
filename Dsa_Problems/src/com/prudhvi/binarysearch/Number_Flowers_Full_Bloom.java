package com.prudhvi.binarysearch;

import java.util.Arrays;
import java.util.Comparator;
/*
	LeetCode:- 2251
	You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.
	Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.
 */
public class Number_Flowers_Full_Bloom {

	public static void main(String[] args) {
		int[][]flowers ={{1,6},{3,7},{9,12},{4,13}};
		int[] people = {2,3,7,11};
		int[] bloomFlowers=fullBloomFlowersOptimized(flowers,people);
		System.out.println(Arrays.toString(bloomFlowers));//{3,0}Output: [1,2,2,2]
		int[] bloomFlowerss=fullBloomFlowersBruteForce(flowers,people);
		System.out.println(Arrays.toString(bloomFlowerss));//{3,0}Output: [1,2,2,2]

	}
	/*
	This method is for solving a problem related to the bloom time of flowers and the time people arrive to see them. 
	The goal is to find the number of flowers that are fully bloomed when each person arrives. 
	The code first sorts the flower bloom start times and end times using two separate arrays. 
	Then, for each person's arrival time, it uses binary search to find the number of flowers that are fully bloomed.
	
	Time Complexity:
			The initial loop that extracts start and end times has a time complexity of O(flowers.length).
			Sorting the start and end arrays has a time complexity of O(flowers.length * log(flowers.length)) each.
			The loop for each person runs in O(people.length) time.
			The binary search functions, bsStart and bsEnd, both have a time complexity of O(log(flowers.length)).
		Overall, the time complexity is dominated by the sorting step, and it is O(flowers.length * log(flowers.length)).

	Space Complexity:
			The code uses additional space for the start, end, and arr arrays, each of which has a size of flowers.length or people.length, resulting in a space complexity of O(flowers.length + people.length).
		In summary, the code performs flower blooming optimization using binary search and has a time complexity of O(flowers.length * log(flowers.length)) and a space complexity of O(flowers.length + people.length).
	 */
	private static int[] fullBloomFlowersOptimized(int[][] flowers, int[] people) {
		//Create two arrays, start and end, to store the start and end times of flowers. These arrays are filled by extracting the respective values from the flowers array.
		int[] start=new int[flowers.length];
		int[] end=new int[flowers.length];
		for (int i = 0; i < flowers.length; i++) {
			start[i]=flowers[i][0];
			end[i]=flowers[i][1];
		}
		//Sort the start and end arrays. Sorting these arrays allows for efficient binary search operations to find the number of fully bloomed flowers for each person.
		Arrays.sort(start);
		Arrays.sort(end);
		//Initialize an array arr to store the results, where arr[i] will represent the number of fully bloomed flowers when the i-th person arrives.
		int[] arr=new int[people.length];
		//Iterate through the people array, and for each person's arrival time (startEnd), perform binary searches on the start and end arrays to find the indices (positions) where the person's arrival time falls. 
		//These indices will be used to determine the number of flowers fully bloomed at that time.
		for (int i = 0; i < people.length; i++) {
			int startEnd=people[i];
			int bloom=bsStart(startEnd,start);// Find the index of the first flower that starts blooming
			int unBloom=bsEnd(startEnd,end);// Find the index of the last flower that ends blooming
			//Calculate arr[i] by subtracting the number of flowers that have stopped blooming (bsEnd) from the number of flowers that have started blooming (bsStart). 
			//This calculation gives the number of fully bloomed flowers when the i-th person arrives.
			arr[i]=bloom-unBloom;
		}
		return arr;
	}
	//Implement the bsStart binary search function. It returns the position of the first flower that starts to bloom after the given bloom time. 
	//The binary search starts with the entire array, and the position where the binary search ends (i.e., low) represents the number of flowers fully bloomed before the person arrives.
	private static int bsStart(int bloom, int[] time) {
		int low=0;
		int high=time.length-1;
		while(low<=high){
			int mid=low+(high-low)/2;
			if(time[mid]<=bloom) {
				low=mid+1;
			}else {
				high=mid-1;
			}
		}
		return low;
	}
	//Implement the bsEnd binary search function. It returns the position of the last flower that ends blooming at the given bloom time. 
	//The binary search is similar to bsStart but finds the ending position.
	private static int bsEnd(int bloom, int[] time) {
		int low=0;
		int high=time.length-1;
		while(low<=high){
			int mid=low+(high-low)/2;
			if(time[mid]==bloom){
				return mid;
			}
			else if(time[mid]<bloom) {
				low=mid+1;
			}else {
				high=mid-1;
			}
		}
		return low;
	}
	//BruteForce
	private static int[] fullBloomFlowersBruteForce(int[][] flowers, int[] people) {
		int n=people.length;
		int m=flowers.length;
		int[] bloomFlowers=new int[n];
		Pair[] pairs=new Pair[m];
		for(int i=0;i<m;i++) {
			pairs[i]=new Pair(flowers[i][0], flowers[i][1]);
		}
		Arrays.sort(pairs,new MySortStart());
		for (int i = 0; i < people.length; i++) {
			int person=people[i];
			int startTime=binarySearchStart(person,pairs);
			int cnt=0;
			for(int j=startTime;j>=0;j--) {
				if(pairs[j].endTime>=person) {
					cnt++;
				}
				
			}
			bloomFlowers[i]=cnt;
		}
		return bloomFlowers;
	}

	private static int binarySearchStart(int person, Pair[] pairs) {
		int low=0;
		int high=pairs.length-1;
		int index=-1;
		while(low<=high){
			int mid=low+(high-low)/2;
			if(pairs[mid].startTime==person) {
				index=mid;
				low=mid+1;
			}else if(pairs[mid].startTime<person) {
				index =mid;
				low=mid+1;
			}else {
				high=mid-1;
			}
		}
		return index;
	}
	
}
class MySortStart implements Comparator<Pair>{

	@Override
	public int compare(Pair o1, Pair o2) {
		return o1.startTime-o2.startTime;
	}
	
}

class Pair{
	int startTime;
	int endTime;
	public Pair(int startTime,int endTime) {
		this.startTime=startTime;
		this.endTime=endTime;
	}
	@Override
	public String toString() {
		return "Pair [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
