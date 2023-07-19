package com.prudhvi.double_linked_list;

import java.util.HashMap;
/*
	Problem Description
		1.You are given a linked list A
		2.Each node in the linked list contains two pointers: a next pointer and a random pointer
		3.The next pointer points to the next node in the list
		4.The random pointer can point to any node in the list, or it can be NULL
		5.Your task is to create a deep copy of the linked list A
		6.The copied list should be a completely separate linked list from the original list, but with the same node values and random pointer connections as the original list
		7.You should create a new linked list B, where each node in B has the same value as the corresponding node in A
		8.The next and random pointers of each node in B should point to the corresponding nodes in B (rather than A)


	Problem Constraints
		0 <= |A| <= 106
 */
class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
    
    /*
    The given code is used to create a deep copy of a linked list with random pointers. 
    This code optimizes the space complexity by using a two-pass approach
    
    Time Complexity:
    		The time complexity of the code is O(n), where n is the number of nodes in the original linked list. 
    		This is because we iterate through the linked list three times: once for the first pass, once for the second pass, and once for resetting pointers in the original and copied linked lists.

	Space Complexity:
			The space complexity of the code is O(1) because we do not use any additional space that grows with the input size.
     */
    public RandomListNode copyRandomListOptimized(RandomListNode head) {
    	//Start the first pass: Iterate through the original linked list.
        RandomListNode temp=head;
        
        while(temp!=null){
            RandomListNode newNode=new RandomListNode(temp.label);//For each node temp, create a new node newNode with the same label as temp.
            newNode.next=temp.next; //Set the next pointer of newNode to temp.next
            temp.next=newNode;	//Update the next pointer of temp to newNode.
            temp=temp.next.next;    //Move temp to the next node (skip the copied node) by temp=temp.next.next.     
        }
        temp=head;//Start the second pass: Reset temp back to the head of the original linked list.
        //Iterate through the linked list again.
        while(temp!=null){
        	 //For each node temp, check the random pointer:
        	//If temp.random is null, set the random pointer of temp.next to null.
            if(temp.random==null){
                temp.next.random=null;
            }
            //If temp.random is not null and temp.next is not null, set the random pointer of temp.next to temp.random.next.
            else if(temp.next!=null){
                 temp.next.random=temp.random.next;
            }
            temp=temp.next.next; //Move temp to the next node (skip the copied node) by temp=temp.next.next.
        }
        //Reset temp back to the head of the original linked list.
        temp=head;
        //Create a new node answerNodeHead and set it equal to temp.next (head of the copied linked list).
        RandomListNode answerNodeHead=temp.next;
        //Create a temporary node temp2 and set it equal to answerNodeHead.
        RandomListNode temp2=answerNodeHead;
       //Iterate through the original and copied linked lists simultaneously:
        while(temp!=null){
        	//Set the next pointer of temp to temp.next.next (skip the copied node).
            temp.next=temp.next.next;
            //If temp2.next is not null, set the next pointer of temp2 to temp2.next.next (skip the copied node).
            if(temp2.next!=null){
                 temp2.next=temp2.next.next;
            }
            temp=temp.next;//Move temp to the next node by temp=temp.next.
            temp2=temp2.next;//Move temp2 to the next node by temp2=temp2.next.

        }
       return answerNodeHead;
     }
    /*
    note: Brute Force Space Complexity: O(N)
    The given code is used to create a deep copy of a linked list with random pointers
    
    Time Complexity:
    		The time complexity of the code is O(n), where n is the number of nodes in the original linked list. 
    		This is because we iterate over the list twice: once to create the copied nodes and once to assign the random pointers.
	
	Space Complexity:
			The space complexity of the code is O(n), as we use extra space to store the mapping between the original nodes and the copied nodes in the addresses HashMap.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
    	//Check if the input head is null. If it is, return null to indicate an empty list.
        if(head==null){
            return null;
        }
        //Create a HashMap addresses to keep track of the mapping between original nodes and copied nodes.
        HashMap<RandomListNode,RandomListNode> addresses=new HashMap<>();
        //Create two pointers h1 and h2 and initialize h1 with the head of the original list and h2 with a new RandomListNode with the same label as the head node.
        RandomListNode h1=head;
        RandomListNode deepCopyListNode=new RandomListNode(head.label);
        //Create a temporary pointer temp and set it equal to h2.
        RandomListNode temp=deepCopyListNode;
        //Add the mapping between h1 (original node) and temp (copied node) to the addresses HashMap.
        addresses.put(h1,temp);
        h1=h1.next;//Move h1 to the next node and iterate over the rest of the original list.
        while(h1!=null){
        	//create a new RandomListNode with the label of the current h1 node 
            RandomListNode node=new RandomListNode(h1.label);
            //and assign it to temp.next. 
            temp.next=node;
            //Move temp to the newly created node.
            temp=temp.next;
            //Add the mapping between h1 and temp to the addresses HashMap.
            addresses.put(h1,temp); 
            h1=h1.next;
        }
        //Reset temp to h2 and h1 to head.
        temp=deepCopyListNode;
        h1=head;
        //Iterate over the original list again.
        while(h1!=null){
        	//Inside the loop, set the random pointer of temp to the corresponding copied node stored in the addresses HashMap using the h1.random node as the key.
            temp.random=addresses.get(h1.random);
            //Move both h1 and temp to their next nodes.
            h1=h1.next;
            temp=temp.next;
        }
        //Return the copied linked list h2.
        return deepCopyListNode;
    }
}


