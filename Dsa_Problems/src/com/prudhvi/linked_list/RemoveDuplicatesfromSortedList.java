package com.prudhvi.linked_list;

public class RemoveDuplicatesfromSortedList {

	public static void main(String[] args) {
		int[] arr= {1,1,1,1,2,2,2,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		ListNode answer=removeDuplicates(head);
		answer.printLinkedList(answer);
	}
	public static ListNode removeDuplicates(ListNode head) {
		ListNode temp1=head;
		ListNode temp2=head.next;
		while(temp2.next!=null){
			if(temp1.value==temp2.value) {
				int i=1;
				int val=temp1.value;
				while(val==temp2.next.value){
					temp2=temp2.next;	
				}
				//System.out.println("i "+i+" temp1:"+temp1.value+" temp2:"+temp2.value);
			}
			if(temp2==null) {
				break;
			}
			System.out.println("after temp1:"+temp1.value+" temp2:"+temp2.value);
			temp1=temp1.next;
			temp2=temp2.next;
			}
		return head;
			
	}
	
	}
		
	


