package com.prudhvi.custom_data_types.hashmap;
/*
	Hashing:
			In the context of a HashMap, hashing refers to the process of converting a given key into an index in the underlying array where the corresponding value will be stored. 
			When you insert a key-value pair into a HashMap, the HashMap uses a hashing function to compute a hash code from the key. 
			This hash code is then used to determine the index where the value associated with the key will be stored in the internal array of the HashMap.
		
		1.A hash function produces the same output every time when you pass the same parameter to it
		2.A perfect hash function always produce unique hash for unique input
		3.Non-Perfect hash Function  there is a possible to produce same output for different unique input this is called as collision

		General used hash functions:
			1.Division Method: h(k)=k mod M 
			2.Mid square method:h(K)=h(k*k):not good Approach
			3.Digit Folding Method:(better Approach) 
					k=k1,k2,k3,k4,k5....,kn.
					s=k1+k2+k2+k4+k5..+kn
					h(k)=s
					1.Divide into segments of equal sizes
					2.take sum of segments
					3.take mod of sum(sum%m) 
			4.Multiplication Method:H(k)=floor( M(kA mod 1)),A=constant,M= table size (best Approach)
				1.we choose a constant value of A such that O<A<1
				2.we multiple K with this constant
				3.we extract fractional past k*A
				4.after extracting multiple with table size get floor value
				
			1.Division Method:
					The "division method" is one of the simplest hashing techniques used to convert a key into a hash code for a HashMap. 
					It involves taking the remainder of the division of the key's numeric representation by the size of the hash table (the internal array) to obtain the index where the corresponding value will be stored. 
					Here's the process of hashing by the division method in a HashMap:
											1.Get the Hash Code: 
														When you insert a key-value pair into the HashMap, the HashMap implementation calls the hashCode() method of the key object to generate a hash code. 
														The hashCode() method typically returns an integer value that represents the key's hash code.
											2.Transform Hash Code into Index: 
														The raw hash code obtained from the hashCode() method might be a large integer. 
														To obtain a valid index within the range of the internal array, the HashMap applies the division method. 
														It calculates the remainder of the division of the hash code by the size of the hash table, which is equal to the number of buckets in the internal array.

											For example, let's say the hash table has a size of N (number of buckets), and the raw hash code is hash. The index i for the key-value pair will be calculated as:
															i = hash % N
											i.Handle Collisions: As mentioned before, hash collisions occur when two different keys generate the same hash code or map to the same index in the internal array. 
																 To handle collisions, HashMap uses a technique called separate chaining. 
																 It maintains a linked list or a binary search tree at each array index to store multiple key-value pairs that collide to the same index.
											ii.Lookup and Retrieval: 
																When you want to retrieve a value associated with a given key, the HashMap calculates the hash code of the key, applies the division method to get the index, and then traverses the linked list or binary search tree (if a collision exists) to find the key and its corresponding value.
											public class DivisionHashFunction {
											    private int tableSize; // Size of the hash table
											    public DivisionHashFunction(int tableSize) {
											        this.tableSize = tableSize;
											    }
											    public int hash(int key) {
											        // Ensure the key is positive by taking its absolute value
											        int positiveKey = Math.abs(key);
											        // Calculate the hash code by taking the remainder of the division
											        int hashCode = positiveKey % tableSize;
											        return hashCode;
											    }
											    public static void main(String[] args) {
											        int tableSize = 10;
											        DivisionHashFunction hashFunction = new DivisionHashFunction(tableSize);
											        int key = 456;
											        int hashValue = hashFunction.hash(key);
											        System.out.println("Key: " + key + ", Hash Code: " + hashValue);
											    }
											}

						It's worth noting that the effectiveness of the division method depends on the size of the hash table. 
						For better performance and reduced collisions, the size of the hash table (number of buckets) is usually chosen to be a prime number. 
						This helps to distribute the keys more evenly across the array, reducing the likelihood of collisions and improving the overall efficiency of the HashMap.
			
			2.Mid Square method:
					The "mid-square method" is another technique used for generating hash codes in hash functions. 
					It involves taking the square of the numeric representation of the key, extracting a portion from the middle of the squared value, and using that extracted portion as the hash code. 
					The extracted portion is usually the middle digits of the squared value.
					Here's the process of the mid-square method for generating hash codes:
								1.Get the Numeric Representation: 
											First, convert the key into its numeric representation. For example, if the key is a string, you can convert each character into its ASCII value and combine them into a single number.
								2.Square the Numeric Representation: 
											Calculate the square of the numeric representation obtained in the previous step.
								3.Extract the Middle Digits: 
											Extract a portion from the middle of the squared value. 
											The number of digits you extract depends on the desired size of the hash table. 
											For example, if the hash table size is N, you can extract M digits from the middle, where M is equal to the number of digits needed to represent N. 
											If the extracted portion is smaller than M digits, you can pad with zeros on the left to make it M digits.
								4.Use Extracted Digits as Hash Code: 
											The extracted digits are used as the hash code, which represents the index in the hash table where the corresponding value will be stored.
								4.The mid-square method can be simple to implement and can work well for certain scenarios. 
								Example: h -> 104,e -> 101,l -> 108,l -> 108,o -> 111
										 The numeric representation of the key "hello" is 104101108108111.
										 104101108108111 * 104101108108111 = 10819158558463132121
										 We need to extract a portion from the middle of the squared value. Since our hash table size N is 10, we need to extract two digits from the middle. The squared value has 20 digits, so we'll take digits 9 and 10 from the middle:
										 The extracted digits "55" will be used as the hash code. Since our hash table size is 10, we take the modulo 10 of the extracted digits to get the index in the hash table:
										 55 % 10 = 5,we store hello in index 5
										public class MidSquareHashFunction {
										    private int tableSize; // Size of the hash table
										    private int numDigits; // Number of digits to use in the hash code
										    public MidSquareHashFunction(int tableSize, int numDigits) {
										        this.tableSize = tableSize;
										        this.numDigits = numDigits;
										    }
										    public int hash(int key) {
										        int squared = key * key;
										        int numDigitsSquared = (int) Math.log10(squared) + 1;
										        if (numDigitsSquared < numDigits) {
										            // If the squared value has fewer digits than the required number of digits,
										            // pad with zeros on the left to make it the required number of digits.
										            squared *= (int) Math.pow(10, numDigits - numDigitsSquared);
										        } else if (numDigitsSquared > numDigits) {
										            // If the squared value has more digits than the required number of digits,
										            // take the middle digits by removing extra digits from the left and right.
										            int numToRemove = (numDigitsSquared - numDigits) / 2;
										            int divisor = (int) Math.pow(10, numToRemove);
										            squared = (squared / divisor) % (int) Math.pow(10, numDigits);
										        }
										        int index = squared % tableSize; // Take the modulo to get the index
										        return index;
										    }
										    public static void main(String[] args) {
										        int tableSize = 10;
										        int numDigits = 3;
										        MidSquareHashFunction hashFunction = new MidSquareHashFunction(tableSize, numDigits);
										        int key = 456;
										        int hashValue = hashFunction.hash(key);
										        System.out.println("Key: " + key + ", Hash Code: " + hashValue);
										    }
										}

					However, it may suffer from clustering issues and poor distribution of hash codes if the extraction of digits does not evenly distribute the keys across the hash table.
					Keep in mind that modern programming languages and libraries, such as Java's HashMap, typically use more advanced and robust hashing techniques like the division method or multiplication method, which have better distribution properties and handle collisions more effectively.
			
			3.Digit Folding Method:
					The "Digit Folding Method" is another technique used for generating hash codes in hash functions. 
					It involves breaking down the numeric representation of the key into smaller parts (digits) and combining these parts in some way to create the hash code. 
					This method is often used when the numeric representation of the key is larger than the desired size of the hash table.
					Here's the process of the Digit Folding Method for generating hash codes:
							1.Get the Numeric Representation: 
										Convert the key into its numeric representation. 
										For example, if the key is a string, you can convert each character into its ASCII value and combine them into a single number.
							2.Divide the Numeric Representation: 
										Divide the numeric representation obtained in the previous step into smaller parts (digits). 
										The number of digits you divide into depends on the desired size of the hash table.
							3.Combine the Digits: 
										Combine the divided digits using some arithmetic operations (e.g., addition, multiplication, bitwise operations, etc.) to create the final hash code.
							4.Use the Combined Digits as Hash Code: 
										The combined digits represent the hash code, which indicates the index in the hash table where the corresponding value will be stored.
							Example:1.Divide the Numeric Representation:123456789
										Now, we divide the numeric representation into smaller parts. For this example, we'll split the numeric representation into three parts of three digits each:
										123 | 456 | 789
										Combine the Digits: Next, we combine the divided digits using addition:
										123 + 456 + 789 = 1368
										Use the Combined Digits as Hash Code:
										Since our hash table size is 7, we take the modulo 7 of the combined digits to get the index in the hash table:
										1368 % 7 = 1
										So, the key "123456789" will be stored at index 1 in the hash table.
										public class DigitFoldingHashFunction {
										    private int tableSize; // Size of the hash table
										    private int numDigits; // Number of digits to use in the hash code
										    public DigitFoldingHashFunction(int tableSize, int numDigits) {
										        this.tableSize = tableSize;
										        this.numDigits = numDigits;
										    }
										    public int hash(int key) {
										        int sum = 0;
										        int tempKey = Math.abs(key); // Make sure the key is positive
										
										        for (int i = 0; i < numDigits; i++) {
										            int digit = tempKey % 10; // Extract the last digit of the key
										            tempKey /= 10; // Remove the last digit from the key
										            sum += digit;
										        }
										        int index = sum % tableSize; // Take the modulo to get the index
										        return index;
										    }
										    public static void main(String[] args) {
										        int tableSize = 10;
										        int numDigits = 3;
										        DigitFoldingHashFunction hashFunction = new DigitFoldingHashFunction(tableSize, numDigits);
										        int key = 456789;
										        int hashValue = hashFunction.hash(key);
										        System.out.println("Key: " + key + ", Hash Code: " + hashValue);
										    }
										}
					The Digit Folding Method allows for more flexibility in generating hash codes compared to methods like division or multiplication. 
					You can adjust the number of digits, the combination method, and other parameters to improve the distribution of hash codes and reduce collisions.
					Keep in mind that like the other hash functions, the Digit Folding Method should aim to evenly distribute the keys across the hash table to minimize collisions and provide efficient access to the stored values.
					It's important to note that the specific implementation of the Digit Folding Method can vary depending on the requirements and characteristics of the data being hashed. 
					It is often used as a component in more complex hash functions.
					
			4.Multiplication Method:
					The Multiplication Method is a popular technique for generating hash codes in hash functions. 
					It involves using a constant (multiplier) and the numeric representation of the key to compute the hash code. 
					The method is widely used because it tends to provide better distribution and reduce clustering compared to other simple hashing techniques like division method.
					Here's the process of the Multiplication Method for generating hash codes:
							1.Get the Numeric Representation: 
									Convert the key into its numeric representation. For example, if the key is an integer, you can directly use the value of the integer as its numeric representation. 
									If the key is a string, you can convert each character into its ASCII value and combine them into a single number.
							2.Choose a Multiplier: 
									Select a constant floating-point value called the multiplier. 
									The multiplier is typically a value between 0 and 1, exclusive.
							3.Calculate the Hash Code: 
									Multiply the numeric representation of the key by the multiplier and extract the fractional part. 
									Then, multiply this fractional part by the size of the hash table (N) and take the floor of the result to get the index in the hash table.
							4.Mathematically, the hash code can be calculated as follows:
									hash_code = floor(N * ((key * multiplier) - floor(key * multiplier)))
							5.Use the Hash Code as Index: 
									The calculated hash code represents the index in the hash table where the corresponding value will be stored.
									Example:we'll use a hash table of size N = 10 (for simplicity), and we want to store integers as keys in the hash table.
											1.Get the Numeric Representation:
													Suppose we have an integer key "456". The numeric representation of the integer is simply the value of the integer itself.
											2.Choose a Multiplier:
													Let's choose the multiplier value to be 0.6180339887. 
													This value is derived from the golden ratio (φ) and is known to provide a good distribution for a wide range of hash table sizes.
													Multiplier (m) = 0.6180339887
											3.Calculate the Hash Code:
													Now, we calculate the hash code using the multiplication method formula:
													hash_code = floor(N * ((key * multiplier) - floor(key * multiplier)))
													hash_code = floor(10 * ((456 * 0.6180339887) - floor(456 * 0.6180339887)))
													          = floor(10 * (281.1706816672 - 173))
													          = floor(10 * 108.1706816672)
													          = floor(1081.706816672)
													          = 1081
											4.Use the Hash Code as Index:
													Since our hash table size is 10, we take the modulo 10 of the calculated hash code to get the index in the hash table:
													1081 % 10 = 1
													So, the key "456" will be stored at index 1 in the hash table.
													public class MultiplicationHashFunction {
													    private int tableSize; // Size of the hash table
													    private double multiplier; // Multiplier constant
													    public MultiplicationHashFunction(int tableSize, double multiplier) {
													        this.tableSize = tableSize;
													        this.multiplier = multiplier;
													    }
													    public int hash(int key) {
													        double intermediateValue = key * multiplier;
													        double fractionalPart = intermediateValue - Math.floor(intermediateValue);
													        double scaledValue = fractionalPart * tableSize;
													        int index = (int) Math.floor(scaledValue);
													        return index;
													    }
													    public static void main(String[] args) {
													        int tableSize = 10;
													        double multiplier = 0.6180339887;
													
													        MultiplicationHashFunction hashFunction = new MultiplicationHashFunction(tableSize, multiplier);
													
													        int key = 456;
													        int hashValue = hashFunction.hash(key);
													
													        System.out.println("Key: " + key + ", Hash Code: " + hashValue);
													    }
													}	
																									
							
						The choice of the multiplier is critical for achieving a good distribution of hash codes. 
						A good multiplier should be close to an irrational number, and its value is often chosen based on empirical testing and analysis.
						
						In practice, modern programming languages and libraries, such as Java's HashMap, use more advanced and well-tuned hash functions based on the multiplication method to ensure good distribution, reduce collisions, and provide efficient access to the stored values.
	
	
	Collision:
			A "collision" refers to a situation where two distinct elements or objects have the same hash value when processed by a hash function. 
			Hash functions are used to map data of arbitrary size to fixed-size values (hash codes) in various applications, such as hash tables, hash maps, cryptographic algorithms, and more.
				
				Collision handling methods are techniques
						1.Separate Chaining(Open Hashing):
						2.Open Addressing (Closed Hashing):
								i.linear probing:
								ii.quadrtic probing:
								iii.Double Hashing:
			
			
			1.Separate Chaining:
					Separate chaining is a collision resolution technique used in hash-based data structures, such as hash maps or hash tables. 
					It involves maintaining a collection (usually a linked list) of elements for each bucket in the hash table. 
					When a collision occurs (i.e., multiple keys hash to the same index), the colliding elements are stored in the same bucket using this collection.
					Implementation:
							1.Initialization: 
									Start with an array (hash table) with a fixed number of buckets. 
									Each bucket will hold a collection of elements that hash to the same index.
							2.Hash Function: 
									When you insert or search for an element, apply a hash function to the key to determine the index (bucket) where the element should be placed or searched.
							3.Insertion:
									i.Apply the hash function to the key to determine the index.
									ii.If the bucket at the determined index is empty, insert the element directly into that bucket.
									iii.If the bucket is not empty (collision occurs), add the new element to the collection associated with that bucket (e.g., a linked list).
							4.Search:
									i.Apply the hash function to the key to determine the index.
									ii.Search the collection in the bucket at the determined index for the desired element. 
									   This involves traversing the linked list and comparing keys until a match is found or the end of the list is reached.
							5.Deletion:
									i.Apply the hash function to the key to determine the index.
									ii.Search the collection in the bucket at the determined index for the element to delete. 
									   If found, remove the element from the collection.
							6.Handling Collisions: 
									i.The separate chaining approach ensures that even if multiple keys hash to the same index, they can be stored and managed within the same bucket using a linked list or similar data structure.
							7.Performance and Load Factor: 
									i.The efficiency of separate chaining depends on the distribution of keys and the load factor (ratio of elements to buckets). 
									  With a well-distributed hash function and an appropriate load factor, separate chaining can provide efficient operations.
									 
									 Load Factor:
											Increasing the capacity of a hash-based data structure as per the load factor involves resizing the structure to accommodate a higher number of elements while maintaining a reasonable load factor. 
											The load factor is the ratio of the number of elements to the number of buckets in the hash table. Resizing ensures that the load factor does not exceed a certain threshold, typically around 0.75 to 0.80, to prevent excessive collisions and maintain good performance.
											
											Here's a step-by-step explanation of how increasing the capacity based on the load factor is typically implemented:
												1.Initial State: 
														Start with an initial hash table with a fixed number of buckets and a specified load factor threshold. The load factor threshold is the point at which resizing will be triggered.
												2.Insertion:
														i.When inserting an element, calculate the load factor by dividing the number of elements by the number of buckets.
														ii.If the calculated load factor exceeds the load factor threshold, it's time to resize the hash table to accommodate more elements while maintaining an acceptable load factor.
												3.Resizing:
														i.Create a new hash table with a larger number of buckets (e.g., double the current size or use another growth factor).
														ii.Rehash all existing elements from the old hash table into the new one based on their keys and the new hash function. 
														   Rehashing recalculates the bucket index for each element in the context of the new hash table size.
														ii.This redistribution of elements helps ensure a more balanced distribution and reduces the likelihood of collisions in the new hash table.
												4.Post-Resizing:
														i.After rehashing, the new element is inserted into the resized hash table as usual.
														ii.The load factor is calculated again based on the new number of elements and buckets.
												5.Deletion Considerations:
														i.If the hash-based data structure supports deletion, it's important to update the load factor after each deletion and check whether resizing down is necessary to prevent becoming too sparse.
											
									Increasing the capacity based on the load factor helps maintain a balance between memory usage and performance. 
									By periodically resizing the hash table as the number of elements grows, you can ensure that the load factor remains within an acceptable range, minimizing collisions and keeping operations efficient.
																						
																				
																					
			
*/