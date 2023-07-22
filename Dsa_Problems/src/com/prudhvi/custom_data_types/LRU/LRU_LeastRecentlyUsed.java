package com.prudhvi.custom_data_types.LRU;

import java.util.HashMap;
import java.util.Map;

/*
 	The provided code implements a Least Recently Used (LRU) cache using a HashMap and a doubly linked list. 
 	The cache has a capacity limit, and when the cache reaches its capacity, the least recently used element is evicted.
 	
 	Time Complexity:
			Both the get and set methods have a time complexity of O(1) since HashMap operations like get, put, and remove are generally constant time operations.


	Space Complexity:
			The space complexity of the LRU cache is O(capacity) because it can store up to capacity key-value pairs in the HashMaps and linked list.
			
			Best Case Scenario:
					The best case scenario is when all operations (get and set) take constant time, which is O(1). 
					This occurs when the operations always involve the most recently accessed items.

			Worst Case Scenario:
					The worst case scenario is when all operations involve accessing or evicting the least recently used items. 
					In this case, both get and set operations may involve traversing the entire linked list, leading to a time complexity of O(n), where n is the number of elements in the cache (capacity). 
					However, since n is limited by the cache capacity, the worst-case time complexity remains O(capacity).
 */
/*
	The LRU_LeastRecentlyUsed class is the LRU cache implementation. 
	It has two HashMaps: keyValue to store key-value pairs, 
	and keyReferance to store references to the corresponding linked list nodes.
 */
public class LRU_LeastRecentlyUsed {
	int capacity;//capacity is the maximum number of elements the cache can hold.
	int size;//size is used to keep track of the current number of elements in the cache.
	//keyValue is a HashMap that stores key-value pairs. 
	//The key represents the key of the cache, and the value represents the corresponding value.
	HashMap<Integer, Integer> keyValue=new HashMap<>();
	//keyReferance is another HashMap that stores references to the corresponding linked list nodes for each key.
	HashMap<Integer, DoubleLinkList> keyReferance=new HashMap<>();
	//head and tail are dummy nodes of the doubly linked list that represent the start and end of the list.
	DoubleLinkList head=new DoubleLinkList(-1, -1);
	DoubleLinkList tail=new DoubleLinkList(-1, -1);
	
  
	//The constructor is used to initialize the LRU cache with the given capacity.
    public LRU_LeastRecentlyUsed(int capacity) {
    	this.capacity=capacity;//this.capacity is set to the provided capacity.
    	size=0;   //size is initialized to 0 as the cache is initially empty.
    	//head.next is set to tail, and tail.prev is set to head to create an empty doubly linked list.
    	head.next=tail;
    	tail.prev=head;
    }
    //The get method is used to retrieve the value associated with a given key from the cache.
    public int get(int key) {
    	//It checks if the keyReferance HashMap contains the given key. If it does, it means the key is present in the cache.
    	if(keyReferance.containsKey(key)) {
    		int val=keyValue.get(key);//If the key is present, it retrieves the corresponding value from the keyValue HashMap.
    		removeFromList(key, keyReferance, keyValue);//It then removes the node associated with the key from its current position in the linked list using the removeFromList method.
    		addElement(key, val, keyReferance, keyValue, tail);//The node is then added to the end of the linked list using the addElement method, marking it as the most recently used item.
    		return keyValue.get(key);//Finally, it returns the value associated with the key.
    	}
    	//if key is not there means it will return -1
    	else {
			return -1;
		}  
    }
    //The set method is used to add or update a key-value pair in the cache.
    public void set(int key, int value) {
    	if(!keyReferance.containsKey(key)) {//If the key is not present
    		//it checks if the cache has reached its capacity
    		if(size==capacity) {
    			// If the cache is full, it removes the least recently used element (the one at the front of the linked list) using the removeFromList method
    			removeFromList(head.next.key, keyReferance, keyValue);
    			//It then adds the new element to the end of the linked list using the addElement method, marking it as the most recently used item.
    			addElement(key, value, keyReferance, keyValue, tail);
    		}
    		//If the cache is not full, it simply adds the new element to the end of the linked list using the addElement method and increments the size.
    		else {
    			addElement(key, value, keyReferance, keyValue, tail);
    			size++;	
			}
    	}
    	//It checks if the keyReferance HashMap contains the given key. 
    	//If it does, it means the key is already present in the cache and needs to be updated.
    	else {
    		//it removes the node associated with the key from its current position in the linked list using the removeFromList method.
			removeFromList(key, keyReferance, keyValue);
			//The node is then added to the end of the linked list using the addElement method, marking it as the most recently used item.
			addElement(key, value, keyReferance, keyValue, tail);
		}
    }
    //The removeFromList method is a helper function used to remove a node from the linked list.
    //It takes the key of the node to be removed, along with the keyReferance and keyValue HashMaps.
    public static void removeFromList(int key,Map< Integer, DoubleLinkList> keyReferance,Map<Integer, Integer> keyValue){
    	DoubleLinkList removeElement=keyReferance.get(key);
    	//It then updates the references of the next and previous nodes to bypass the node to be removed, effectively removing it from the linked list.
    	removeElement.next.prev=removeElement.prev;
    	removeElement.prev.next=removeElement.next;
    	keyReferance.remove(key);//It gets the node to be removed using the keyReferance HashMap.
    	keyValue.remove(key);//The node is also removed from both keyReferance and keyValue HashMaps.
    }
    
    //The addElement method is a helper function used to add a new node to the end of the linked list.
    //It takes the key, value, keyReferance, keyValue, and the tail node as arguments.It creates a new DoubleLinkList node with the given key and value.
    public static void addElement(int key,int value,Map< Integer, DoubleLinkList> keyReferance,Map<Integer, Integer> keyValue,DoubleLinkList tail) {
    	//It then updates the references of the next and previous nodes to insert the new node at the end of the linked list, just before the tail.
    	DoubleLinkList newElement=new DoubleLinkList(key, value);
    	tail.prev.next=newElement;
    	newElement.next=tail;
    	newElement.prev=tail.prev;
    	tail.prev=newElement;
    	//The new node is added to both the keyReferance and keyValue HashMaps to keep track of it.
    	keyReferance.put(key, newElement);
    	keyValue.put(key, value);
    	
    }
}


class DoubleLinkList {
    int key;
    int value;
    DoubleLinkList next;
    DoubleLinkList prev;
    public DoubleLinkList(int key, int value){
        this.key = key;
        this.value = value;
        this.next=null;
        this.prev=null;
    }
}

