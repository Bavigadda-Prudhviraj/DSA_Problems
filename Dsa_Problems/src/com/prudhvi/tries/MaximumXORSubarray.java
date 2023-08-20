package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class MaximumXORSubarray {
	/*
	Problem Description
		Given an array, A of integers of size N. Find the sub array AL, AL+1, AL+2, ... AR with 1<=L<=R<=N, which has maximum XOR value.
		NOTE: 1.If there are multiple sub arrays with the same maximum value, return the sub array with minimum length. 
			  2.If the length is the same, return the sub array with the minimum starting index.
	Problem Constraints
		1 <= N <= 100000
		0 <= A[i] <= 109
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(33,29,18));//1,2  //1, 4, 3
		ArrayList<Integer> maxSubArrXor=maxSubArrayXor(arr);
		System.out.println(maxSubArrXor);
	}
	/*
	Function maxSubArrayXor that aims to find a sub array with the maximum XOR value among all sub arrays of a given array. 
	The approach involves utilizing a trie data structure to efficiently compute the XOR of sub arrays and identify the sub array with the maximum XOR
	
	Time Complexity:
			Constructing the trie takes O(N * log MAX), where N is the number of elements in the array and MAX is the maximum value in the array.
			For each element in the array, the trie operations take O(log MAX) time.
			Therefore, the overall time complexity of the function is O(N * log MAX).

	Space Complexity:
			The space complexity is determined by the storage used for the trie nodes, which can hold a maximum of O(N * log MAX) nodes.
			Additionally, a constant amount of extra space is used for variables and temporary storage.
			Therefore, the overall space complexity is O(N * log MAX).
	 */
	private static ArrayList<Integer> maxSubArrayXor(ArrayList<Integer> arr) {
		int maxXor=Integer.MIN_VALUE;
		int maxNum=findMax(arr);// Find the maximum number in the array
		int msb=msb(maxNum);// Find the position of the most significant bit in the maximum number
		int start=0,end=0,preXor=0;
		TrieNode root=new TrieNode();
		insertEleIntoTrie(root,preXor,msb,-1);// Insert an initial element into the Trie (Note:- Why? reason below)
		for(int i=0;i<arr.size();i++) {
			preXor^=arr.get(i);// Calculate the prefix XOR
			insertEleIntoTrie(root,preXor,msb,i);// Insert the prefix XOR into the Trie
			ArrayList<Integer> xor_index=maxXor(root, preXor, msb);// Find the maximum XOR and its corresponding index
			int currentXor=xor_index.get(0);// Get the maximum XOR value
			//xor_index.get(1) retrieves the 0-based index from the xor_index ArrayList.
			//Adding 1 to this value effectively converts it to a 1-based index, which is the desired format for the output.
			int index=xor_index.get(1)+1;	// Get the corresponding index for the maximum XOR & Incrementing index
			if(maxXor<currentXor) {
				maxXor=currentXor;// Update the maximum XOR value
				start=index;// Update the start index of the sub array
				end=i;// Update the end index of the sub array
			}
			else if(maxXor==currentXor) {
				int prevLen=end-start+1;
				int newLen=i-index+1;
				if(newLen<prevLen) {
					start=index;
					end=i;
				}
				else if (newLen==prevLen && index<start){
					start=index;
					end=i;
				}
			}
		}
		
		return new ArrayList<>(List.of(start+1,end+1));
	}
	private static int findMax(ArrayList<Integer> prefixXorList) {
		int max=-1;
		for(int i=0;i<prefixXorList.size();i++) {
			max=Math.max(max, prefixXorList.get(i));
		}
		return max;
	}
	private static int msb(int num) {
		for(int i=31;i>=0;i--) {
			if((num&(1<<i))>0) 
				return i;
		}
		return -1;
	}
	private static void insertEleIntoTrie(TrieNode root, int xor, int msb,int index) {
		TrieNode temp=root;
		for(int i=msb;i>=0;i--){
			int digit=(xor&(1<<i))>0?1:0;
			if(temp.child[digit]==null) {
				temp.child[digit]=new TrieNode();
			}
			temp=temp.child[digit];
			temp.index=i;
		}
		temp.index=index;
		temp.val=xor;
	
	}
	private static ArrayList<Integer> maxXor(TrieNode root, int num, int msb) {
		TrieNode temp=root;
		for(int i=msb;i>=0;i--) {
			int digit=(num&(1<<i))>=0?1:0;
			if(temp.child[1-digit]!=null) {
				temp=temp.child[1-digit];
			}else {
				temp=temp.child[digit];
			}
		}
		int index=temp.index;
		int xor=temp.val^num;
		ArrayList<Integer> xor_num=new ArrayList<>(List.of(xor,index));
		return xor_num;
	}
}
class TrieNode{
	TrieNode[] child;
	int index;
	int val;
	public TrieNode() {
		child=new TrieNode[2];
		index=-1;
	}
}
	/*
	Inserting 0 into the Trie before iterating through the array is a crucial step in the algorithm to handle the case where the XOR of a sub array from index 0 to i is maximum. 
	It's done to ensure that the XOR value of the sub array starting at index 0 is also considered in the comparisons.
		
		Here's why inserting 0 into the Trie before iterating is important:
			1.Handling Sub arrays Starting from Index 0: 
					When you're iterating through the array, you are calculating the XOR of sub arrays starting from index 0 up to the current index i. 
					However, the code inside the loop considers only sub arrays starting from index 1 (i.e., it calculates preXor for the current index and inserts it into the Trie). 
					This means that the sub array starting from index 0 is not considered in the loop, potentially missing out on the maximum XOR value if it happens to be the largest.
		
			2.Inserting 0 into Trie: 
					By inserting 0 into the Trie before starting the loop, you're considering the XOR value of the sub array starting from index 0. 
					This ensures that the maximum XOR value of sub arrays starting from index 0 to i is correctly calculated and considered in the loop's comparisons.
	
	In summary, inserting 0 into the Trie at the beginning ensures that the algorithm accounts for the XOR value of the sub array starting from index 0. This is necessary to handle all possible sub arrays and accurately find the maximum XOR value and its corresponding indices.

	 */
