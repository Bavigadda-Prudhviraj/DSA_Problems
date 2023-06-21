package com.prudhvi.sorting.quick_sorting;

import java.util.*;


public class ActualQuickSort {
	/*
	  QuickSort is a popular and efficient sorting algorithm that follows the divide-and-conquer approach. 
	  It works by selecting a pivot element from the array and partitioning the other elements into two sub arrays, 
	  according to whether they are less than or greater than the pivot. The process is recursively applied to the sub arrays until the entire array is sorted
	 
	 Here's a step-by-step explanation of the QuickSort algorithm:
			1.Select a pivot element: Choose a pivot element from the array. The pivot can be selected in various ways, but a common approach is to choose the last element of the array as the pivot.
			2.Partitioning: Reorder the array in such a way that all elements less than the pivot are placed before it, and all elements greater than the pivot are placed after it. After partitioning, the pivot element will be in its final sorted position.
			3.Recursion: Recursively apply the above steps to the sub arrays formed by the partitioning. That is, repeat the process for the sub array before the pivot and the sub array after the pivot. This process continues until each sub array contains only one element or is empty.
			4.Combine the sorted sub arrays: Since the individual sub arrays are sorted, combining them will result in a fully sorted array. No additional merging step is necessary, as the sorting is performed in-place.
			
			
	why Random index we are choosing?
		Choosing a random index as the pivot in QuickSort is a technique known as random pivot selection. It helps mitigate the possibility of encountering the worst-case time complexity scenario, which occurs when the pivot is consistently chosen as the smallest or largest element in the array. Random pivot selection introduces an element of randomness that helps distribute the values more evenly during partitioning, improving the average-case performance of the algorithm.
	Reasons:-
		1.Avoiding worst-case time complexity: QuickSort's worst-case time complexity occurs when the pivot is repeatedly chosen as the smallest or largest element. This happens, for example, when the array is already sorted or contains many duplicate values. By selecting a random index as the pivot, the chances of consistently choosing the smallest or largest element are greatly reduced, mitigating the worst-case scenario.
		2.Improving average-case performance: Random pivot selection helps achieve a more balanced partitioning of the array on average. With a random pivot, the likelihood of encountering unbalanced partitions (i.e., significantly uneven sub arrays) decreases. Balanced partitions lead to better overall performance by reducing the number of recursive calls and comparisons required.
		3.Enhancing algorithm stability: When dealing with large data sets or data sets with complex patterns, choosing a random pivot can enhance the stability of the algorithm. Stability refers to the preservation of the relative order of equal elements during the sorting process. By introducing randomness, the algorithm is less likely to encounter patterns that could potentially lead to unstable behavior.
		4.It's important to note that while random pivot selection improves the average-case performance of QuickSort, it does not guarantee the best-case or optimal performance in all scenarios. Other techniques, such as median-of-three pivot selection or choosing a pivot using statistical methods, can further improve the algorithm's performance characteristics.
	Overall, choosing a random index as the pivot in QuickSort helps reduce the likelihood of encountering worst-case scenarios, improves average-case performance, and enhances algorithm stability. These benefits contribute to the overall efficiency and reliability of the QuickSort algorithm in practical applications
	 
	 Time complexity: worst case O(n^2) it is consider when we select start as random
	 				  in general O(n log n) it is because of using random function
	 Space complexity:O(N)
	 */

	public static void main(String[] args) {
		int[] arr= {18,8,6,3,11,14,23,20,31,27};
		quickRecursion(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

	private static void quickRecursion(int[] arr, int start, int end) {
		
		if(start>=end) {
			return;
		}
		//here are we are selecting the index passing that index will be placed at right place 
		int index=quickSort(arr,start,end);
		//calling recursion function for left part of the index to sort left part
		quickRecursion(arr, start, index-1);
		//calling the recursion function for right part of the present index
		quickRecursion(arr, index+1, end);	
	}
	private static int quickSort(int[] arr, int start, int end) {
		//read line number 19
		//it reduce 80% time complexity to O(nLonn)
		int randomIndex=(int) (Math.random()*(end-start)+start);
		int temp1=arr[randomIndex];
		arr[randomIndex]=arr[start];
		arr[start]=temp1;
		//here we are selecting p1 as start+1 because we have to place the start index at the right place
		int p1=start+1;
		int p2=end;
		//here we are considering happy element and unhappy element
		//we have to place <= array[0] left side , this are happy elements according to array[0]
		//we have to place > array[0] right side , this are happy elements according to array[0]
		//when we reach p1<=p2 all elements (happy elements) are placed 
		while (p1<=p2){
			//we faced an element which is greater then array[0], this is unhappy element
			if(arr[p1]<=arr[start]) {
				p1++;
			}
			else if(arr[p1]>arr[start]) {
				p2--;
			}
			//we faced an element which is array[p1] greater then array[0], this is unhappy element
			//we faced an element which is  array[p2] less then array[0], this is unhappy element
			//we have to place all greater elements right side and <= elements in left side
			//there are two unhappy elements we have to swap those elements to make happy elements
			else{
				int temp=arr[p1];
				arr[p1]=arr[p2];
				arr[p2]=temp;
			}	
		}
		//when p1 and p2 are crossed we have to swap with p2 element
		int temp=arr[start];
		arr[start]=arr[p2];
		arr[p2]=temp;
		return p2;
	}

}
