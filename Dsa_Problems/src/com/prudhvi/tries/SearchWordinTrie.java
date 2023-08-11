package com.prudhvi.tries;

public class SearchWordinTrie {

	public static void main(String[] args) {
		String name="prudhviraj";
		TriesOfChar root=TriesOfChar.createCharTrie(name);
		boolean isWordExist=isWordExist(root,"prudhviraj");
		System.out.println(isWordExist);
		

	}

	private static boolean isWordExist(TriesOfChar root, String word) {
		for(int i=0;i<word.length();i++) {
			int charIndex=word.charAt(i)-'a';
			if(root.child[charIndex]==null) {
				return false;
			}
			root=root.child[charIndex];
		}
		return root.isEnd;
	}

}
