package com.prudhvi.custom_data_types.LRU;

import java.util.HashMap;

public class LRU_Cache_LeetCode {

	public static void main(String[] args) {
		/*
		Input
["LRUCache", "put",   "put", "get", "put", "get", "put", "get", "get", "get"]
[[2],        [1, 1], [2, 2], [1],   [3, 3], [2],  [4, 4], [1],  [3],  [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]
		 */
		LRUCache lru=new LRUCache(2);
		lru.put(1,1);
		lru.put(2,3);
		System.out.println(lru.get(1));
		lru.put(3,3);
		System.out.println(lru.get(2));
		lru.put(4,4);
		System.out.println(lru.get(1));
		System.out.println(lru.get(3));
		System.out.println(lru.get(4));

	}

}
class LRUCache {
    HashMap<Integer,DoublyLinkedList> map=new HashMap<>();
    DoublyLinkedList head=new DoublyLinkedList(-1,-1);
    DoublyLinkedList tail=new DoublyLinkedList(-1,-1);
    int capacity;

    public LRUCache(int capacity) {
        this.capacity=capacity;
        head.next=tail;
        tail.prev=head; 
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        DoublyLinkedList val=map.get(key);
        removeElement(map.get(key),map);
        addFast(key,val.val,map,tail);
        return val.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            DoublyLinkedList val=map.get(key);
            removeElement(map.get(key),map);
            addFast(val.key,value,map,tail);
        }else{
            if(capacity!=0){
                addFast(key,value,map,tail);
                capacity--;
            }
            else{
                removeLast(head,map);
                addFast(key,value,map,tail);
            }
        }
    }
    public static void removeLast(DoublyLinkedList head,HashMap<Integer,DoublyLinkedList> map){
        DoublyLinkedList temp=head.next;
        head.next=temp.next;
        temp.next.prev=temp.prev;
        map.remove(temp.key);
    } 
    public static void removeElement(DoublyLinkedList list,HashMap<Integer,DoublyLinkedList> map){
        DoublyLinkedList node=list;
        node.next.prev=node.prev;
        node.prev.next=node.next;
        map.remove(node.key);
    }
    public void addFast(int key,int val,HashMap<Integer,DoublyLinkedList> map,DoublyLinkedList tail){
        DoublyLinkedList node=new DoublyLinkedList(key,val);
        map.put(key,node);
        DoublyLinkedList previous=tail.prev;
        previous.next=node;
        node.next=tail;
        tail.prev=node;
        node.prev=previous;
    }
}
class DoublyLinkedList{
    int key;
    int val;
    DoublyLinkedList next;
    DoublyLinkedList prev;
    public DoublyLinkedList(int key,int val){
        this.key=key;
        this.val=val;
        this.next=null;
        this.prev=null;
    }
}
