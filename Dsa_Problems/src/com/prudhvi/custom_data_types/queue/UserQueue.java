package com.prudhvi.custom_data_types.queue;

import java.util.Stack;
/*


	This code implements a queue using two stacks, FrontOrAddingStack and rearOrRemovingStack. 
	The queue follows the First-In-First-Out (FIFO) principle, meaning elements are added to the back and removed from the front. 
	The push, pop, peek, and empty methods provide the basic functionalities of adding elements to the back, removing elements from the front, getting the front element without removing it, and checking if the queue is empty, respectively.
	
	Time Complexity:
			1.Push operation is O(1) as it involves pushing an element onto the FrontOrAddingStack.
			2.Pop operation is O(n) in the worst case if it needs to transfer all elements from one stack to the other. 
			  However, on average, it is O(1) as the elements are moved only once from one stack to the other, 
			  giving it an amortized O(1) time complexity.
			3.Peek operation has a similar time complexity to the pop operation, O(n) in the worst case 
			  and Note:-O(1) amortized.
			4.Empty operation is O(1) since it only involves checking if both stacks are empty.
			Note:-To know more about amortized time complexity mention at the end of the code.

	Space Complexity:
			The space complexity is O(n), where n is the number of elements in the queue. 
			In the worst case, all elements can be stored in both the FrontOrAddingStack and rearOrRemovingStack.
	
	In this particular implementation of the queue using two stacks, 
	the push operation is O(1), 
	the pop operation is O(1) amortized, 
	the peek operation is O(1) amortized, and 
	the empty operation is O(1). 
	The space complexity is O(n).
 */
public  class UserQueue {
	//Two static stacks are declared as class variables: FrontOrAddingStack and rearOrRemovingStack. 
	//These stacks will be used to store elements of the queue.
	 static Stack<Integer> FrontOrAddingStack;
	 static Stack<Integer> rearOrRemovingStack;
    /** Initialize your data structure here. */
    UserQueue() {//The constructor UserQueue() initializes both stacks.
    	 FrontOrAddingStack=new Stack<>();
    	 rearOrRemovingStack=new Stack<>();
        
    }
    
    /** Push element X to the back of queue. */
    //The push( X) method is used to push an element X to the back of the queue. It simply pushes the element onto the FrontOrAddingStack.
    static void push(int X) {
    	FrontOrAddingStack.push(X);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    //The pop() method is used to remove and return the front element of the queue. 
    static int pop() {
    	//It first checks if the rearOrRemovingStack is not empty. If it's not empty, it means there are elements in the rearOrRemovingStack, and the front element is at the top. 
        //In this case, the method pops the front element and returns it.
    	if(!rearOrRemovingStack.isEmpty()) {
    		int deletingElement=rearOrRemovingStack.peek();
    		rearOrRemovingStack.pop();
    		return deletingElement;
    	}
    	//If the rearOrRemovingStack is empty, it means there are elements in the FrontOrAddingStack. 
    	//In this case, the method transfers all elements from the FrontOrAddingStack to the rearOrRemovingStack, effectively reversing their order. 
    	//Then, it pops the front element from the rearOrRemovingStack and returns it.
    	else {
			while(!FrontOrAddingStack.isEmpty()){
				rearOrRemovingStack.push(FrontOrAddingStack.peek());
				FrontOrAddingStack.pop();
			}
			int deletingElement=rearOrRemovingStack.peek();
			rearOrRemovingStack.pop();
			return deletingElement;
		} 
    }
    
    /** Get the front element of the queue. */
    //The peek() method is used to get the front element of the queue without removing it. 
    //It follows a similar process as the pop() method. If the rearOrRemovingStack is not empty, it returns the top element. If the rearOrRemovingStack is empty, it transfers elements from the FrontOrAddingStack to the rearOrRemovingStack, and then returns the top element of the rearOrRemovingStack.
    static int peek() {
    	//If the rearOrRemovingStack is not empty, it returns the top element.
    	if(!rearOrRemovingStack.isEmpty()) {
    		return rearOrRemovingStack.peek();
    	}
    	//If the rearOrRemovingStack is empty, it transfers elements from the FrontOrAddingStack to the rearOrRemovingStack,
    	//and then returns the top element of the rearOrRemovingStack.
    	else {
			while(!FrontOrAddingStack.isEmpty()){
				rearOrRemovingStack.push(FrontOrAddingStack.peek());
				FrontOrAddingStack.pop();
			}
			return rearOrRemovingStack.peek();
		}
    }
    
    /** Returns whether the queue is empty. */
    //The empty() method checks if both FrontOrAddingStack and rearOrRemovingStack are empty, and returns true if they are, indicating that the queue is empty.
    static boolean empty() {
    	boolean isEmpty=rearOrRemovingStack.isEmpty() && FrontOrAddingStack.isEmpty()?true:false;
    	return isEmpty; 
    }
}

/*
  	Amortized:-
  	
	 	Amortized time complexity is a way to analyze the average time complexity of a sequence of operations over time, even when some individual operations might be more expensive than others. 
	 	It provides a more realistic estimate of the average cost of each operation in a series of operations.
	
		In the context of the stack-to-queue implementation using two stacks, the pop operation has the potential to be expensive when the rearOrRemovingStack is empty. 
		In this case, to retrieve the front element, we need to transfer all elements from the FrontOrAddingStack to the rearOrRemovingStack, which would take O(n) time, 
		where n is the number of elements in the queue.
	
		However, if we analyze the sequence of operations, we can observe that for each element, there is only one push operation (where an element is added to the queue) and at most two pop operations (when that element is removed from the queue). 
		The two pop operations occur during the sequence when the rearOrRemovingStack is empty, and all elements need to be transferred.
	
		Let's say we have a series of n operations, where n elements are added to the queue and then removed. 
		The total cost of n push operations is O(n), as each push operation is O(1). 
		The total cost of the pop operations can be analyzed as follows:
			1.The first pop operation: O(n) (when all elements are transferred from FrontOrAddingStack to rearOrRemovingStack).
			2.The second pop operation: O(n) (when elements are popped from rearOrRemovingStack one by one).
			Now, if we divide the total cost of the pop operations by n, 
				we get    (O(n) + O(n)) / n,
				which is 2 * O(n) / n = 2. 
				So, on average, each pop operation costs O(2), which is O(1).
	
		Therefore, the amortized time complexity of the pop operation in this implementation is O(1). 
		It means that on average, each pop operation will take constant time, despite the occasional costly pop operation when transferring elements.
	
		To summarize, amortized time complexity considers the average cost of a sequence of operations, distributing the cost of expensive operations 
		over multiple cheap operations, giving a more balanced and realistic estimation of the average time complexity over the entire sequence.
	
		In this particular implementation of the queue using two stacks, 
		the push operation is O(1), 
		the pop operation is O(1) amortized, 
		the peek operation is O(1) amortized, and 
		the empty operation is O(1). 
		The space complexity is O(n).
 */
