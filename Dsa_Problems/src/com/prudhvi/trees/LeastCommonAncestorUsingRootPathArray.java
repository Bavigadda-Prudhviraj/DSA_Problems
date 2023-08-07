package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Collections;

public class LeastCommonAncestorUsingRootPathArray {
	/*
	Problem Description
			Find the lowest common ancestor in an unordered binary tree A, given two values, B and C, in the tree.
			Lowest common ancestor: 
					the lowest common ancestor (LCA) of two nodes and w in a tree or directed acyclic graph (DAG) is the lowest (i.e., deepest) node that has both v and w as descendants.
	Problem Constraints
			1 <= size of tree <= 100000
			1 <= B, C <= 109
	Input Format
			First argument is head of tree A.
			Second argument is integer B.
			Third argument is integer C.
	Output Format
			Return the LCA.
	 */

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7,8,9,10};
		TreeNode root=SortedArrayToBalancedBST.sortedArrayToBalancedBST(arr);
		int answer=lca(root, 2,10);
		System.out.println(answer);
		int answer2=leastCommonAncestor(root,2,10);
		System.out.println(answer2);

	}
	/*
	This code defines a method leastCommonAncestor that takes a root node of a binary search tree (BST) and two integer values leftChild and rightChild as parameters. 
	The goal of the method is to find the least common ancestor of the nodes with values leftChild and rightChild in the given binary search tree. 
	The method returns the value of the least common ancestor.
	
	Time Complexity:
			In the worst case, the algorithm traverses the height of the binary search tree, which is O(log N) for a balanced tree, and O(N) for a skewed tree.

	Space Complexity:
			The space complexity is O(1), as the algorithm uses a constant amount of extra space for variables regardless of the size of the input tree.
	 */
	public static int leastCommonAncestor(TreeNode root,int leftChild,int rightChild) {
		//This initializes an integer variable answer with -1, which will store the value of the least common ancestor. 
		//The initial value -1 indicates that no common ancestor has been found yet.
		int answer=-1;
		//This is a while loop that iterates as long as the root node is not null. The loop is used to traverse the tree and find the least common ancestor.
		while(root!=null){
			//checks if the values of both leftChild and rightChild are greater than the value of the current root node.
			//If this condition is true, it means both nodes are located on the right subtree, so the code moves the root to its right child using root = root.right;.
			if(root.val<leftChild && root.val<rightChild) {
				root=root.right;
			}
			//This condition checks if the values of both leftChild and rightChild are less than the value of the current root node. 
			//If this condition is true, it means both nodes are located on the left subtree, so the code moves the root to its left child using root = root.left;.
			else if(root.val>leftChild && root.val>rightChild) {
				root=root.left;
			}
			//This is the else part of the conditions, which means the current root node's value is between the values of leftChild and rightChild. In this case, 
			else {
				//the current root is the least common ancestor, so its value is assigned to the answer variable, and 
				answer=root.val;
				//the loop is broken using break;
				break;
			}
		}
		//After the loop ends, the method returns the value of the answer, which represents the least common ancestor of the nodes with values leftChild and rightChild.
		return answer;
	}
	/*
	Time Complexity:
			The time complexity of the lca method is O(n), where n is the number of nodes in the binary tree. 
			The method performs two root-to-node path traversals, each of which takes O(n) time, and the list comparison operations take O(n) time as well.
	
	Space Complexity:
			The space complexity of the method is O(h), where h is the height of the binary tree. 
			This space is used to store the root-to-node paths in the arr1 and arr2 lists, as well as for the recursive call stack during the rootPathFromNodeToRoot method execution. 
			In the worst case, the height of the binary tree could be equal to the number of nodes (in the case of a skewed tree), so the space complexity could be O(n). However, in a balanced binary tree, the height is logarithmic (O(log n)), and the space complexity would be O(log n).
	 */
	public static int lca(TreeNode root, int leftNode, int rightNode) {
        ArrayList<Integer> leftNodePathToRootArr=new ArrayList<>();
        rootPathFromNodeToRoot(root,leftNode,leftNodePathToRootArr);
        ArrayList<Integer> rightNodePathToRootArr=new ArrayList<>();
        rootPathFromNodeToRoot(root,rightNode,rightNodePathToRootArr);
        Collections.reverse(leftNodePathToRootArr);
        Collections.reverse(rightNodePathToRootArr);
        int answer=-1;
        if(leftNodePathToRootArr.size()==0 || rightNodePathToRootArr.size()==0){
           return answer;
        }
        else{
            if(leftNodePathToRootArr.size()<rightNodePathToRootArr.size()){
                for(int i=1;i<leftNodePathToRootArr.size();i++){
                    if(!rightNodePathToRootArr.get(i).equals(leftNodePathToRootArr.get(i)) ){
                        answer=rightNodePathToRootArr.get(i-1);
                        break;
                    }
                }
                if(answer==-1){
                    answer=leftNodePathToRootArr.get(leftNodePathToRootArr.size()-1);
                }
            }
            else if(leftNodePathToRootArr.size()>rightNodePathToRootArr.size() ){
                for(int i=1;i<rightNodePathToRootArr.size();i++){
                    if(!rightNodePathToRootArr.get(i).equals(leftNodePathToRootArr.get(i)) ){
                        answer=rightNodePathToRootArr.get(i-1);
                        break;
                    }
                }
                if(answer==-1){
                    answer=rightNodePathToRootArr.get(rightNodePathToRootArr.size()-1);
                }
            }
            else{
                for(int i=1;i<leftNodePathToRootArr.size();i++){
                    if(!rightNodePathToRootArr.get(i).equals(leftNodePathToRootArr.get(i))){
                        answer=leftNodePathToRootArr.get(i-1);
                        break;
                    }
                }
                if(answer==-1){
                    answer=leftNodePathToRootArr.get(leftNodePathToRootArr.size()-1);
                }
            }
        }
        return answer;
       
    }
    public static boolean rootPathFromNodeToRoot(TreeNode root,int target,ArrayList<Integer> roothPathArrayList){
        if(root==null){
            return false;
        }
        if(root.val==target){
             roothPathArrayList.add(root.val);
            return true;
        }
        else if(rootPathFromNodeToRoot(root.left,target,roothPathArrayList) || rootPathFromNodeToRoot(root.right,target,roothPathArrayList)){
            roothPathArrayList.add(root.val);
            return true;
        }
        return false;
    }

}
