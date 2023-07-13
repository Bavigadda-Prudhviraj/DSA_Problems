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
}

