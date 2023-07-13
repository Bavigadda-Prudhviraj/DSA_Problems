package com.prudhvi.linked_list;

public class SearchKinLL {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		int target=100;
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		if(searchForTarget(head,target)) {
			ArrToLL.printLinkedList(head);
			
		}
		else {
			System.out.println("Linked list is empty or does not contain any data matching the given values");
		}
		
		
	}
	public static boolean searchForTarget(ListNode head,int target){
		while(head!=null){
			if(head.value==target) {
				return true;
			}
			head=head.next;
		}
		return false;
		
		
	}

}
