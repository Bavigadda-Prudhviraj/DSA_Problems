package com.prudhvi.dynamic_programming.fractional_knapsack;

import java.util.Arrays;
import java.util.Comparator;
/*
	Problem Description
		Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
		Also given an integer C which represents knapsack capacity.
		Find out the maximum total value that we can fit in the knapsack. If the maximum total value is ans, then return ⌊ans × 100⌋ , i.e., floor of (ans × 100).
		NOTE: You can break an item for maximizing the total value of the knapsack
	Problem Constraints
		1 <= N <= 105
		1 <= A[i], B[i] <= 103
		1 <= C <= 103
 */
public class FractionalKnapsack {

	public static void main(String[] args) {
		int[] values= {10, 20, 30, 40};
		int[] weights = {12, 13, 15, 19};
		int	capacity = 10;
		int maxProfit=fractionalKnapsack(values,weights,capacity);
		System.out.println(maxProfit);

	}
	/*
	Code implements the fractional knapsack problem, which is a variant of the classical knapsack problem. 
	In the fractional knapsack problem, you are given a set of items, each with a weight and a value, and a knapsack with a maximum weight capacity. 
	The goal is to select items to include in the knapsack in such a way that their total value is maximized, and the total weight does not exceed the capacity of the knapsack. 
	Unlike the classical knapsack problem, in the fractional knapsack problem, you can take a fraction of an item if needed.
	Time Complexity:
		The time complexity of this code is determined by its most time-consuming operation.
		In this code, the dominant operation is the sorting of the items array, which takes O(n*log(n)) time.
		So, the overall time complexity is O(n*log(n)), where 'n' is the number of items.
	Space Complexity:
		The space complexity is determined by the amount of memory used by the code.
		The primary space-consuming factor here is the items array, which stores information about each item and takes up O(n) space.
		Other variables in the code consume constant space, not dependent on 'n'.
		Thus, the overall space complexity is O(n), where 'n' is the number of items.
	 */
	private static int fractionalKnapsack(int[] values, int[] weights, int capacity) {
		int n=values.length;
		//array each object represents an item with its value, weight, and value per weight (vpw). The vpw is the ratio of value to weight and is used to determine which items are most valuable per unit of weight.
		Items[] items=new Items[n];
		for(int i=0;i<items.length;i++) {
			double vpw=(values[i]*1.0)/weights[i];
			items[i]=new Items(values[i], weights[i], vpw);
		}
		//It sorts the items array in descending order based on the vpw of each item. This means that items with a higher value-to-weight ratio come last in the array.
		Arrays.sort(items,new ItemsComparator());
		//It initializes currCap (current capacity) to the total capacity of the knapsack and currVal (current value) to 0. These variables will be used to keep track of the remaining capacity and the total value of items selected.
		int currCap=capacity;
		double currVal=0;
		for(int i=n-1;i>=0;i--) {
			//It checks if the current capacity (currCap) is greater than or equal to the weight of the item (items[i].weight). 
			//If there is enough capacity, it adds the entire item to the knapsack by incrementing currVal with the item's value and decrementing currCap by the item's weight.
			if(currCap>=items[i].weight) {
				currVal+=items[i].value;
				currCap-=items[i].weight;
			}
			//If there is not enough capacity to add the entire item, it calculates the fraction of the item that can be added to the knapsack by 
			//multiplying the item's value by the ratio of the remaining capacity (currCap) to the item's weight. It then adds this fraction to currVal, and currCap is set to 0 to indicate that the knapsack is now full.
			else {
				currVal+=items[i].value*((double)currCap/items[i].weight);
				currCap=0;
				break;
			}
		}
		return (int)Math.floor((currVal*1000)/10);
	}

}
class Items {
	int value;
	int weight;
	double vPw;
	public Items(int value,int weight,double vPw) {
		this.value=value;
		this.weight=weight;
		this.vPw=vPw;
	}
}
class ItemsComparator implements Comparator<Items> {
    @Override
    public int compare(Items o1, Items o2) {
        double compare = o1.vPw - o2.vPw;
        if (compare < 0.0) {
            return -1;
        } else if (compare > 0.0) {
            return 1;
        } else {
            return 0;
        }
    }
}
