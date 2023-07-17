package com.prudhvi.linked_list;

public class MergeSort {
	/*
	Problem Description
		Sort a linked list, A in O(n log n) time.

	Problem Constraints
		0 <= |A| = 105
	 */

	public static void main(String[] args) {
		int[] arr= {2,1,3,4,6,4,7,8,9,0,1,2,3};
		ListNode listNode=ListNode.convertArrtoLinkedList(arr);
		ListNode sortedlListNode=mergeSort(listNode);
		ListNode.printLinkedList(sortedlListNode);
		
	}
	/*
	The merge method merges two sorted linked lists in ascending order. 
	It takes two sorted linked lists, head1 and head2, and merges them into a single sorted linked list.

	The listMid method finds the middle node of a linked list using the two-pointer technique. It returns the middle node.
	
	Time Complexity:
			The time complexity of the mergeSort function in the best and worst case scenarios is O(n log n), 
			where n is the number of nodes in the linked list. 
			This is because the list is recursively divided into halves and then merged, 
			similar to the standard merge sort algorithm.
	
	Space Complexity:
			The space complexity of the mergeSort function is O(log n) in the worst case scenario, 
			where n is the number of nodes in the linked list. 
			This is due to the recursion stack space used for the recursive calls. 
			In the best case scenario, the space complexity is O(1) if the linked list is already sorted.
	 */
	public static ListNode mergeSort(ListNode head){
		//Check if head is null. If true, return head as there is nothing to sort.
		if(head==null) {
			return head;
		}
		//Check if head.next is null. If true, return head as it is a single node and already sorted
		else if(head.next==null){
			return head;	 
		}
		//Find the middle node of the linked list using the listMid method.
		ListNode firstMidNode=listMid(head);
		//Get the second half of the linked list by assigning secondMidNode.
		//next to secondHalfNodes and disconnecting the first half from the second half by setting firstMidNode.next to null.
		ListNode secondMidNode=firstMidNode.next;
		firstMidNode.next=null;
		
		ListNode firstHalfNodes=mergeSort(head);//Recursively call mergeSort on the first half of the linked list.
		ListNode secondHalfNodes=mergeSort(secondMidNode);//Recursively call mergeSort on the second half of the linked list.
		//Merge the two sorted halves using the merge method and assign the result to mergeTwoLists.
		ListNode mergeTwoLists=merge(firstHalfNodes,secondHalfNodes);
		//Return the merged and sorted linked list.
		return mergeTwoLists;
		
	}
	//to Understand this code go to class " MergTwoSortedLists ".
	public static ListNode merge(ListNode head1,ListNode head2){
		if(head1==null) {
			return head2;
		}
		else if(head2==null) {
			return head1;
		}
		ListNode headNode=null,tempNode=null;
		if(head1.value<=head2.value){
			headNode=head1;
			tempNode=head1;
			head1=head1.next;
		}
		else if(head1.value>head2.value) {
			headNode=head2;
			tempNode=head2;
			head2=head2.next;
		}
		while(head1!=null && head2!=null){
			if(head1.value<=head2.value) {
				tempNode.next=head1;
				head1=head1.next;
				tempNode=tempNode.next;
			}
			else {
				tempNode.next=head2;
				head2=head2.next;
				tempNode=tempNode.next;
			}
		}
		if(head1!=null) {
			tempNode.next=head1;
		}
		else {
			tempNode.next=head2;
		}
		return headNode;
		
	}
	//to Understand this code go to class " MiddleElementOfLinkedList  ".
	public  static ListNode listMid(ListNode head){
		if(head==null) {
			return head;
		}
		ListNode slowPointerNode=head;
		ListNode fastPointerNode=head;
		while(fastPointerNode.next!=null && fastPointerNode.next.next!=null){
			slowPointerNode=slowPointerNode.next;
			fastPointerNode=fastPointerNode.next.next;	
		}
		return slowPointerNode;
		
	}

}
