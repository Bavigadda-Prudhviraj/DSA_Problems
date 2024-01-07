package com.prudhvi.linked_list;
/*
	You are given the head of a singly linked-list. The list can be represented as:
		L0 → L1 → … → Ln - 1 → Ln
	Reorder the list to be on the following form:
		L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
	You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */
public class ReorderList {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		ListNode reorderList=reorderList(head);
		ListNode.printLinkedList(reorderList);

	}
    public static ListNode reorderList(ListNode A) {
        if(A.next==null || A==null){
            return A;
        }
        ListNode mid=findMid(A);
        ListNode list2=mid.next;
        mid.next=null;
        list2=reverseLinkedList(list2);
        ListNode temp1=A;
        ListNode temp2=list2;
        ListNode node1,node2;
        while(temp1!=null && temp2!=null){
            node1=temp1.next;
            node2=temp2.next;

            temp1.next=temp2;
            temp2.next=node1;

            temp1=node1;
            temp2=node2;
            
        }
        return A;

    }
    public static ListNode findMid(ListNode A){
        ListNode slow=A;
        ListNode fast=A;
        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        if(fast.next!=null){
            return slow.next;
        }
        return slow;
    }
    public static ListNode reverseLinkedList(ListNode head){
        ListNode current=head;
        ListNode reversedHead=null;
        while(current!=null){
            ListNode temp=current.next;
            current.next=reversedHead;
            reversedHead=current;
            current=temp;
        }
        return reversedHead;
        
    }

}
