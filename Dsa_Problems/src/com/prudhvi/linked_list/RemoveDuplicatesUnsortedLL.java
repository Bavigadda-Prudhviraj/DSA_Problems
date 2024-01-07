package com.prudhvi.linked_list;
public class RemoveDuplicatesUnsortedLL {

	public static void main(String[] args) {
		int[] arr= {2,2,2,2,2};
		ListNode head=ListNode.createLinkedListFromArray(arr);
		ListNode llListNode=removeDuplicates(head);
		ListNode.printLinkedList(llListNode);

	}
	public static ListNode removeDuplicates(ListNode head) {
	    // Check if the linked list is empty or has only one node
	    if (head == null || head.next == null) 
	        return head;
	    // Initialize a temporary pointer to the head of the linked list
	    ListNode temp = head;

	    // Traverse the linked list to remove duplicate nodes
	    while (temp.next != null && temp.next.next != null) {
	        // Check if the current node has the same value as the next node
	        if (temp.value == temp.next.value)
	            // Skip the next node by updating the next pointer
	            temp.next = temp.next.next;
	        else 
	            // Move to the next node
	            temp = temp.next;
	        
	    }

	    // Check if the last two nodes have the same value and remove the duplicate
	    if (temp.value == temp.next.value) 
	        temp.next = null;
	    // Return the head of the modified linked list
	    return head;
	}


}
