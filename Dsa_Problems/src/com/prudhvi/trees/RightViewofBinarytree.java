package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RightViewofBinarytree {

	public static void main(String[] args) {
		ArrayList<Integer> postOrder=new ArrayList<>(Arrays.asList(11,16,2, 12,10, 6, 18, 14, 15, 9, 4, 8));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList (11,2, 16,6, 10, 12,8,  14, 18, 4, 9,15));
		TreeNode root=BinaryTreeFromInorderAndPostorder.buildTree(inOrder,postOrder);
		ArrayList<Integer> rightViewArrayList=rightViewofBinaryTree(root);
		System.out.println(rightViewArrayList);

	}
	public static ArrayList<Integer> rightViewofBinaryTree(TreeNode root){
		ArrayList<Integer> rightViewArrayList=new ArrayList<>();
		Queue<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			int queueSize=queue.size();
			for(int i=1;i<=queueSize;i++) {
				TreeNode queueFrontNode=queue.poll();
				if(i==queueSize) {
					rightViewArrayList.add(queueFrontNode.val);
				}
				if(queueFrontNode.left!=null) {
					queue.add(queueFrontNode.left);
				}
				if(queueFrontNode.right!=null) {
					queue.add(queueFrontNode.right);
				}
			}
		}
		return rightViewArrayList;
		
	}

}
