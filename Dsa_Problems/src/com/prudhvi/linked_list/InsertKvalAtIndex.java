package com.prudhvi.linked_list;



public class InsertKvalAtIndex {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		int index=7;
		int element=10;
		ListNode addLL=insertAt(head,index,element);
		ArrToLL.printLinkedList(addLL);
	}
	/*
	The method insertAt takes a linked list head, an index, and an element as input parameters.
	It is used to insert a new node with the given element at the specified index in the linked list.
	
	Time complexity:
		Best case: O(1) when inserting at the beginning of the list.
		Worst case: O(n) when inserting at the end or a specific index, where n is the length of the linked list.

	Space complexity: 
		O(1) as it uses a constant amount of additional space to create a new node.
	 */
	public static ListNode insertAt(ListNode head,int index,int element){
		//Assigns the head of the linked list to a temporary node for traversal.
		ListNode tempNode=head;
		//Declares a new ListNode variable to hold the new node to be inserted.
		ListNode newNode;
		//Checks if the index is 1 or less than 1, indicating that the new node should be inserted at the beginning of the list.
		if(index==1 || index<1) {
			 newNode=new ListNode(element);//Creates a new node with the given element value.
			 //Sets the next pointer of the new node to the current head of the linked list.
			newNode.next=tempNode;
			//Returns the new node as the new head of the modified linked list.
			return newNode;
		}
		//Iterates from 1 to index-1 to find the node before the index where the new node should be inserted.
		for(int i=1;i<index-1;++i) {
			//Checks if the current node is the last node in the linked list.
			if(tempNode.next==null) {
				ListNode newnNode = new ListNode(element);
				//Sets the next pointer of the current node to the new node.
				tempNode.next=newnNode;
				return head;
			}
			tempNode=tempNode.next;
		}
		newNode=new ListNode(element);
		//Sets the next pointer of the new node to the node at the specified index.
		newNode.next=tempNode.next;
		//Sets the next pointer of the previous node to the new node, effectively inserting the new node into the linked list.
		tempNode.next=newNode;	
		return head;
		
	}

}
