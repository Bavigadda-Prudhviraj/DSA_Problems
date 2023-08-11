package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DeleteWordInTrie {

	public static void main(String[] args) {
		ArrayList<String> words=new ArrayList<>(Arrays.asList("hat", "cat", "rat","cat","race","ra"));
		TriesOfChar root=new TriesOfChar();
		for(int i=0;i<words.size();i++) {
			singleTrieWithMultipleWords(root,words.get(i));
		}
		System.out.println(root.letterFrequency+" a letter frequency");
		System.out.println(deleteWordInTrie(root,"rat"));;
		System.out.println(TriesOfChar.isWordExist(root, "rat"));
		System.out.println(TriesOfChar.isWordExist(root, "ra"));
		check(root,"rat");
	}

	private static void check(TriesOfChar root, String string) {
		for(int i=0;i<string.length();i++) {
			int charaIndex=string.charAt(i)-'a';
			if(root.child[charaIndex]==null) {
				System.out.println(i+" "+charaIndex+" test");
				break;
			}
			root=root.child[charaIndex];
		}
		System.out.println("checked");
		
	}

	private static boolean deleteWordInTrie(TriesOfChar root, String word) {
		Stack<TriesOfChar> stack=new Stack<>();
		TriesOfChar temp=root;
		boolean isWordExist=true;
		for(int i=0;i<word.length();i++) {
			int charIndex=word.charAt(i)-'a';
			if(temp.child[charIndex]==null) {
				stack.clear();
				return true;
			}
			stack.add(temp);
			temp=temp.child[charIndex];
			
		}
		System.out.println(stack);
		System.out.println("work");
		if(isWordExist==true) {
			while(!stack.isEmpty()){
				TriesOfChar poppedChar=stack.pop();
				boolean isletterUsed=false;
				int wordLastindex=word.length()-1; //letters index from last letter because in stack letter in reverse order
				int index=-1;						//this index is use full for making child as null
				if(poppedChar.child!=null) {
					for(int i=0;i<poppedChar.child.length;i++) {
						int lastCharIndex=word.charAt(wordLastindex)-'a';//getting the char index r=17,a=0,t=19
						index=lastCharIndex;
						if(poppedChar!=null && i!=lastCharIndex && poppedChar.letterFrequency>1 ){
							isletterUsed=true;
							break;
						}
					}
				}
				if(isletterUsed==false) {
					stack.peek().child[index]=null;
				}
				else {
					stack.clear();
					return true;
				}
				wordLastindex--;
				
			}
		}
		//temp.isEnd=false;
		stack.clear();
		return true;
		
		
	}
	private static void singleTrieWithMultipleWords(TriesOfChar root,String word){
		TriesOfChar temp=root;
		for(int i=0;i<word.length();i++) {
			int charIndex=word.charAt(i)-'a';
			if(temp.child[charIndex]==null) {
				TriesOfChar newNode=new TriesOfChar();
				temp.child[charIndex]=newNode;
			}
			temp.letterFrequency++;//it will increase the frequency of letter(at that index) when ever it is a part of any word
			System.out.println(temp.child[charIndex].letterFrequency+" "+charIndex);
			temp=temp.child[charIndex]; 
			
		}
		temp.isEnd=true;
		temp.wordFrequency++;
		
	}
	

}
