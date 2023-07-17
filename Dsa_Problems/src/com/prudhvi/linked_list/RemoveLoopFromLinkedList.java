package com.prudhvi.linked_list;

public class RemoveLoopFromLinkedList {
	/*
	Problem Description
		You are given a linked list that contains a loop.
		You need to find the node, which creates a loop and break it by making the node point to NULL.

	Problem Constraints
		1 <= number of nodes <= 1000
	 */

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		//creating the loop node,check if is it loop link or not by un-commenting the print statement in the list link loop method
		ListNode loopNode=RemoveLoopFromLinkedList.ListLinkLoop(arr);
		//passing that loopNode node to remove loopNode method 
		ListNode headNode=RemoveLink(loopNode);
		ListNode.printLinkedList(headNode);
		

	}
	/*
	The given code is used to detect and remove a loop in a linked list
	
	Time Complexity:
			The time complexity of the code is O(n), where n is the number of nodes in the linked list.
			 This is because we traverse the linked list using two pointers until we detect a loop or reach the end of the list.
	
	Space Complexity:
			The space complexity of the code is O(1) since we are using a constant amount of additional space to store the pointers and boolean variable.
	 
	 */
	public static ListNode RemoveLink(ListNode head) {
		//Create two pointers, fastPointerNode and slowPointerNode, and initialize them with the head of the linked list.
        ListNode fastPointerNode=head;
        ListNode slowPointernode=head;
        //Create a boolean variable loopExist and initialize it as false.
        //This variable will be used to determine if a loop exists in the linked list.
        boolean loopExist=false;
        //while loop to traverse the linked list. The loop condition checks if fastPointerNode.next and fastPointerNode.next.next are not null.
        while(fastPointerNode.next!=null && fastPointerNode.next.next!=null){
        	//move slowPointerNode one step ahead and fastPointerNode two steps ahead.
            slowPointernode=slowPointernode.next;
            fastPointerNode=fastPointerNode.next.next;
            //Check if slowPointerNode is equal to fastPointerNode. 
            //If they are equal, it means a loop exists in the linked list. 
            if(slowPointernode==fastPointerNode){
            	// //Set loopExist to true and break out of the loop.
                loopExist=true;
                break;
            }
        }
        // if loopExist is false, it means a looping was not there so we returned in the head.
        if(!loopExist) {
        	return head;
        }
        ListNode midPointerNode=head;//new pointer midPointerNode and set it to the head of the linked list.
        //while loop to find the point of intersection of the two pointers (midPointerNode and slowPointerNode).
        while(midPointerNode!=slowPointernode){
        	//move both pointers one step at a time until they meet at the point of intersection.
            midPointerNode=midPointerNode.next;
            slowPointernode=slowPointernode.next;
            //Once the intersection point is found, it means that this node is the start of the loop in the linked list.
        }
        // temporary node tempNode and set it to slowPointerNode.
        ListNode tempNode=slowPointernode;
        //while loop to traverse the loop and find the last node before the intersection point.
        while(tempNode.next!=midPointerNode){
        	//Inside the loop, move tempNode one step ahead until it reaches the node just before the intersection point.
            tempNode=tempNode.next;
        }
        //Set the next pointer of tempNode to null, effectively removing the loop in the linked list.
        tempNode.next=null;
        //Finally, return the head of the modified linked list.
        return head;
    }
	public static ListNode ListLinkLoop(int[] arr){
		//Create a new ListNode headNode and initialize it with the first element of the given array.
		ListNode headNode=new ListNode(arr[0]);
		//Create a temporary ListNode tempNode and set it equal to headNode.
		ListNode tempNode=headNode;
		//Create a ListNode linkListNode and initialize it as null. This variable will store the node that will create the loop in the linked list.
		ListNode linkListNode=null;
		//Use a for loop to iterate over the remaining elements of the array starting from index 1.
		for(int i=1;i<arr.length;i++) {
			//Inside the loop, create a new ListNode with the current element of the array and assign it to tempNode.next. Move tempNode to the newly created node.
			tempNode.next=new ListNode(arr[i]);
			tempNode=tempNode.next;
			//Check if the current index is 5. If it is, set linkListNode equal to tempNode. 
			//This marks the node at index 5 as the node where the loop will start.
			if(i==5) {
				linkListNode=tempNode;
			}
			
		}
		//After the loop, set the next pointer of tempNode (which currently points to the last node) to linkListNode, 
		//creating a loop in the linked list.
		tempNode.next=linkListNode;
		//ListNode.printLinkedList(headNode);
		return headNode;//Return the headNode of the linked list.
	}

	

}
