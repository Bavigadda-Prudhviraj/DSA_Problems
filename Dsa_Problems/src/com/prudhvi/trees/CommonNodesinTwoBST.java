package com.prudhvi.trees;


import java.util.HashSet;

public class CommonNodesinTwoBST {
	/*
	 Problem Description
			Given two BST's A and B, return the (sum of all common nodes in both A and B) % (109 +7) .
			In case there is no common node, return 0.
		NOTE:
			Try to do it one pass through the trees.
	 */
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int answer=commonNodesSum(root, root);
		System.out.println(answer);

	}
	/*
	The code defines a method commonNodesSum that takes two binary trees A and B as input and calculates the sum of values that are common to both trees. 
	The method uses an in-order traversal to collect the values of both trees in two HashSet collections, and then finds the common elements between the sets to calculate the sum.
	
	Time Complexity:
			The time complexity of this method is O(N + M), 
			where N is the number of nodes in tree A and M is the number of nodes in tree B. 
			This is because both trees are traversed once.
	
	Space Complexity:
			The space complexity is O(N + M), where N is the number of nodes in tree A and M is the number of nodes in tree B. 
			This is due to the space required to store the HashSet values for each tree during traversal.
	 */
    public static int commonNodesSum(TreeNode A, TreeNode B) {
    	long sum=0;//Initializes the sum of common nodes to zero.
    	long mod=1000000007;// A constant used for modular arithmetic to prevent integer overflow.
        HashSet<Integer> arr1=new HashSet<>();//Initializes a HashSet to store the values of nodes in tree A during an inorder traversal.
        HashSet<Integer> arr2=new HashSet<>();//Initializes a HashSet to store the values of nodes in tree B during an inorder traversal.
        //Performs an inorder traversal of tree A and populates arr1 with the values of nodes in tree A.
        inorderTraversal(A,arr1);   
        //Performs an inorder traversal of tree B and populates arr2 with the values of nodes in tree B.
        inorderTraversal(B,arr2); 
        //The loop iterates through the elements in arr1.
        for(int element:arr1) {
        	//If the current element (element) is also present in arr2,it means that the node value is common between both trees
        	if(arr2.contains(element)) {
        		//In this case, the sum is incremented by the current element and then modulo mod.
        		sum=(sum+element)%mod;
        	}
        }
        //Finally, the method returns the computed sum as an integer value.
        return (int)sum;
    }
    public static void inorderTraversal(TreeNode root,HashSet<Integer> arr){
        if(root==null){
            return;
        }
        inorderTraversal(root.left,arr);
        arr.add(root.val);
        inorderTraversal(root.right,arr);
    }
}
