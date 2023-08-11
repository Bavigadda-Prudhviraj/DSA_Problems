package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFrequencyOfWordInTrie {

	public static void main(String[] args) {
		ArrayList<String> words=new ArrayList<>(Arrays.asList("hat", "cat", "rat","cat","rat"));
		TriesOfChar root=new TriesOfChar();
		for(int i=0;i<words.size();i++) {
			TriesOfChar.singleTrieWithMultipleWords(root,words.get(i));
		}
		int frequency=checkFrequency(root,"hat");
		System.out.println(frequency);

	}
	public static int checkFrequency(TriesOfChar root,String word) {
		TriesOfChar temp=root;
		for(int i=0;i<word.length();i++) {
			int charIndex=word.charAt(i)-'a';
			if(temp.child[charIndex]==null) {
				return 0;
			}
			temp=temp.child[charIndex];
		}
		return temp.wordFrequency;
		
	}

}
