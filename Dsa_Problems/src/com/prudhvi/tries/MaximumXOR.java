package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumXOR {
	/*
	Problem Description
		Given an array of integers A, find and return the maximum result of A[i] XOR A[j], where i, j are the indexes of the array.
	Problem Constraints
		1 <= length of the array <= 100000
		0 <= A[i] <= 109
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		int maxXor=maximumXor(arr);
		System.out.println(maxXor);

	}
	/*
	Below function finds the maximum XOR value that can be obtained by selecting two elements from an ArrayList of integers. 
	It uses a bitwise trie (also known as a binary trie or XOR trie) data structure to efficiently compute the maximum XOR value
	
	Time Complexity:
			1.The function maximumXor first computes the maximum number in the input list, which takes O(n) time.
			2.It then constructs a trie by inserting each number from the list, which also takes O(n) time.
			3.For each number in the list, the function maxXor traverses the trie, 
			  which takes O(log(maxNumber)) time in the worst case (where maxNumber is the maximum number in the list).
		Therefore, the overall time complexity of the maximumXor function is O(n * log(maxNumber)).
	
	Space Complexity:
			1.The space complexity is determined by the storage required for the binary trie data structure.
			2.The size of the trie depends on the binary representation of the numbers in the input list.
		The overall space complexity is O(n * log(maxNumber)), where n is the size of the input list and log(maxNumber) is the maximum height of the trie.
				 
	 */
	private static int maximumXor(ArrayList<Integer> arr) {
		int maxNumber=findMax(arr);// Find the maximum number in the array
		int msbOfMax=msbBit(maxNumber);// Find the position of the most significant bit in the maximum number
		BTNode root=new BTNode();// Create the root node of the Trie
		//It inserts all numbers from the input array into the Trie using the insertNumIntoTrie function.
		for(int i=0;i<arr.size();i++) {
			insertNumIntoTrie(root,arr.get(i),msbOfMax);
		}
		int maxXor=-1;
		//// Find the maximum XOR value for each number in the array
		for(int i=0;i<arr.size();i++) {
			maxXor=Math.max(maxXor,maxXor(root,arr.get(i),msbOfMax));// Update the maximum XOR value
		}
		return maxXor;
	}
	private static int maxXor(BTNode root, int num, int msb) {
		BTNode tempNode=root;
		int maxXor=0;
		for(int i=msb;i>=0;i--) {
			int bit=((num&(1<<i))>0)?1:0;
			//At each step, it checks if there's a complementary bit (1 if the current bit is 0, and vice versa) in the trie. 
			//If yes, it adds the corresponding power of 2 to maxXor and moves to the next node. If not, it moves to the node with the same bit.
			if(tempNode.child[1-bit]!=null) {
				maxXor+=(1<<i);
				tempNode=tempNode.child[1-bit];
			}else {
				tempNode=tempNode.child[bit];
			}
		}
		return maxXor;
	}
	private static void insertNumIntoTrie(BTNode root, int num, int msb) {
		BTNode tempNode=root;
		for(int i=msb;i>=0;i--) {
			int bit=((num&(1<<i))>0)?1:0;
			if(tempNode.child[bit]==null) {
				tempNode.child[bit]=new BTNode();
			}
			tempNode=tempNode.child[bit];
		}
		
		
	}
	private static int msbBit(int maxNumber) {
		for(int i=31;i>=0;i--) {
			if((maxNumber&(1<<i))>0) {
				return i;
			}
		}
		return -1;
	}

	private static int findMax(ArrayList<Integer> arr) {
		int max=Integer.MIN_VALUE;
		for(int i=0;i<arr.size();i++) {
			max=Math.max(max,arr.get(i));
		}
		return max;
	}

}
//helper class
class BTNode{
	BTNode[] child;
	public BTNode() {
		child=new BTNode[2];
	}
}
