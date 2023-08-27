package com.prudhvi.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordBreak {
	/*
	Given a string A and a dictionary of words B, determine if A can be segmented into a space-separated sequence of one or more dictionary words.
	 */
	public static void main(String[] args) {
		String searchString="baaaaabbabaaababaabbbba";
		ArrayList<String> words=new ArrayList<>(List.of("aaa","abbabbbabb","bbaaababa","aba","bab","bba","baa","aa","baabaaaaa","ababbaaaa","aaaaaa","b","aaabb","aaaaba","babbbaaba","b","babbb","bbaaaaa","bbaaa","baaaaaa","aa","aaabba","baaabaa","bbabbab","abbb","bbabbb","aaabaaa","a","aaabbabbaa","baaaaab","baabbbab","ba","baab"));
		int canBreak=search(searchString,words);
		System.out.println(canBreak);

	}
	public static int ouput=0;
	private static int search(String searchString, ArrayList<String> words) {
		HashSet<String> set=new HashSet<>();
		for(int i=0;i<words.size();i++) {
			set.add(words.get(i));
		}
		
		helper(searchString,set);
		return ouput;
	}

	private static boolean helper(String string, HashSet<String> set) {
		if(0==string.length()) {
			ouput=1;
			return true;
		}
		for(int i=1;i<=string.length();i++) {
			String subString=string.substring(0, i);
			if(set.contains(subString)) {
				if(helper( string.substring(i), set)) {
					return true;
				}
			}
		}
		return false;
	}

}
