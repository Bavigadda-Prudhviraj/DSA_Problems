package com.prudhvi.tries;

import java.util.Arrays;

class TriesOfChar {
	public static int size;
	TriesOfChar[] child;
	boolean isEnd;
	int wordFrequency;
	int letterFrequency;
	
	TriesOfChar(){
		child=new TriesOfChar[26];
		isEnd=false;
		wordFrequency=0;
		letterFrequency=0;
	}
	public static TriesOfChar createCharTrie(String word) {
		TriesOfChar root=new TriesOfChar();
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
		size++;
		return root;
	}
	public static boolean isWordExist(TriesOfChar root, String word) {
		TriesOfChar temp=root;
		for(int i=0;i<word.length();i++) {
			int charIndex=word.charAt(i)-'a';
			if(temp.child[charIndex]==null) {
				return false;
			}
			temp=temp.child[charIndex];
		}
		return temp.isEnd;
	}
	public static void singleTrieWithMultipleWords(TriesOfChar root,String word){
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
		size++;
	}
	
	
	
}
