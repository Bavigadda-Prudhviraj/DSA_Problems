package com.prudhvi.custom_data_types.LRU;

import java.util.HashMap;

import javax.print.attribute.Size2DSyntax;


//class DoubleLinkList {
//    int key;
//    int value;
//    DoubleLinkList next;
//    DoubleLinkList prev;
//    public DoubleLinkList(int key, int value){
//        this.key = key;
//        this.value = value;
//    }
//}

public class Solution {
	int key;
	int value;
	int capacity;
	Solution prevoius;
	Solution next;
	HashMap<Integer, Integer> inputData;
	HashMap<Integer,Solution> nodeAddresses;
	Solution head=new Solution(-1);
    Solution tail=new Solution(-1);
    	
	public  Solution(int key,int value) {
		this.key=key;
		this.value=value;
		this.prevoius=null;
		this.next=null;
	}
	public Solution(int capacity) {
		this.capacity=capacity;
		head.next=tail;
	    prevoius.prevoius=head;
		inputData=new HashMap<>();
		nodeAddresses=new HashMap<>();
		nodeAddresses.put(-1, head);
		nodeAddresses.put(-1, tail); 
		
	}
	public int get(int key) {
		if(nodeAddresses.containsKey(key)) {
			return inputData.get(key);
		 }
		 else {
			return -1;
		 }
		        
	}
	public void set(int key, int value) {
		
		if(nodeAddresses.containsKey(key)) {
		    RemoveElement(key,value);
		    addElementAtLast(key,value);
		}
		else {
			
			if(nodeAddresses.size()==capacity) {
				RemoveElement(key, value);
				addElementAtLast(key, value);
			}
			else {
				addElementAtLast(key, value);
			}
		}        
	}
	public void addElementAtLast(int key2, int value2) {
		inputData.put(key2,value2);
		Solution newElement=new Solution(key2);
		newElement.next=tail;
		newElement.prevoius=tail.prevoius;
		tail.prevoius.next=newElement;
		tail.prevoius=newElement;
		nodeAddresses.put(key2, newElement);
	}
	public void RemoveElement(int key2, int value2) {
		Solution element=nodeAddresses.get(key2);
		nodeAddresses.remove(key2);
		inputData.remove(key2);
		element.prevoius.next=element.next;
		element.next.prevoius=element.prevoius;
				
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}




