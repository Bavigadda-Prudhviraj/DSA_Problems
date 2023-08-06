package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
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
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		int answer=lca(root, 18, 15);
		System.out.println(answer);

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
	public static int lca(TreeNode A, int B, int C) {
        ArrayList<Integer> arr1=new ArrayList<>();
        rootPathFromNodeToRoot(A,B,arr1);
        ArrayList<Integer> arr2=new ArrayList<>();
        rootPathFromNodeToRoot(A,C,arr2);
        Collections.reverse(arr1);
        Collections.reverse(arr2);
        int answer=-1;
        if(arr1.size()==0 || arr2.size()==0){
            answer=-1;
        }
        else{
            if(arr1.size()<arr2.size()){
                for(int i=1;i<arr1.size();i++){
                    if(!arr2.get(i).equals(arr1.get(i)) ){
                        answer=arr2.get(i-1);
                        break;
                    }
                }
                if(answer==-1){
                    answer=arr1.get(arr1.size()-1);
                }
            }
            else if(arr1.size()>arr2.size() ){
                for(int i=1;i<arr2.size();i++){
                    if(!arr2.get(i).equals(arr1.get(i)) ){
                        answer=arr2.get(i-1);
                        break;
                    }
                }
                if(answer==-1){
                    answer=arr2.get(arr2.size()-1);
                }
            }
            else{
                for(int i=1;i<arr1.size();i++){
                    if(!arr2.get(i).equals(arr1.get(i))){
                        answer=arr1.get(i-1);
                        break;
                    }
                }
                if(answer==-1){
                    answer=arr1.get(arr1.size()-1);
                }
            }
        }
        if(answer==-1){
            return -1;
        }else{
            return answer;
        }
       
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
