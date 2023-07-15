package com.prudhvi.linked_list;

public class MiddleElementOfLinkedList {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		int mid=findMidValues(head);
		System.out.println(mid);

	}
	/*
	The code finds the middle value of a linked list by using two pointers, t1 and t2. 
	The t1 pointer moves one node at a time, while the t2 pointer moves two nodes at a time. 
	By the time t2 reaches the end of the linked list, t1 will be pointing to the middle node 
	if the linked list has an odd number of nodes, or the node just before the middle if the linked list has an even number of nodes.


	Time complexity:
			Best case: O(1) when the linked list is empty or contains only one node.
			Worst case: O(n) when the linked list contains n nodes. T
						he method needs to traverse through half of the linked list to find the middle value.

	Space complexity: 
			O(1) as it uses a constant amount of additional space for the temporary nodes t1 and t2.
	 */
	public static int findMidValues(ListNode A) {
		//Creates a temporary node slowNode and assigns it the value of the head node A for traversal.
        ListNode slowNode=A;
        //Creates another temporary node fastNode and assigns it the value of the head node A for faster traversal by moving two nodes at a time.
        ListNode fastNode=A;
        //Loops until fastNode reaches the end of the linked list or the second last node.
        while(fastNode.next!=null && fastNode.next.next!=null){
        	//Moves slowNode to the next node, which represents a slower traversal by moving one node at a time.
            slowNode=slowNode.next;
            // Moves fastNode to the next two nodes, which represents a faster traversal by moving two nodes at a time.
            fastNode=fastNode.next.next;
        }
        //Checks if fastNode has reached the end of the linked list, indicating that the linked list has an odd number of nodes.
        if(fastNode.next==null){
        	// Retrieves the value of the middle node and assigns it to the variable val.
            int val=slowNode.value;
            return val;//Returns the value of the middle node as an integer.
        }
        //Executes if fastNode has not reached the end of the linked list, indicating that the linked list has an even number of nodes.
        else{
        	//Retrieves the value of the next node after the middle node and assigns it to the variable val.
            int val=slowNode.next.value;
            return val;//Returns the value of the next node after the middle node as an integer.
        }
	}

}
