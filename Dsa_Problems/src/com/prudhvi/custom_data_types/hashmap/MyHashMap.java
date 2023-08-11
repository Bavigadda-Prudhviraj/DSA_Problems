package com.prudhvi.custom_data_types.hashmap;

import java.util.LinkedList;


public class MyHashMap <K,V>{
	static final int CAPACITY=4;
	static final float LOAD_FACTOR=0.75f;
	LinkedList<Node>[] bucket;
	int size;
	private void initBuckets(int capacity){		 // Create a new array of linked lists
		bucket=new LinkedList[capacity];
		for(int i=0;i<bucket.length;i++) {
			bucket[i]=new LinkedList<>();		 // Initialize each bucket as an empty linked list
		}
	}
	private int hashFunction(K key) {
		int hashCode=key.hashCode();			 // Calculate the hash code of the key
		return Math.abs(hashCode)%bucket.length; // Map the hash code to an index within the buckets
	}
	private int searchKey(K key,LinkedList<Node> linkedList) {
		for(int i=0;i<linkedList.size();i++) {
			if(linkedList.get(i).key==key) {
				return i;						// Return the index if the key is found in the linked list
			}
		}
		return -1;								// Return -1 if the key is not found in the linked list
	}
	private void reHashFunction() {
		LinkedList<Node>[] oldBucket=bucket;	// Store the reference to the current buckets
		initBuckets(bucket.length*2);			// Initialize new buckets with double the capacity
		size=0;									// Reset the size counter
		for(LinkedList<Node> node:oldBucket) {	// Iterate through each old linked list
			for(Node ele:node) {				// Iterate through each node in the linked list
				put(ele.key,ele.value);			// Re-insert the key-value pair into the new buckets
			}
		}	
	}
	public MyHashMap() {
		initBuckets(CAPACITY);
	}
	class Node{
		K key;
		V value;
		Node(K key,V value){
			this.key=key;
			this.value=value;
		}
	}
	public void put(K key,V value){
		int bucketIndex=hashFunction(key);						// Calculate the index using the hash function
		LinkedList<Node> linkedListAtIndex=bucket[bucketIndex];	// Get the linked list at the calculated index
		int nodeIndex=searchKey(key,linkedListAtIndex);			// Search for the key in the linked list
		if(nodeIndex==-1) {
			Node newNode=new Node(key, value);					// Create a new node with the key-value pair
			linkedListAtIndex.add(newNode);						// Add the new node to the linked list
			size++; 											// Increment the size counter
		}else {
			linkedListAtIndex.get(nodeIndex).value=value;		// Update the value if the key already exists
		}
		if(size>=bucket.length*LOAD_FACTOR) {
			reHashFunction();									// Trigger rehashing if load factor is exceeded
		}
	}
	public V get(K key) {
		int bucketIndex=hashFunction(key);						// Calculate the index using the hash function
		LinkedList<Node> linkedListIndex=bucket[bucketIndex];	// Get the linked list at the calculated index
		int nodeIndex=searchKey(key, linkedListIndex); 			// Search for the key in the linked list
		if(nodeIndex!=-1) {
			return linkedListIndex.get(nodeIndex).value;		// Return the value if the key is found
		}
		return null;											// Return null if the key is not found
	}
	public V remove(K key) {
		int bucketIndex=hashFunction(key); 						// Calculate the index using the hash function
		LinkedList<Node> linkedListIndex=bucket[bucketIndex];	// Get the linked list at the calculated index
		int nodeIndex=searchKey(key, linkedListIndex);			// Search for the key in the linked list
		if(nodeIndex==-1) {
			return null;										// Return null if the key is not found
		}
		else {
			V removedElement=linkedListIndex.get(nodeIndex).value;// Get the value to be removed
			linkedListIndex.remove(nodeIndex);					  // Remove the node from the linked list
			size--;												  // Decrement the size counter
			return removedElement;								  // Return the removed value
		}
	}
	public boolean containsKey(K key){
		int index=hashFunction(key);							// Calculate the index using the hash function
		LinkedList<Node> linkedList=bucket[index];   			// Get the linked list at the calculated index
		int keyIndex=searchKey(key, linkedList);				// Search for the key in the linked list
		if(keyIndex==-1) {										// Return true if the key is found, false otherwise
			return false;
		}
		return true;
	}
	public int size() {
		return size;
	}
}
/*
	Time Complexity:
			Insertion (put): Amortized O(1)
			Lookup (get): Amortized O(1)
			Deletion (remove): Amortized O(1)
	Space Complexity:
			The space complexity depends on the number of elements stored in the hash map, 
			which is O(N), where N is the number of elements in the map.

	In summary, the provided code implements a basic hash map with chaining for handling collisions. 
	It maintains an array of linked lists to store key-value pairs and uses a hash function to distribute keys across the buckets. 
	The hash map automatically resizes when the load factor is exceeded, ensuring efficient operations.
	
	Method with Explanation:
	 		initBuckets(integer capacity);
	 					The initBuckets method is a private utility function within the MyHashMap class that is responsible for initializing the array of linked lists, also known as the "buckets," used for storing key-value pairs in the hash map. 
						This method is typically called during the creation of a new hash map or when the hash map needs to be resized (due to rehashing) to accommodate more elements efficiently.
	 					Explanation of each step:
						1.bucket = new LinkedList[capacity];
									This line creates a new array called bucket with a specified capacity. 
									The capacity is determined by the parameter capacity passed to the method. 
									Each element of this array will hold a reference to a linked list that will store key-value pairs.
						2.Loop through each index in the bucket array:
									The loop iterates through each index from 0 to bucket.length - 1, which corresponds to each bucket in the array.
						3.bucket[i] = new LinkedList<>();
									For each index i, this line creates a new instance of the LinkedList class and assigns it to the i-th element of the bucket array. 
									This effectively initializes each bucket as an empty linked list, ready to store key-value pairs.
					The purpose of the initBuckets method is to set up the underlying data structure that will hold the key-value pairs. 
					By creating an array of linked lists, the hash map is prepared to efficiently manage collisions (when multiple keys hash to the same index) by chaining multiple key-value pairs together within each linked list.
			hashFunction(key):
						It is responsible for generating a hash code for a given key and then mapping that hash code to an index within the array of linked lists (buckets) used in the hash map. 
						The purpose of the hash function is to efficiently distribute keys across the available buckets to minimize collisions and provide fast access to stored values.
						Note:It's important to note that while a good hash function can reduce collisions, it may not completely eliminate them. 
						     This is why techniques like separate chaining (using linked lists) are employed to handle collisions that do occur.
						Explanation of each step:
						1.int hashCode = key.hashCode();
									This line calculates the hash code of the given key using the hashCode method provided by the Java language. 
									The hashCode method is usually overridden in classes that need custom hashing behavior. The hash code is an integer representation of the key's value, used to uniquely identify the key within the hash map.
						2.return Math.abs(hashCode) % bucket.length;
									i.After obtaining the hash code, this line maps the hash code to an index within the array of linked lists (bucket) using the modulo operation (%). 
									  This operation calculates the remainder of the division between the hash code and the length of the bucket array. 
									  This ensures that the resulting index falls within the range of valid indices for the array.
								   ii.The Math.abs function is used to ensure that the hash code is treated as a positive value, as the modulo operation should not be affected by the sign of the hash code.
			searchKey(key):
						This method is called when performing operations like put, get, remove, and containsKey in the hash map to locate a particular key and determine its position within the linked list. 
						The purpose of searchKey is to assist in finding the appropriate node associated with a given key in the hash map's data structure.
						Explanation of each step:
						1.private int searchKey(K key, LinkedList<Node> linkedList) { ... }
									This method takes two parameters: key, which is the key to be searched for, and linkedList, which is the linked list within which the search is performed.
						2.for (int i = 0; i < linkedList.size(); i++) { ... }
									This loop iterates through each element (node) in the linked list. 
									The loop variable i represents the index within the linked list.
						3.if (linkedList.get(i).key == key) { ... }
									Inside the loop, this conditional statement checks if the key of the current node (linkedList.get(i).key) matches the target key that is being searched for. 
									If the keys match, it means the key has been found in the linked list.
						4.return i;
									If a matching key is found, the loop returns the index i indicating the position of the key within the linked list.
						5.return -1;
									If the loop completes without finding a matching key, it returns -1 to indicate that the key was not found in the linked list.
			reHashFunction(k Key);
						Explanation of each step:
						1.LinkedList<Node>[] oldBucket = bucket;
									This line creates a reference oldBucket pointing to the current array of linked lists (bucket) before rehashing. 
									This reference is used to iterate through the existing elements and transfer them to the new buckets.
						2.initBuckets(bucket.length * 2);
									This line initializes new buckets with a capacity that is twice the size of the current capacity. 
									Doubling the capacity helps maintain a low load factor, reducing the likelihood of collisions and maintaining efficient performance.
						3.size = 0;
									This line resets the size counter to zero. 
									The size will be incremented as key-value pairs are re-inserted into the new buckets.
						4.Loop through each linked list in the oldBucket:
									The outer loop iterates through each linked list in the old buckets.
						5.Loop through each node in the linked list:
									The inner loop iterates through each node within the linked list.
						6.put(ele.key, ele.value);
									This line calls the put method to re-insert the key-value pair from the old linked list into the new buckets. 
									This step ensures that each key-value pair is redistributed to its appropriate new position based on the updated hash code and capacity.
				By rehashing, the hash map can effectively handle changes in load and maintain a balanced distribution of key-value pairs, thereby improving the overall efficiency and performance of operations like put, get, remove, and containsKey.
			put(key,value):
						Explanation of each step:
						1.int bucketIndex = hashFunction(key);
									This line calculates the index where the key should be placed within the array of linked lists using the hashFunction. 
									It maps the key to an index based on its hash code.
						2.LinkedList<Node> linkedListAtIndex = bucket[bucketIndex];
									This line retrieves the linked list at the calculated bucketIndex within the array of linked lists. 
									It identifies the specific linked list where the key-value pair should be inserted or updated.
						3.int nodeIndex = searchKey(key, linkedListAtIndex);
									This line searches for the key within the linked list at the calculated index. 
									The searchKey method is used to find the position of the key within the linked list, or it returns -1 if the key is not present.
						if (nodeIndex == -1) { ... }
									This conditional block is executed when the key is not found in the linked list. 
									In this case, a new node containing the given key and value is created and added to the linked list. 
									The size counter is incremented to reflect the addition of a new key-value pair.
						4.else { ... }
									If the key is found in the linked list (i.e., nodeIndex is not -1), this block is executed. 
									It updates the value associated with the existing key to the new value.
						5.if (size >= bucket.length * LOAD_FACTOR) { ... }
									This conditional block checks if the size of the hash map has exceeded the product of the array length and the load factor threshold. 
									If it has, it triggers the reHashFunction to resize and rehash the hash map to maintain an efficient load factor.
					It is used to add or update a key-value pair in the hash map. 
					It calculates the hash code of the given key, determines the appropriate index within the array of linked lists (buckets), and performs the necessary actions to insert or update the key-value pair. 
					If the load factor of the hash map exceeds a predefined threshold, it triggers a rehashing process to maintain the efficiency of the hash map.
			get(key):
						The get method in the provided code is used to retrieve the value associated with a given key from the hash map. 
						It calculates the hash code of the given key to determine the index within the array of linked lists (buckets) where the key is likely to be located. 
						Then, it searches the linked list at that index to find the specific key and return its associated value.
						Explanation of each step:
						1.int bucketIndex = hashFunction(key);
									This line calculates the index where the key should be located within the array of linked lists using the hashFunction. 
									It maps the key to an index based on its hash code.
						2.LinkedList<Node> linkedListIndex = bucket[bucketIndex];
									This line retrieves the linked list at the calculated bucketIndex within the array of linked lists. 
									It identifies the specific linked list where the key might be found.
						3.int nodeIndex = searchKey(key, linkedListIndex);
									This line searches for the key within the linked list at the calculated index. 
									The searchKey method is used to find the position of the key within the linked list, or it returns -1 if the key is not present.
						4.if (nodeIndex != -1) { ... }
									This conditional block is executed if the key is found in the linked list (i.e., nodeIndex is not -1). 
									In this case, it retrieves the associated value from the node at the found index within the linked list and returns it.
						5.return null;
									If the key is not found in the linked list (i.e., nodeIndex is -1), this line is executed, and the method returns null to indicate that the key was not present in the hash map.
			remove(key):
						This Method used to remove a key-value pair from the hash map based on a given key. 
						It calculates the hash code of the key to determine the index within the array of linked lists (buckets) where the key is likely to be located. 
						Then, it searches the linked list at that index to find the specific key, removes the corresponding node if found, and returns the value associated with the removed key.
						Explanation of each step:
						1.int bucketIndex = hashFunction(key);
									This line calculates the index where the key should be located within the array of linked lists using the hashFunction. 
									It maps the key to an index based on its hash code.
						2.LinkedList<Node> linkedListIndex = bucket[bucketIndex];
									This line retrieves the linked list at the calculated bucketIndex within the array of linked lists. 
									It identifies the specific linked list where the key might be found.
						3.int nodeIndex = searchKey(key, linkedListIndex);
									This line searches for the key within the linked list at the calculated index. 
									The searchKey method is used to find the position of the key within the linked list, or it returns -1 if the key is not present.
						4.if (nodeIndex == -1) { ... }
									This conditional block is executed if the key is not found in the linked list (i.e., nodeIndex is -1). 
									In this case, the method returns null to indicate that the key was not present in the hash map.
						5.else { ... }
									If the key is found in the linked list, this block is executed. 
									It retrieves the value associated with the found key, removes the corresponding node from the linked list, decrements the size counter, and returns the removed value.
			containsKey(key):
						This method is used to determine whether the hash map contains a specific key. 
						It calculates the hash code of the given key to determine the index within the array of linked lists (buckets) where the key is likely to be located. 
						Then, it searches the linked list at that index to find the specific key and returns a boolean value indicating whether the key is present or not.
						Explanation of each step:
						1.nt index = hashFunction(key);
									This line calculates the index where the key should be located within the array of linked lists using the hashFunction. 
									It maps the key to an index based on its hash code.
						2.LinkedList<Node> linkedList = bucket[index];
									This line retrieves the linked list at the calculated index within the array of linked lists. 
									It identifies the specific linked list where the key might be found.
						3.int keyIndex = searchKey(key, linkedList);
									This line searches for the key within the linked list at the calculated index. 
									The searchKey method is used to find the position of the key within the linked list, or it returns -1 if the key is not present.
						4.return keyIndex != -1;
									This line returns true if the key was found in the linked list (i.e., keyIndex is not -1), indicating that the hash map contains the specified key. 
									If the key is not found, it returns false.
			size():
				The size method is very straightforward. 
				It doesn't involve complex calculations or operations; it simply returns the value of the size variable. 
				The size variable is incremented whenever a new key-value pair is added to the hash map (in the put method) and decremented when a key-value pair is removed (in the remove method).











*/
