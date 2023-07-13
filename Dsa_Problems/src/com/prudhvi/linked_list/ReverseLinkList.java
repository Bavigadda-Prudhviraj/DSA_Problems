package com.prudhvi.linked_list;

public class ReverseLinkList {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		ListNode revesedLL=reverseLinkedList(head);
		ArrToLL.printLinkedList(revesedLL);

	}
	private static ListNode reverseLinkedList(ListNode head) {
		ListNode previousNode=null;
		ListNode presentNode=head;
		while (presentNode.next!=null){
			previousNode=presentNode.next;
			presentNode=previousNode.next;

			
		}
		
		return presentNode;
		
	}

}
