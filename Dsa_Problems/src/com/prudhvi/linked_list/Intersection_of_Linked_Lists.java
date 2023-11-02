package com.prudhvi.linked_list;
/*
	Write a program to find the node at which the intersection of two singly linked lists, A and B, begins. For example, the following two linked lists:
	
	A:          a1 → a2
	                   ↘
	                     c1 → c2 → c3
	                   ↗
	B:     b1 → b2 → b3
	NOTE:
		If the two linked lists have no intersection at all, return null.
		The linked lists must retain their original structure after the function returns.
		You may assume there are no cycles anywhere in the entire linked structure.
		Your code should preferably run in O(n) time and use only O(1) memory.
		The custom input to be given is different than the one explained in the examples. Please be careful.
 */
public class Intersection_of_Linked_Lists {

	public static void main(String[] args) {
		// A = [1, 2, 3, 4, 5]
		int[] arr1= {1, 2, 3, 4, 5};
		ListNode head1=ArrToLL.createLinkedListFromArray(arr1);
		// B = [6, 3, 4, 5]
		ListNode head2=new ListNode(6);
		int count=1;
		ListNode tempListNode=head1;
		while(count!=3){
			tempListNode=tempListNode.next;	
			count++;
		}
		head2.next=tempListNode;	
		ListNode ansListNode=getIntersectionNode(head1, head2);
		
		
		ListNode.printLinkedList(ansListNode);
		
		

	}
	public static ListNode getIntersectionNode(ListNode A, ListNode B) {
		
		//Initialize two pointers, pt1 and pt2, to the heads of linked lists A and B, respectively.
	    ListNode pt1 = A;
	    ListNode pt2 = B;
	    //Check if either of the pointers is null (i.e., one of the linked lists is empty). 
	    if(pt1==null || pt2==null){
	    	//If either of them is null, there is no intersection, so return null.
	         return null;
	    } 
	    //Enter a while loop to traverse the linked lists.
	    while(pt1!=pt2){
	    	//Move pt1 and pt2 one step forward by setting them to their respective next nodes.
	         pt1 = pt1.next;
	         pt2 = pt2.next;
	         //Check if pt1 is equal to pt2. If they are equal, this means they have met at an intersection point.   
	         if(pt1==pt2){
	        	//In this case, return either pt1 or pt2 (they are the same at the intersection point).
	             return pt1;
	         }
	         //If pt1 reaches the end of list A (becomes null), set pt1 to the head of list B. 
	         if(pt1==null){
	        	 //This allows pt1 to continue traversing list B.
	             pt1 = B;
	         }
	         //If pt2 reaches the end of list B (becomes null), set pt2 to the head of list A. 
	         if(pt2==null){
	        	 //This allows pt2 to continue traversing list A.
	             pt2 = A;
	         } 
	     }
	    ////Continue the loop until an intersection is found, or until both pointers reach the end of both linked lists, indicating that there is no intersection.
	     return pt1;
	}
}
