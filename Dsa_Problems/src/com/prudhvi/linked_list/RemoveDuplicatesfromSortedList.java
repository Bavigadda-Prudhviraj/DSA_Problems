package com.prudhvi.linked_list;

public class RemoveDuplicatesfromSortedList {

	public static void main(String[] args) {
		int[] arr= {1,1,2,2,3,3,4,5,5,5,6,6,6,9,9,10,10,10};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		ListNode answer=removeDuplicates(head);
		answer.printLinkedList(answer);
	}
	
	public static ListNode removeDuplicates(ListNode head) {
		ListNode tempNode = head;
        while(tempNode.next!=null){
            if(tempNode.value==tempNode.next.value){
                tempNode.next=tempNode.next.next;
            }else{
                tempNode=tempNode.next;
            }
        }

        return head;
			
	}
	
	
}
		
	


