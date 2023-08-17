package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.HashMap;




public class TriesOfCharHM {
	int letterFrequency;
	int wordFrequency;
	boolean isEnd;
	HashMap<Character,TriesOfCharHM> hm;
	//constructor
	public TriesOfCharHM() {
		letterFrequency=0;
		wordFrequency=0;
		isEnd=false;
	}
	//single word insertion
	public static TriesOfCharHM singlewordInsertion(String word) {
		TriesOfCharHM root=new TriesOfCharHM();
		TriesOfCharHM temp=root;
		for(int i=0;i<word.length();i++) {
			char ch=word.charAt(i);
			if(!temp.hm.containsKey(ch)) {
				TriesOfCharHM referance=new TriesOfCharHM();
				temp.hm.put(ch,referance);
			}
			temp=temp.hm.get(ch);
			temp.letterFrequency++;
		}
		temp.isEnd=true;
		temp.wordFrequency++;
		return root;
	}
	//insertion when root is given
	public static void insertion(TriesOfCharHM root,String word) {
		TriesOfCharHM temp=root;
		for(int i=0;i<word.length();i++){
			char character=word.charAt(i);
			if(!temp.hm.containsKey(character)) {
				TriesOfCharHM referance=new TriesOfCharHM();
				temp.hm.put(character, referance);
			}
			temp=temp.hm.get(character);
			temp.letterFrequency++;
		}
		temp.isEnd=true;
		temp.wordFrequency++;
	}
	//searching a word when root is given
	public static boolean isExist(TriesOfCharHM root,String word) {
		for(int i=0;i<word.length();i++) {
			char character=word.charAt(i);
			if(!root.hm.containsKey(character)) {
				return false;
			}
			root=root.hm.get(character);
		}
		return root.isEnd;
	}
	//Deleting word
	public static boolean deleteWord(TriesOfCharHM root,String word) {
		TriesOfCharHM temp=root;
		ArrayList<TriesOfCharHM> track=new ArrayList<>();
		for(int i=0;i<word.length();i++) {
			char character=word.charAt(i);
			temp=temp.hm.get(character);//assuming the word is existed
			track.add(temp);
		}
		return true;
	}

}
