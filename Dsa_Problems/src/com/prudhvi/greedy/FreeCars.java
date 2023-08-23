package com.prudhvi.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FreeCars {
	/*
	Problem Description
		Given two arrays, A and B of size N. 
			A[i] represents the time by which you can buy the ith car without paying any money.
			B[i] represents the profit you can earn by buying the ith car. It takes 1 minute to buy a car, so you can only buy the ith car when the current time <= A[i] - 1.
		Your task is to find the maximum profit one can earn by buying cars considering that you can only buy one car at a time.
		NOTE:
			You can start buying from time = 0.
			Return your answer modulo 109 + 7.
	Problem Constraints
		1 <= N <= 105
		1 <= A[i] <= 109
		0 <= B[i] <= 109
	 */
	public static void main(String[] args) {
		ArrayList<Integer> time  =new ArrayList<>(List.of(1, 7,6,2, 8,4,4,6,8,2));
		ArrayList<Integer> profit=new ArrayList<>(List.of(8,11,7,7,10,8,7,5,4,9));
		int maxProfit=maxProfitFromCars(time,profit);
		System.out.println(maxProfit);
	}
	/*
	The function maxProfitFromCars that aims to find the maximum profit that can be obtained from cars by choosing the most profitable options within a given time constraint. 
	The input consists of two arrays: time containing the times when cars become available, and profit containing the corresponding profits for each car. 
	The approach sorts the cars based on their times and then uses a priority queue (min-heap) to track the most profitable cars within the given time constraint.
	
	Time Complexity:
			Sorting the array of CarPair objects takes O(N * log N) time.
			The loop to iterate through the sorted array and manage the priority queue takes O(N) time.
			The loop to calculate the maximum profit from the min-heap takes O(N * log N) time (since each insertion and deletion from the min-heap takes O(log N) time).
		Therefore, the overall time complexity of the function is O(N * log N).
	
	Space Complexity:
			The space complexity is determined by the storage used for the array of CarPair objects, which takes O(N) space.
			Additionally, a constant amount of extra space is used for variables, temporary storage, and the CarPair and CarComparator classes.
			The priority queue (min-heap) also requires space proportional to the number of elements in the array.
		Therefore, the overall space complexity is O(N).
	 */
	public static int maxProfitFromCars(ArrayList<Integer> time,ArrayList<Integer> profit){
		int mod=1000000007;
		long maxProfit=0;//to keep track of the maximum profit.
		PriorityQueue<Integer> minHeap=new PriorityQueue<>();// to store the profits of selected cars.
		int timeTrack=0;// to keep track of the current time.
		int size=time.size();
		CarPair[] arr=new CarPair[size];//array of CarPair objects, where each CarPair holds a time and a profit.
		// Create an array of CarPair objects using the time and profit data
		for(int i=0;i<size;i++) {
			arr[i]=new CarPair(time.get(i),profit.get(i));
		}
		// Sort the array of CarPair objects based on "time" using a custom comparator
		Arrays.sort(arr,new CarComparator());
		// Iterate through the sorted array to calculate the maximum profit
		for(int i=0;i<size;i++) {
			//If the current time slot is available (timeTrack < arr[i].time), the car's profit is added to the min-heap, and timeTrack is incremented.
			if(timeTrack<arr[i].time) {
				minHeap.add(arr[i].profit);
				timeTrack++;
			}
			//If the current time slot is not available, the code checks if the current car's profit is greater than the minimum profit in the heap (minHeap.peek()). 
			//If it is, the minimum profit is removed from the heap, and the current car's profit is added.
			else {
				if(minHeap.peek()<arr[i].profit) {
					minHeap.poll();
					minHeap.add(arr[i].profit);
				}
			}
		}
		// Sum up the profits in the minHeap to calculate the maximum profit
		while(!minHeap.isEmpty()){
			maxProfit=(maxProfit+minHeap.poll())%mod;
		}
		return (int)maxProfit;
	}
}
class CarPair{
	int time;
	int profit;
	public CarPair(int time,int profit) {
		this.time=time;
		this.profit=profit;
	}
}
class CarComparator implements Comparator<CarPair>{

	@Override
	public int compare(CarPair car1, CarPair car2) {
		return car1.time-car2.time;
	}
	
}
