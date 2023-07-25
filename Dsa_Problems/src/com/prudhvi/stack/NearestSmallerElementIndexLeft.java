package com.prudhvi.stack;

import java.util.Arrays;
import java.util.Stack;

public class NearestSmallerElementIndexLeft {

	public static void main(String[] args) {
		int[] arr= {4, 5, 2, 10, 8};
		int[] answerArr=nerestSmallestElementIndex(arr);
		System.out.println(Arrays.toString(answerArr));
		

	}

	private static int[] nerestSmallestElementIndex(int[] arr) {
		int[] nerestSmallementsIndexces=new int[arr.length];
		Stack<Integer> indexStack=new Stack<>();
		for(int i=0;i<arr.length;i++) {
			while(!indexStack.isEmpty() && arr[indexStack.peek()]>=arr[i]){
				indexStack.pop();
			}
			if(!indexStack.isEmpty()) {
				nerestSmallementsIndexces[i]=indexStack.peek();
			}
			else {
				nerestSmallementsIndexces[i]=-1;
			}
			indexStack.push(i);
		}
		return nerestSmallementsIndexces;
	}

}
