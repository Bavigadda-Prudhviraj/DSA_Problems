package com.prudhvi.linked_list;

public class AccessKthElement {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		int target=10;
		int answerNode=accessKthElement(head,target);
		System.out.println(answerNode);

	}
	/*
	This code defines a method accessKthElement that takes a ListNode object head and an integer target as input parameters. 
	It aims to access the value of the k-th element in the linked list.
	
	Time Complexity:
		Best Case: O(1) - If the target position is 1, it can directly access the value of the head node in constant time.
		Worst Case: O(N) - If the target position is at the end of the linked list, it needs to iterate through all N nodes to reach the target position.
	
	Space Complexity:
		O(1) - The code uses a constant amount of additional space, regardless of the size of the linked list.
	 */
	public static int accessKthElement(ListNode head,int target){
		//The method starts by checking if the head node is null. If it is, 
		//it means the linked list is empty, so it returns 0.
		if(head==null) {
			return 0;
		}
		//It creates a reference headListNode and initializes it with the head node.
		ListNode headListNode=head;
		//It initializes a counter i with 1 to keep track of the current position in the linked list.
		int i=1;
		//Inside the while loop, it iterates until i reaches the target position.
		while (i<target) {	
			// Checks if the next node of the current node is null, indicating that the target is not there  in the given linked list. 
			//In this case, it returns a large negative value (-Integer.MAX_VALUE) as an indicator.
			if(headListNode.next==null) {
				System.out.println("Element is not there in the given Linked List");
				return -Integer.MAX_VALUE;
			}
			//If there is a next node, it updates headListNode to the next node and increments the counter i.
			headListNode=headListNode.next;
			i++;
		}
		//After the while loop completes, it means the desired position has been reached, 
		//so it returns the value of the node at the target position (headListNode.value).
		return headListNode.value;
		
	}

}

/*









Time Complexity:

Best Case: O(1) - If the target position is 1, it can directly access the value of the head node in constant time.
Worst Case: O(N) - If the target position is at the end of the linked list, it needs to iterate through all N nodes to reach the target position.
Space Complexity:

O(1) - The code uses a constant amount of additional space, regardless of the size of the linked list.
*/