package com.prudhvi.linked_list;

public class ListNode {
	public int value;
	public ListNode next;
	
	public  ListNode(int value) {
		this.value=value;
		this.next=null;
	}
	public ListNode(ListNode other) {
        this.value = other.value;
    }
	private static boolean insertAt(ListNode head,int index,int element){
		ListNode tempNode=head;
		ListNode newNode;
		if(index==1 || index<1) {
			 newNode=new ListNode(element);
			newNode.next=tempNode;
			return true;
		}
		for(int i=1;i<index-1;++i) {
			if(tempNode.next==null) {
				ListNode newnNode = new ListNode(element);
				tempNode.next=newnNode;
				return true;
			}
			tempNode=tempNode.next;
		}
		newNode=new ListNode(element);
		newNode.next=tempNode.next;
		tempNode.next=newNode;	
		return true;
	}
	private static void printLinkedList(ListNode head){
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
	public static boolean searchForTarget(ListNode head,int target){
		while(head!=null){
			if(head.value==target) {
				return true;
			}
			head=head.next;
		}
		return false;
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
	public static ListNode deleteEle(ListNode head,int ele){
		if(head==null) {
			return head;
		}
		ListNode tempNode=head;
		while(tempNode.next!=null){
			if(tempNode.next.value==ele){
				tempNode.next=tempNode.next.next;
				break;
			}
		}
		return head;
		
	}
}

