package com.prudhvi.trees;

public class BSTnodesinRange {
	/*
	Problem Description
			Given a binary search tree of integers. You are given a range B and C.
			Return the count of the number of nodes that lie in the given range.
	Problem Constraints
			1 <= Number of nodes in binary tree <= 100000
			0 <= B < = C <= 109
	 */
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int min=1;
		int max=10;
		int answer=nodesInRange(root,min, max);
		System.out.println(answer);

	}
	/*
	 The nodesInRange method  counts the number of nodes in a binary search tree (BST) that have values within a specified range [min, max]. 
	 The counting is done using a pre-order traversal approach.
	
	Time Complexity:
			The time complexity of this method is O(n), where n is the number of nodes in the binary tree. 
			This is because each node is visited exactly once during the pre-order traversal.
	Space Complexity
			The space complexity of the method is O(h), where h is the height of the binary tree. 
			This space is used for the recursive call stack during the traversal. 
			In the best case (balanced tree), the height is logarithmic (O(log n)), and 
			In the worst case (skewed tree), the height is linear (O(n)).
	 */
	static int count;//This static variable is used to keep track of the number of nodes that satisfy the condition within the specified range.
    public static int nodesInRange(TreeNode A, int min, int max) {
    	//Calls the preOrder method to perform a pre-order traversal of the binary tree while updating the count variable based on the values within the specified range.
        preOrder(A,min,max);
        return count;
        
    }
    //This is a helper method that performs a pre-order traversal of the binary tree and updates the count variable based on the values within the specified range.
    static void preOrder(TreeNode root,int min,int max){
        if(root==null){
            return;
        }
        //If the value of the current node is greater than or equal to min and less than or equal to max, 
        //it means the node's value is within the specified range. In this case, the count variable is incremented.
        if(root.val<=max && root.val>=min){
            count++;
        }
        preOrder(root.left,min,max);//The method then proceeds to traverse the left subtree recursively using 
        preOrder(root.right,min,max);//After the left subtree traversal, the method moves to the right subtree recursively using 
    }

}
