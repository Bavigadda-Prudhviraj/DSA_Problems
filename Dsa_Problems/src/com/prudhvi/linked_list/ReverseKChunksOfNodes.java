package com.prudhvi.linked_list;

public class ReverseKChunksOfNodes {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		ListNode revesedLL=reveseHead(head);
		ArrToLL.printLinkedList(revesedLL);

	}
	/*
	The method reverseLinkedList takes a linked list head as input and returns the reversed linked list.
	It reverses the order of nodes in the linked list by changing the next pointers of each node.
	
	Time complexity:
			Best case : O(1) when the linked list is empty or contains only one node.
			Worst case: O(n) when the linked list contains n nodes, as each node needs to be visited and its next pointer updated during the reversal process.
	Space complexity: 
			O(1) as it uses a constant amount of additional space to hold the previous, current, and next node variables.
	
	 */
	private static ListNode reverseLinkedList(ListNode head) {
		//Declares a variable to hold the previous node during the reversal process. Initially set to null.
		ListNode previousNode=null;
		//Assigns the head of the linked list to a current node variable for traversal.
		ListNode currentNode=head;
		//Declares a variable to hold the next node in the original linked list.
		ListNode nextNode;
		// Begins a loop to iterate through each node in the linked list until the current node becomes null, indicating the end of the original list.
		while (currentNode!=null){
			//Stores the next node of the current node before modifying its next pointer. 
			//This is necessary to keep track of the remaining nodes in the original list.
			nextNode=currentNode.next;
			//Moves the previous node pointer to the current node, as it will become the previous node for the next iteration.
			currentNode.next=previousNode;
			//Moves the current node pointer to the next node, allowing the loop to progress to the next iteration.
			previousNode=currentNode;
			currentNode=nextNode;
		}
		return previousNode;
		
	}
	public static ListNode reverseList(ListNode A) {
	    ListNode node, prev, temp;
	    node = A;
	    if (node == null)
	        return null;
	    prev = null;
	    while (node != null) {
	        temp = node.next;
	        node.next = prev;
	        prev = node;
	        node = temp;
	    }
	    return prev;
	}
	public static ListNode reveseHead(ListNode head){
		ListNode dataNode=head;
		ListNode tempNode=head;
		ListNode endNode=null;
		while(dataNode!=null){
			tempNode=dataNode;
			dataNode=dataNode.next;
			tempNode.next=null; //this line is not needed
			tempNode.next=endNode;
			endNode=tempNode;
		}
		return  tempNode;
		
	}

}
