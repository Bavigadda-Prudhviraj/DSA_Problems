package com.prudhvi.stack;

import java.util.ArrayList;



/*
	The given code implements a special stack called MinStack that supports additional operations to retrieve the minimum element in constant time. 
			The MinStack consists of two ArrayLists: 
				1.variableStack to store the elements pushed onto the stack,  
				2.minVariablesStack to store the minimum elements encountered so far in the variableStack.
	Time Complexity:
			The time complexity for all the methods (push, pop, top, and getMin) is O(1) in both the best case and worst case scenarios. 
			This is because all operations, such as adding, removing, and retrieving elements from an ArrayList, are constant time operations, 
			and the size of the stack does not affect the time complexity of these methods.

	Space Complexity:
			The space complexity for the MinStack is O(n) in both the best case and worst case scenarios, 
			where n is the number of elements pushed onto the stack. 
			This is because the two ArrayLists, variableStack and minVariablesStack, grow with the number of elements pushed onto the stack. 
			In the best case scenario, the minimum element is always the first element, and the size of minVariablesStack is 1. 
			In the worst case scenario, the minimum element is the same for all elements pushed, and both variableStack and minVariablesStack will have the same size.
 */
public class MinStack {
	//Declare two ArrayLists: variableStack to store the elements pushed onto the stack, 
    ArrayList<Integer> variableStack=new ArrayList<>();
    //and minVariablesStack to store the minimum elements encountered so far.
	ArrayList<Integer> minVariablesStack=new ArrayList<>();
	

	//The push method takes an integer value x as input and adds it to the stack. 
	//It also ensures that the minVariablesStack is updated with the minimum element encountered so far. 
	public void push(int x) {
		//If minVariablesStack is empty, it means x is the first element, so it is added to minVariablesStack. 
		if(minVariablesStack.isEmpty()) {
			minVariablesStack.add(x);
		}
		else {
			//if x is smaller than or equal to the last element of minVariablesStack, it means x is the new minimum, so it is added to minVariablesStack.
			if(minVariablesStack.get(minVariablesStack.size()-1)>=x) {
				minVariablesStack.add(x);
				
			}
		} 
        variableStack.add(x);
    }
	
	//The pop method removes the top element from the variableStack. 
    public void pop() {
    	//check variable stack is empty or not if empty then only we can pop elements.
    	if(!variableStack.isEmpty()){
    		//It also checks if the element being removed is the minimum element (i.e., top element of minVariablesStack)
            int stackTopElement=variableStack.get(variableStack.size()-1);
            //If it is, the top element is also removed from minVariablesStack.
    	    if(stackTopElement==minVariablesStack.get(minVariablesStack.size()-1)) {
    		    minVariablesStack.remove(minVariablesStack.size()-1);
    	    }
    	    //remove that element from the stack means arrayList. 
    	    variableStack.remove(variableStack.size()-1);
        }
    }
    
    //The top method retrieves the top element from the variableStack, but if the stack is empty, it returns -1.
    public int top() {
    	if(variableStack.isEmpty()) {
    		return -1;
    	}
    	else {
    		return variableStack.get(variableStack.size()-1);
    	}  
    }
    //The getMin method retrieves the minimum element from the minVariablesStack, but if the stack is empty, it returns -1.
    public int getMin() {
    	if(minVariablesStack.isEmpty()) {
    		return -1;
    	}
    	else {
    		return minVariablesStack.get(minVariablesStack.size()-1);
    	}
    }
	
}
