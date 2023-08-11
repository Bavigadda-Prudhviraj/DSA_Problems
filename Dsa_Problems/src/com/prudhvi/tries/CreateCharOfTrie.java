package com.prudhvi.tries;

public class CreateCharOfTrie {
	public static void main() {
		String word="prudhviraj";
		createCharTrie(word);
		
		
	}

	private static TriesOfChar createCharTrie(String word) {
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
		return root;
	}

}
