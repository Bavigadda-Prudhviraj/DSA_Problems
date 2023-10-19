package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.HashMap;



public class WordBreak {

	public static void main(String[] args) {
		String str = "sisirad";
		String[] dictionary = {"s1","s1r","ad" };
		int canBreak=checkCanBreak(str,dictionary);
		System.out.println(canBreak);
		int canBreak1=checkCanBreakDp(str,dictionary);
		System.out.println(canBreak1);


	}

	private static int checkCanBreakDp(String str, String[] dictionary) {
		String[] dp=new String[str.length()];
		dp[0]=Character.toString(str.charAt(0));
		for(int i=1;i<str.length();i++) {
			dp[i]=dp[i-1]+str.charAt(i);
		}
		TrieWB root=new TrieWB();
		for(int i=0;i<dictionary.length;i++) {
			insertIntoRoot(root,dictionary[i]);
		}
		return 0;
	}

	private static int checkCanBreak(String str, String[] dictionary) {
		TrieWB root=new TrieWB();
		for(int i=0;i<dictionary.length;i++) {
			insertIntoRoot(root,dictionary[i]);
		}

		StringBuilder tempStr=new StringBuilder();
		for(int i=0;i<str.length();i++) {
			tempStr.append(str.charAt(i));
			if(checkIsExist(root,tempStr.toString())) {
				tempStr.delete(0,tempStr.length());
			}
		}
		return tempStr.length()==0?1:0;
	}

	private static boolean checkIsExist(TrieWB root, String tempStr) {
		TrieWB temp=root;
		for(int i=0;i<tempStr.length();i++) {
			char ch=tempStr.charAt(i);
			if(!temp.map.containsKey(ch)) {
				return false;
			}
			temp=temp.map.get(ch);
		}
		return temp.isEnd;
	}

	private static void insertIntoRoot(TrieWB root, String string) {
		TrieWB temp=root;
		for(int i=0;i<string.length();i++) {
			char ch=string.charAt(i);
			if(!temp.map.containsKey(ch)) {
				temp.map.put(ch,new TrieWB());
			}
			temp=temp.map.get(ch);
		}
		temp.isEnd=true;
		
	}
	


}
class TrieWB{
	HashMap<Character, TrieWB> map;
	boolean isEnd;
	public TrieWB() {
		map=new HashMap<>();
		isEnd=false;
	}
}
/*
This code is checking if a given string `str` can be broken into words from a provided dictionary of words. It utilizes a Trie data structure to efficiently perform this task. Let's break down the code step by step:

1. `private static int checkCanBreakDp(String str, String[] dictionary)`:
   - This function is a placeholder for an implementation that uses dynamic programming (DP) but currently returns `0`.

2. `private static int checkCanBreak(String str, String[] dictionary)`:
   - This function performs the actual check to determine if the string `str` can be broken into words from the dictionary.
   - It initializes a `TrieWB` data structure called `root`, which represents a trie to store the dictionary words.
   - It inserts each word from the `dictionary` into the `root` using the `insertIntoRoot` function.
   - It iterates through each character of `str` and appends the characters to a `tempStr` StringBuilder.
   - It checks if the `tempStr` exists in the `root` (i.e., if it forms a complete word). If so, it clears `tempStr`.
   - Finally, it returns `1` if `tempStr` is empty (meaning `str` can be broken into words from the dictionary), and `0` otherwise.

3. `private static boolean checkIsExist(TrieWB root, String tempStr)`:
   - This function checks if a given string `tempStr` exists in the trie, starting from the root.
   - It traverses the trie node by node, character by character, checking if the characters in `tempStr` are present in the trie.
   - If at any point a character in `tempStr` is not found in the trie, it returns `false`.
   - If the traversal reaches the end of `tempStr`, and the current node in the trie represents the end of a word (i.e., `isEnd` is true), it returns `true`, indicating that `tempStr` forms a complete word in the dictionary.

4. `private static void insertIntoRoot(TrieWB root, String string)`:
   - This function inserts a word `string` into the trie.
   - It starts the traversal from the `root` node and iterates through the characters of `string`.
   - For each character, it checks if there is a corresponding child node in the trie. If not, it creates a new node for that character and attaches it to the current node.
   - After inserting all characters from the word, it marks the last node as the end of a word by setting `isEnd` to `true`.

This code essentially checks if the given string `str` can be broken into words from the dictionary, utilizing a Trie data structure for efficient word lookup. The function returns `1` if `str` can be broken into words from the dictionary, and `0` otherwise. The time complexity of this algorithm depends on the size of the dictionary and the length of the input string.
 */
