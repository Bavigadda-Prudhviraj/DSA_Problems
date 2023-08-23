package com.prudhvi.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FinishMaximumJobs {
	/*
	Problem Description
		There are N jobs to be done, but you can do only one job at a time.
		Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.
		Your aim is to select jobs in such a way so that you can finish the maximum number of jobs.
		Return the maximum number of jobs you can finish.
	Problem Constraints
		1 <= N <= 105
		1 <= A[i] < B[i] <= 109
	 */
	public static void main(String[] args) {
		ArrayList<Integer> startTime=new ArrayList<>(List.of(9,2,7,10,4,7,10,8));
		ArrayList<Integer> endTime  =new ArrayList<>(List.of(11,4,9,1,6,8,3,10));
		int maxJobs=finishMaxJobs(startTime,endTime);
		System.out.println(maxJobs);
	}
	/*
	The code is an implementation of a greedy algorithm to find the maximum number of non-overlapping jobs that can be completed, given their start and end times. 
	The algorithm sorts the jobs based on their end times and then iterates through the sorted jobs to select non-overlapping ones.
	
	Time Complexity:
			Sorting the array of pairs takes O(N * log N) time.
			The loop to iterate through the sorted array takes O(N) time.
		Therefore, the overall time complexity of the function is O(N * log N).
	
	Space Complexity:
			The space complexity is determined by the storage used for the array of pairs, which takes O(N) space.
			Additionally, a constant amount of extra space is used for variables, temporary storage, and the Pair and MyComparator classes.
		Therefore, the overall space complexity is O(N).
	 */
	public static int finishMaxJobs(ArrayList<Integer> startTime,ArrayList<Integer> endTime){
		int maxJobs=0;//maxJobs to keep track of the maximum number of non-overlapping jobs.
		int size=startTime.size();
		Pair[] arr=new Pair[size];//It creates an array of Pair objects, where each Pair holds a start time and an end time.
		for(int i=0;i<startTime.size();i++) {
			arr[i]=new Pair(startTime.get(i),endTime.get(i));
		}
		Arrays.sort(arr,new MyComparator());//The array is sorted based on the end times using a custom comparator MyComparator.
		int endTimeTrack=arr[0].endTime;//The variable endTimeTrack is used to keep track of the latest end time among selected jobs.
		//The first job with the earliest end time is selected by default, so maxJobs is incremented, and endTimeTrack is updated.
		maxJobs++;
		for(int i=1;i<arr.length;i++) {
			//If the current job's start time is greater than or equal to endTimeTrack, it means the job does not overlap with the previously selected job. 
			//So, the job is selected, maxJobs is incremented, and endTimeTrack is updated to the current job's end time.
			if(arr[i].startTime>=endTimeTrack) {
				maxJobs++;
				endTimeTrack=arr[i].endTime;
			}
		}
		return maxJobs;
		
	}
	
}
class Pair{
	int startTime;
	int endTime;
	public Pair(int start,int end) {
		this.startTime=start;
		this.endTime=end;
	}
	@Override
	public String toString() {
		return "Pair [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
class MyComparator implements Comparator<Pair>{

	@Override
	public int compare(Pair o1, Pair o2) {
		return o1.endTime-o2.endTime;
	}
	
	
}
