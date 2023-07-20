package com.prudhvi.stack;



public class StackWithLinkedList {
	int val;
	static ListedList stackList;
	
	public StackWithLinkedList(int val) {
		this.val=val;
		StackWithLinkedList.stackList=null;
	}
	
	
	
	public void push(int ele){
		ListedList newElement=new ListedList(ele);
		
		
		
		
		
		
		
	}
	
	
	class ListedList{
		int val;
		ListedList node;
		public ListedList(int val) {
			this.val=val;
			this.node=null;
		}
		
		
	}

}
