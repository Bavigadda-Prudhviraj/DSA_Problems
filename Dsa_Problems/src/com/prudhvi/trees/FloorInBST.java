package com.prudhvi.trees;
/*
	You are given a BST(Binary Search Tree) with n number of nodes and value x. 
	your task is to find the greatest value node of the BST which is smaller than or equal to x.
	
	Note: when x is smaller than the smallest node of BST then returns -1.
 */
public class FloorInBST {
	
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8};
		TreeNode root1=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int target=9;
		int floor=floor(root1, target);
		System.out.println(floor);

	}
	/*
	Overall, the code works by recursively traversing the BST, making decisions based on the comparison between the node's value and the target value. 
	When it finds a node with a value equal to the target, it records it as the floor value. 
	If it doesn't find an exact match, it keeps track of the current best floor candidate using globalVar.
	
	Time Complexity:
		The time complexity of this code is O(H), where H is the height of the BST. In the worst case, the code may need to traverse the entire height of the tree to find the floor value.
	Space Complexity:
		The space complexity is O(H), where H is the height of the BST. This space is used for the call stack during the recursive function calls.
	 */
	//global variable globalVar and initializes it to -1. This variable will be used to store the floor value.
	static int globalVar=-1;
    public static int floor(TreeNode root, int x) {
        int floor=-1;//initializes a local variable floor and sets it to -1. This variable will store the floor value to be returned.
        bstTree(root,x);
        //After the recursive call to bstTree, it updates the floor variable with the value stored in the global variable globalVar.
        floor=globalVar;
        //It resets the globalVar to -1 (to prepare for future calls)
        globalVar=-1;
        //returns the floor value, which is the floor of the target value in the BST.
        return floor;
    }
    public static  void bstTree(TreeNode root,int target){
    	//If the root is null (i.e., we've reached a leaf node without finding the floor), the function returns.
        if(root==null){
            return;
        }
        //It checks the value of the current root node (data) against the target value:
        int data=root.val;
        //If data is greater than target, it recursively searches in the left subtree (root.left) since the floor value cannot be in the right subtree.
        if(data>target){
            bstTree(root.left, target);
        }
        //If data is equal to target, it means we have found the exact value, and 
        if(data==target){
            globalVar=data;//it sets globalVar to data, effectively finding the floor. 
            return;//It then returns.
        }
        //If data is less than target, 
        else if(data<target){
            globalVar=data;//it updates globalVar to the current data value (as it's a potential floor value) and 
            bstTree(root.right,target);//searches in the right subtree (root.right).
        }
    }

}
