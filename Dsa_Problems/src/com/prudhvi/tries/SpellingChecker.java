package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.Arrays;

public class SpellingChecker {

	public static void main(String[] args) {
		ArrayList<String> arr1=new ArrayList<>(Arrays.asList("hat", "cat", "rat"));
		ArrayList<String> arr2=new ArrayList<>(Arrays.asList("cat", "ball"));
		ArrayList<Integer> isExist=spellingChecker(arr1,arr2);
		System.out.println(isExist);

	}

	private static ArrayList<Integer> spellingChecker(final ArrayList<String> arr1, final ArrayList<String> arr2) {
		ArrayList<Integer> isExistWords=new ArrayList<>();
		TriesOfChar root=new TriesOfChar();
		for(int i=0;i<arr1.size();i++) {
			singleTrieWithMultipleWords(root,arr1.get(i));
		}
		System.out.println(TriesOfChar.size);
		for(int i=0;i<arr2.size();i++) {
			isExistWords.add(searchWordinTrie(root,arr2.get(i))?1:0);
		}
		
		return isExistWords;
	}
	private static void singleTrieWithMultipleWords(TriesOfChar root,String word){
		TriesOfChar temp=root;
		for(int i=0;i<word.length();i++) {
			int charIndex=word.charAt(i)-'a';
			if(temp.child[charIndex]==null) {
				TriesOfChar newNode=new TriesOfChar();
				temp.child[charIndex]=newNode;
			}
			temp=temp.child[charIndex];
			temp.letterFrequency++;
		}
		temp.isEnd=true;
		temp.wordFrequency++;
		
	}
	private static boolean searchWordinTrie(TriesOfChar root,String word) {
		for(int i=0;i<word.length();i++) {
			 int  charIndex=word.charAt(i)-'a';
			 if(root.child[charIndex]==null) {
				 return false;
			 }
			 root=root.child[charIndex];
					 
		}
		return root.isEnd;
		
	}
	

}
