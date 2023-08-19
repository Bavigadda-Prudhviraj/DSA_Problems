package com.prudhvi.heaps;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.prudhvi.linked_list.ListNode;

public class MergeKSortedLists {
	/*
	Problem Description
		Given a list containing head pointers of N sorted linked lists.
		Merge these given sorted linked lists and return them as one sorted list.
	Problem Constraints
		1 <= total number of elements in given linked lists <= 100000
	 */
	public static void main(String[] args) {
		int[] arr1= {1,10,20};
		int[] arr2= {4,11,13};
		int[] arr3= {3,8,9};
		ListNode l1=ListNode.convertArrtoLinkedList(arr1);
		ListNode l2=ListNode.convertArrtoLinkedList(arr2);
		ListNode l3=ListNode.convertArrtoLinkedList(arr3);
		ArrayList<ListNode> arrayList=new ArrayList<>();
		arrayList.add(l1);
		arrayList.add(l2);
		arrayList.add(l3);
		ListNode sortedListNode=sortedLinkedList(arrayList);
		ListNode.printLinkedList(sortedListNode);
	}
	/*
	Function sortedLinkedList that takes an ArrayList of sorted linked lists (ListNode objects) and merges them into a single sorted linked list. 
	It uses a min heap (priority queue) to efficiently merge the linked lists
	
	Time Complexity:
			Adding all values from the linked lists to the min heap takes O(N * M * log N) time, where N is the number of linked lists and M is the average number of nodes in a linked list.
			Constructing the sorted linked list from the min heap takes O(N * M * log N) time.
		Therefore, the overall time complexity of the function is O(N * M * log N).
	Space Complexity:
			The space complexity is determined by the storage used for the priority queue (minHeap), which can hold a maximum of N * M elements.
			Additionally, a constant amount of extra space is used for variables and temporary storage.
		Therefore, the overall space complexity is O(N * M).
	 */
	public static ListNode sortedLinkedList(ArrayList<ListNode> arr) {
		// Create a min heap to store node values
		PriorityQueue<Integer> minHeap=new PriorityQueue<>();
		// Add all values from linked lists into the min heap
		for(int i=0;i<arr.size();i++) {
			allValuesIntoHeap(arr.get(i),minHeap);
		}
		 // Create the sorted linked list
		ListNode root=new ListNode(minHeap.poll());
		ListNode temp=root;
		 // Construct the linked list using values from the min heap
		while(!minHeap.isEmpty()){
			temp.next=new ListNode(minHeap.poll());
			temp=temp.next;
		}
		return root;
		
	}
	private static void allValuesIntoHeap(ListNode root, PriorityQueue<Integer> minHeap) {
		ListNode temp=root;
		while(temp!=null){
			 // Add all values from the linked list to the min heap
			minHeap.add(temp.value);
			temp=temp.next;
		}
	}
}
