package com.prudhvi.linked_list;



public class DeleteKthElement {
	/*
	Problem Description
		Given a linked list A, remove the B-th node from the end of the list and return its head. 
		For example, Given linked list: 1->2->3->4->5, and B = 2. 
		After removing the second node from the end, the linked list becomes 1->2->3->5. 
			NOTE: 
				If B is greater than the size of the list, remove the first node of the list. 
				NOTE: 
					Try doing it using constant additional space.

		Problem Constraints
			1 <= |A| <= 106
	 */

	public static void main(String[] args) {
		int[] arr= {20,380,349,322,389,424,429,120,64,691,677,58,327,631,916,203,484,918,596,252,509,644,33,460};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		int target=83;
		ListNode answerNode=deleteKthElementFromEnd(head,target);
		ArrToLL.printLinkedList(answerNode);
		//from other resource method
		ListNode answerNode2=removeNthFromEnd(head, target);
		ArrToLL.printLinkedList(answerNode2);
		
	}
	/*
	The method function deleteKthElementFromEnd is used to delete the node at the given target position from the end of the linked list.

	Time Complexity:
			The time complexity of this code is O(n), where n is the length of the linked list. 
			It requires traversing the entire linked list to calculate its length and delete the node.

	Space Complexity:
			The space complexity of this code is O(1) because it uses a constant amount of additional space, 
			regardless of the size of the linked list.
	 */
	
	public static ListNode  deleteKthElementFromEnd(ListNode head,int target) {
		// a variable length to keep track of the length of the linked list.
		int length=0;
		//Checks if the head is null, indicating an empty linked list. In this case, it returns the head itself.
		if(head==null) {
			return head;
		}
		//Creates a new reference variable headNode and assigns it the value of head.
		ListNode headNode=head;
		//Enters a loop that traverses the linked list until the end, updating the headNode reference and incrementing the length variable.
		while (headNode!=null) {
			headNode=headNode.next;	// Moves the headNode to the next node in the linked list.
			length++;//Increments the length variable by 1.
		}
		//Checks if the length of the linked list is less than the target position. 
		//In this case, it means the target position is beyond the end of the linked list, so it returns head.
		//next to delete the first element.
		if(length<target) {
			return head=head.next;
		}
		//Calculates the index of the element to be deleted from the beginning.
		int deletingIndex=length-target;
		//Checks if the deleting index is 0, indicating that the first element needs to be deleted.
		//In this case, it updates the headNode to the next node and returns it.
		if(deletingIndex==0) {
			headNode=head.next;
			return headNode;
		}
		// Initializes a counter variable count to 1, which will be used to track the position of the current node while traversing the linked list.
		int count=1;
		headNode=head;//Resets the headNode reference to the beginning of the linked list.
		//Enters a loop that continues until the count reaches the deleting index.
		while (count<deletingIndex){
			headNode=headNode.next;// Moves the headNode to the next node in the linked list.
			count++;//Increments the count variable by 1.
		}
		//Creates a temporary reference tempNode to store the next-next node after the node to be deleted.
		ListNode tempNode=headNode.next.next;
		//Updates the next reference of the current node to skip the node to be deleted.
		headNode.next=tempNode;
		// Returns the modified head of the linked list.
		return head;
		
	}
	public static ListNode removeNthFromEnd(ListNode A, int B) {
        int n;
        ListNode node;
        if (A == null)
            return null;
        n = 0;
        node = A;
        // calculates the size of the linked list
        while (node != null) {
            n++;
            node = node.next;
        }
        if (B >= n) {
            return A.next;
        }
        node = A;
        for (int i = 0; i < n - B - 1; i++)
            node = node.next;
        node.next = node.next.next;
        return A;
    }

}
