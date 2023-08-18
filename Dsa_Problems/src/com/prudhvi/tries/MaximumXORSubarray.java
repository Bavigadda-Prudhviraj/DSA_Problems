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
		ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(1, 4, 3));//1, 4, 3
		ArrayList<Integer> maxSubArrXor=maxSubArrayXor(arr);
		System.out.println(maxSubArrXor);
	}
	private static ArrayList<Integer> maxSubArrayXor(ArrayList<Integer> arr) {
		ArrayList<Integer> prefixXorList=new ArrayList<>();
		if(arr.size()==1) {
			return new ArrayList<>(List.of(1,1));
		}
		prefixXorList.add(arr.get(0));
		for(int i=1;i<arr.size();i++) {
			prefixXorList.add(prefixXorList.get(i-1)^arr.get(i));
		}
		prefixXorList.add(0);
		System.out.println(prefixXorList);
		ArrayList<Integer> maxSubArrIndexArr=maxSubXor(prefixXorList);
		return maxSubArrIndexArr;
	}

	private static ArrayList<Integer> maxSubXor(ArrayList<Integer> prefixXorList) {
		ArrayList<Integer> maxSubArrIndexArr=new ArrayList<>(List.of(-1,-1));
		int maxNum=findMax(prefixXorList);
		int msb=msb(maxNum);
		TrieNode root=new TrieNode();
		for(int i=0;i<prefixXorList.size();i++) {
			eleInsertIntoTrie(root,prefixXorList.get(i),msb);
		}
		int maxXor=-1;
		int len=0;
		for(int i=0;i<prefixXorList.size();i++) {
			ArrayList<Integer> xor_num=maxXor(root,prefixXorList.get(i),msb);
			if(maxXor<=xor_num.get(0)) {
				int secondIndex=prefixXorList.indexOf(xor_num.get(1));
				int minIndex=Math.min(i, secondIndex+1);
				int maxIndex=Math.max(i, secondIndex);
				if(maxXor<xor_num.get(0)) {
					maxSubArrIndexArr.set(0,minIndex+1);
					maxSubArrIndexArr.set(1,maxIndex);
					len=maxIndex-minIndex+1; 
				}
				else if(maxXor==xor_num.get(0)){
					if(len==(maxIndex-minIndex+1)) {
						if(minIndex>maxSubArrIndexArr.get(0)) {
							maxSubArrIndexArr.set(0,minIndex);
							maxSubArrIndexArr.set(1,maxIndex);
						}
						
					}
					else {
						maxSubArrIndexArr.set(0,minIndex);
						maxSubArrIndexArr.set(1,maxIndex);
					}
					
				}
				
				maxXor=xor_num.get(0);
			}
		}
		return maxSubArrIndexArr;
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
	private static void eleInsertIntoTrie(TrieNode root, int num, int msb) {
		TrieNode temp=root;
		for(int i=msb;i>=0;i--){
			int digit=(num&(1<<i))>0?1:0;
			if(temp.child[digit]==null) {
				temp.child[digit]=new TrieNode();
			}
			temp=temp.child[digit];
			temp.index=i;
		}
	
	}
	private static ArrayList<Integer> maxXor(TrieNode root, int num, int msb) {
		TrieNode temp=root;
		int xor=0;
		for(int i=msb;i>=0;i--) {
			int digit=(num&(1<<i))>0?1:0;
			if(temp.child[1-digit]!=null) {
				xor+=(1<<i);
				temp=temp.child[1-digit];
			}else {
				temp=temp.child[digit];
			}
		}
		int index=temp.index;
		int anotherNum=xor^num;
		ArrayList<Integer> xor_num=new ArrayList<>(List.of(xor,anotherNum,index));
		System.out.println(" xorValue: "+xor+" num: "+num+" anotherNum: "+anotherNum );
		return xor_num;
	}
}
class TrieNode{
	TrieNode[] child;
	int index;
	public TrieNode() {
		child=new TrieNode[2];
		index=-1;
	}
}
