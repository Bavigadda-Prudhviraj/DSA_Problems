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
		ListNode temp2=head;
		while(temp2.next!=null){
			if(temp1.value==temp2.next.value) {
				int val=temp1.value;
				int i=1;
				while(val==temp2.next.value){
					if(temp2.next!=null) {
						temp2=temp2.next;	
						System.out.println("i: "+i);
						i++;
						
					}
					
				}
				
				
				
			}
			if(temp2.next!=null) {
				temp1.next=temp2.next;
				
			}
			else if(temp2.next==null) {
				System.out.println("666666666666666666666");
				temp1.next=temp2;
				return head;
			}
			//System.out.println(temp1.value+" "+temp2.value);
			
			System.out.println("after temp1:"+temp1.value+" temp2:"+temp2.value);
			temp1=temp2;
			if(temp2.next==null) {
				temp1=temp2;
				break;
			}
			temp1=temp1.next;
			temp2=temp2.next;
			head.printLinkedList(head);
			//temp2=temp2.next;
			}
		return head;
			
	}
	
	}
		
	


