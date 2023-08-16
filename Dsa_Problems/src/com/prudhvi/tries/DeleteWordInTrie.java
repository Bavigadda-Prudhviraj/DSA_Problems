package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.Arrays;


public class DeleteWordInTrie {

	public static void main(String[] args) {
		ArrayList<String> words=new ArrayList<>(Arrays.asList("hat", "cat", "rat","cat","race","ra"));
		TriesOfChar root=new TriesOfChar();
		for(int i=0;i<words.size();i++) {
			singleTrieWithMultipleWords(root,words.get(i));//perfect
		}
		System.out.println(deleteWordInTrie(root,"rat"));;//true
		System.out.println(TriesOfChar.isWordExist(root, "rat")); //false
		System.out.println(TriesOfChar.isWordExist(root, "ra"));//true
		check(root,"rat");
	}

	private static void check(TriesOfChar root, String string) {
		for(int i=0;i<string.length();i++) {
			int charaIndex=string.charAt(i)-'a'; //17
			if(root.child[charaIndex]==null) {   
				System.out.println(string.charAt(i)+"=null");
				break;
			}
			root=root.child[charaIndex];
		}
		
	}
	private static boolean deleteWordInTrie(TriesOfChar root,String word) {
		ArrayList<TriesOfChar> track = new ArrayList<>();
		TriesOfChar temp = root;
		for(int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if(temp.child[idx] == null) {
				return false;
			}
			temp = temp.child[idx];
			temp.letterFrequency--;
			track.add(temp);
		}
		
		if(temp.isEnd == false) {
			return false;
		}
		temp.isEnd=false;
		// valid word
		// backtrack the stack
		int N = track.size(); //it will be same as length of the string as we are storing all characters in the track
		for(int i = N - 1; i >=0; i--) {
			int index = word.charAt(i) - 'a';// we are iterating from right to left so i will last character
			temp = track.get(i);
			// check number of branches
			boolean empty = true;
			for(int j = 0; j < 26; j++) {
				if(temp.child[j] != null) {
					empty = false;
					break;
				}
			}
			if(empty) {
				// eligible for remove
				TriesOfChar parent = track.get(i - 1);
				parent.child[index] = null;
			} else {
				// break
				break;
			}
		}
		
		return true;
	}

//	private static boolean deleteWordInTrie(TriesOfChar root, String word) {
//		Stack<TriesOfChar> stack=new Stack<>();
//		TriesOfChar temp=root;
//		boolean isWordExist=true;
//		for(int i=0;i<word.length();i++) {
//			int charIndex=word.charAt(i)-'a';
//			if(temp.child[charIndex]==null) {
//				stack.clear();
//				return true;
//			}
//			stack.add(temp);
//			temp=temp.child[charIndex];
//			
//		}
//		System.out.println(stack);
//		if(isWordExist==true) {
//			while(!stack.isEmpty()){
//				TriesOfChar stackTopTrie=stack.pop();// trie type with 26 array
//				if(stackTopTrie.child!=null) {
//					boolean isletterUsed=false;
//					int wordLastindex=word.length()-1; //letters index from last letter because in stack letter in reverse order
//					int index=-1;						//this index is use full for making child as null
//					for(int i=0;i<stackTopTrie.child.length;i++) {
//						//stackTopTrie=stackTopTrie.child[i];
//						int lastCharIndex=word.charAt(wordLastindex)-'a';//getting the char index r=17,a=0,t=19
//						index=lastCharIndex;
//						//if any char in not null then it is not leaf tires we can delete
//						//stackTopTrie.child[lastCharIndex] it means that ending char is part of another letter
//						if(stackTopTrie.child[i]!=null &&  //all should be null
//								stackTopTrie.child[lastCharIndex]!=null && 
//								i!=lastCharIndex && //is nor equal to char index
//								stackTopTrie.child[lastCharIndex].letterFrequency>1){ //that latter frequency should be >1
//							isletterUsed=true;
//							//break;
//						}
//					}
//					//all remaining are null that char is not used in any other word
//					if(isletterUsed==false && !stack.isEmpty()) {
//						stack.peek().child[index]=null;
//					}
//					//that char is is part of another word so we not deleting it
//					else {
//						stack.clear();
//						return true;
//					}
//					wordLastindex--;
//				}
//				
//				
//			}
//		}
//		//edit
//		temp.isEnd=false;
//		stack.clear();
//		return true;
//		
//		
//	}
	//perfectly working
	private static void singleTrieWithMultipleWords(TriesOfChar root,String word){
		TriesOfChar temp=root;
		for(int i=0;i<word.length();i++) {
			int charIndex=word.charAt(i)-'a';
			//System.out.println(word.charAt(i)+" "+charIndex);
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
}
/*
 /*
 
 package programming;

import java.util.ArrayList;

class TrieNode {
	TrieNode children[] = new TrieNode[26];
	boolean end = false;
}

public class TrieDS {
	
	TrieNode root = new TrieNode();

	// add to trie
	void add(String word) {
		TrieNode temp = root;
		for(int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if(temp.children[idx] == null) {
				temp.children[idx] = new TrieNode();
			}
			temp = temp.children[idx];
		}
		temp.end = true;
	}
	
	// find in trie
	boolean find(String word) {
		TrieNode temp = root;
		for(int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if(temp.children[idx] == null) {
				return false;
			}
			temp = temp.children[idx];
		}
		return temp.end;
	}
	
	// delete from trie
	boolean delete(String word) {
		ArrayList<TrieNode> track = new ArrayList<>();
		TrieNode temp = root;
		for(int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if(temp.children[idx] == null) {
				// break the process
				return false;
			}
			temp = temp.children[idx];
			track.add(temp);
		}
		
		if(temp.end == false) {
			return false;
		}
		
		// valid word
		// backtrack the stack
		int N = track.size();
		for(int i = N - 1; i >=0; i--) {
			int index = word.charAt(i) - 'a';
			temp = track.get(i);
			// check number of branches
			boolean empty = true;
			for(int j = 0; j < 26; j++) {
				if(temp.children[j] != null) {
					empty = false;
				}
			}
			
			//System.out.println(word.charAt(i) + " " + empty);
			
			if(empty) {
				// eligible for remove
				TrieNode parent = track.get(i - 1);
				parent.children[index] = null;
			} else {
				// break
				break;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// test
		TrieDS trie = new TrieDS();
		
		trie.add("rat");
		trie.add("race");
		trie.add("ra");
		
		System.out.println("Found: " + trie.find("rat"));
		System.out.println("Deleted: " + trie.delete("rat"));
		System.out.println("Found: " + trie.find("rat"));
		System.out.println("Found: " + trie.find("ra"));
	}
}

 */

 
