package com.prudhvi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PreorderTraversalByIterative {

	public static void main(String[] args) {
		ArrayList<Integer> preOrder=new ArrayList<>(Arrays.asList(8,6,2,11,16,10,12,4,14,18,9,15));
		ArrayList<Integer> inOrder =new ArrayList<>(Arrays.asList(11,2,16,6,10,12,8,14,18,4,9,15));
		TreeNode root=BinaryTreeFromInorderAndPreorder.buildTree(preOrder, inOrder);
		ArrayList<Integer> arrayList=preOrder(root);
		System.out.println(arrayList);
	}
	public static ArrayList<Integer> preOrder(TreeNode root){
		ArrayList<Integer> preOrderArrayList=new ArrayList<>();
		Stack<Pair> stack=new Stack<>();
		Pair pair=new Pair(root);
		stack.push(pair);
		while(!stack.isEmpty()){
			if(stack.peek().task==1) {
				stack.peek().task++;
				preOrderArrayList.add(stack.peek().node.val);
			}
			else if (stack.peek().task==2){
				stack.peek().task++;
				if(stack.peek().node.left!=null) {
					Pair leftPair=new Pair(stack.peek().node.left);
					stack.push(leftPair);
				}
			}
			else if (stack.peek().task==3) {
				stack.peek().task++;
				if(stack.peek().node.right!=null) {
					Pair rightPair=new Pair(stack.peek().node.right);
					stack.push(rightPair);
				}
			}
			else {
				stack.pop();
			}	
		}
		return preOrderArrayList;
	}

}
class Pair{
	TreeNode node;
	int task;
	public Pair(TreeNode node) {
		super();
		this.node = node;
		this.task =1;
	}
	
}
