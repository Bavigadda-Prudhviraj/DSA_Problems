package com.prudhvi.linked_list;

public class MergeTwoSortedLists {
	/*
	Problem Description
		Merge two sorted linked lists, A and B, and return it as a new list.
		The new list should be made by splicing together the nodes of the first two lists and should also be sorted.

	Problem Constraints
		0 <= |A|, |B| <= 105
	 */

	public static void main(String[] args) {
		int[] arr1= {1,3,5,7,9,9};
		int[] arr2= {2,4,6,8,10,10};
		ListNode headNode1=ListNode.convertArrtoLinkedList(arr1);
		ListNode headNode2=ListNode.convertArrtoLinkedList(arr2);
		ListNode mergedNode=merge(headNode1,headNode2);
		ListNode.printLinkedList(mergedNode);

	}
	/*
	The merge method takes two sorted linked lists, h1 and h2, and merges them into a single sorted linked list
	
	Time Complexity:
			The time complexity of the merge method is O(m + n), where m and n are the lengths of the input linked lists h1 and h2. 
			The method iterates through the two lists simultaneously, comparing and merging the nodes.

	Space Complexity:
			The space complexity of the method is O(1) as it does not require any additional data structures. 
			It performs the merge in-place by modifying the next pointers of the existing nodes.
	
	 */
	public static ListNode merge(ListNode h1,ListNode h2) {
		//If the first linked list h1 is null, it means there are no nodes in h1,
		//so we can simply return h2 as it is already sorted.
		if(h1==null) {
			return h2;
		}
		//If the second linked list h2 is null, it means there are no nodes in h2, 
		//so we can simply return h1 as it is already sorted.
		else if(h2==null){
			return h1;
		}
		//Two temporary variables, head and current, are initialized to null. 
		//These variables will be used to track the head and current node of the merged list.
		ListNode head=null;
		ListNode current=null;
		//Compare the values of the first nodes of h1 and h2. 
		//If h1.value is less than or equal to h2.value, set head and current to h1 and move h1 to the next node.
		if(h1.value<=h2.value) {
			head=h1;
			current=h1;
			h1=h1.next;
		}//If h1.value is greater than h2.value, set head and current to h2 and move h2 to the next node.
		else if(h1.value>h2.value) {
			head=h2;
			current=h2;
			h2=h2.next;
		}
		//Enter a loop that continues until either h1 or h2 becomes null.
		//Inside the loop, compare the values of the current nodes of h1 and h2
		while(h1!=null && h2!=null){
			//If h1.value is less than or equal to h2.value, connect the current node of current to h1, 
			//move h1 to the next node, and update current to the new current node.
			if(h1.value<=h2.value) {
				current.next=h1;
				h1=h1.next;
				current=current.next;
			}
			//If h1.value is greater than h2.value, connect the current node of current to h2, 
			//move h2 to the next node, and update current to the new current node.
			else {
				current.next=h2;
				h2=h2.next;
				current=current.next;
			}
		}
		//After the loop, check if there are any remaining nodes in either h1 or h2.
		//If h1 is not null, connect the remaining nodes of h1 to the current node of current.
		if(h1!=null) {
			current.next=h1;
		}
		////If h2 is not null, connect the remaining nodes of h2 to the current node of current.
		else {
			current.next=h2;
		}
		//eturn the head node, which represents the head of the merged sorted linked list.
		return head;
	}

}
