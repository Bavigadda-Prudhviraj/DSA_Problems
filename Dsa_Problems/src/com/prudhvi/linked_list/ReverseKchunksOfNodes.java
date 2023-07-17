package com.prudhvi.linked_list;

public class ReverseKchunksOfNodes {
	/*
	Problem Description
	Given a singly linked list A and an integer B, reverse the nodes of the list B at a time and return the modified linked list.


	Problem Constraints
		1 <= |A| <= 103
		B always divides A
	
	Input
		A = [1, 2, 3, 4, 5, 6]
 		B = 2
 	output
 		For the first example, the list can be reversed in groups of 2.
    		[[1, 2], [3, 4], [5, 6]]
 		After reversing the K-linked list
    		[[2, 1], [4, 3], [6, 5]]
 	
	 */

	public static void main(String[] args) {
		int[] arr= {6,10,0,3,4,8};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		int index=3;
		ListNode answerNode=reverseKchunksOfNodes(head,index);
		ListNode.printLinkedList(answerNode);

	}
	/*
	The code reverses the linked list in chunks of size index by disconnecting nodes from the original list and building a reversed chunk. 
	It then connects the chunks together to form the final reversed linked list.

	Time complexity:
			Best case: O(1) when the linked list is empty or when the chunk size index is 0.
			Worst case: O(n) when the linked list contains n nodes. 
						The method needs to traverse through each node to reverse the chunks and recursively call the method on the remaining nodes.
	
	Space Complexity:
			The space complexity of the reverseKchunksOfNodes method is actually O(k), 
			where k represents the chunk size index. 
			In each recursive call, k nodes are stored in the temporary variable h2 to reverse the chunk. 
			As the recursion progresses, multiple instances of h2 are created on the stack, each storing k nodes. 
			Therefore, the space complexity is directly proportional to the chunk size.

			To be more precise, if the total number of nodes in the linked list is n and the chunk size is k, 
			the maximum number of recursive calls on the stack would be n/k. 
			Hence, the space complexity in the worst case is O(n/k * k) = O(n).

			In the best case, when the linked list is empty or the chunk size is 0, 
			the space complexity would be O(1) as no additional nodes are stored.
	 */
	private static ListNode reverseKchunksOfNodes(ListNode head, int index) {
		//Checks if the head node is null or if the index is 0. 
		//If either condition is true, it means there are no nodes or there are no chunks to reverse, so the method returns the head as it is.
		if(head==null || index==0){
			return head;
		}
		// Initializes a variable indexCount with the value of the index parameter, 
		//which keeps track of the number of nodes to reverse in each chunk.
		int indexCount=index;
		ListNode h1=head;//Creates a temporary node h1 and assigns it the value of the head node for traversal.
		ListNode h2=null;//Creates another temporary node h2 and initializes it as null. This node will store the reversed chunk of nodes.
		ListNode temp=head;//Creates a temporary node temp and assigns it the value of the head node for reference.
		ListNode h3=head;// Creates another temporary node h3 and assigns it the value of the head node for later connection purposes.
		//Enters a loop that continues as long as there are nodes remaining to be reversed (indexCount > 0) and there are still nodes in the linked list (h1 != null).
		while(indexCount>0 && h1!=null){
			//Assigns the current node h1 to the temp node for reference before moving to the next node.
			temp=h1;
			h1=h1.next;// Moves h1 to the next node in the original order.
			//Sets the next pointer of the temp node to null, disconnecting it from the original list temporarily.
			temp.next=null;
			//Sets the next pointer of the temp node to the reversed chunk h2, connecting it to the reversed list.
			temp.next=h2;
			//Moves h2 to the current node temp, adding it to the reversed chunk.
			h2=temp;
			indexCount--;
		}
		//Connects the end of the reversed chunk to the next chunk by recursively calling the reverseKchunksOfNodes method on the remaining nodes.
		h3.next=reverseKchunksOfNodes(h1,index);
		// Returns the head of the reversed linked list.
		return h2;
	}

}
