package com.prudhvi.linked_list;

public class ArrToLL {

	public static void main(String[] args) {
		int[] a= {1,2,3,4,5,6,7,8,9};
		ListNode aListNode=createLinkedListFromArray(a);
		printLinkedList(aListNode);
		int lenght=size(aListNode);
		System.out.println(lenght);
	}
	public static ListNode createLinkedListFromArray(int[] arr) {
		if(arr.length==0) {
			return null;
		}
		ListNode headNode= new ListNode(arr[0]);
		ListNode tempNode=headNode;
		for(int i=1;i<arr.length;i++) {
			tempNode.next=new ListNode(arr[i]);
			tempNode=tempNode.next;
		}
		return headNode;
	}
	public static void printLinkedList(ListNode head){
		if(head==null) {
			System.out.println("empty linked list");
		}
		ListNode headNode=head;
		while (headNode!=null) {
			System.out.print(headNode.value+" ");
			headNode=headNode.next;
		}
		System.out.println();
	}
	public static int size(ListNode head){
		int length=0;
		if(head==null) {
			return length;
		}
		else{
			ListNode tempNode=head;
			while(tempNode!=null) {
				tempNode=tempNode.next;
				length++;
			}
		}
		return length;
	}
	public static ListNode  deleteKthElementFromEnd(ListNode head,int target) {
		int length=0;
		if(head==null) 
			return head;
		ListNode headNode=head;
		while (headNode!=null) {
			headNode=headNode.next;	
			length++;
		}
		if(length<target) {
			System.out.println(" Index Out Of Bounds ");
			return null;
		}
		int deletingIndex=length-target;
		if(deletingIndex==0) {
			headNode=head.next;
			return headNode;
		}
		int count=1;
		headNode=head;
		while (count<deletingIndex){
			headNode=headNode.next;
			count++;
		}
		ListNode tempNode=headNode.next.next;
		headNode.next=tempNode;
		return head;
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
