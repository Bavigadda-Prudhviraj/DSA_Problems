package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.Arrays;

public class ShortestUniquePrefix {
	/*
	Problem Description
		Given a list of N words, find the shortest unique prefix to represent each word in the list.
		NOTE: Assume that no word is the prefix of another. In other words, the representation is always possible
	Problem Constraints
		1 <= Sum of length of all words <= 106
	Input 1:
		A = ["zebra", "dog", "duck", "dove"]-->["z", "dog", "du", "dov"]
		A = ["apple", "ball", "cat"]-->["a", "b", "c"]
	 */
	public static void main(String[] args) {
		ArrayList<String> words=new ArrayList<>(Arrays.asList("zebra", "dog", "duck","dot"));
		ArrayList<String> prefixArrayList=createdTrieAndSortestUniquePrefixes(words);
		System.out.println(prefixArrayList);
		
	}
	/*
	 the function implementation of a Trie data structure to find the shortest unique prefixes for a list of words. 
	 The code creates a Trie using the TriesOfChar class and then uses it to find the shortest unique prefixes for each word.
	
	Time Complexity:
			1.The createdTrieAndSortestUniquePrefixes function iterates through each word and inserts them into the trie, 
			  which takes O(N*M) time, where N is the number of words and M is the average length of words.
			2.The sortestUniquePrefix function iterates through each word and, for each word, traverses the trie, 
			  which also takes O(N*M) time.
		Therefore, the overall time complexity is O(N*M).
			
	Space Complexity:
			1.The space complexity primarily depends on the storage used for the trie, 
			  which is constructed in the createdTrieAndSortestUniquePrefixes function. 
			  The space complexity of the trie structure itself is not provided in this code snippet.
			2.Additionally, space is used to store the shortestPrefixList and the local variables like string, prefix, and temp.
			3.In the worst case, where all words are unique and share no common prefixes, the space complexity of the shortestUniquePrefixList is O(N*M), where N is the number of words and M is the average length of words.
		Overall, the space complexity is influenced by the trie and the shortestPrefixList.
	 */
	private static ArrayList<String> createdTrieAndSortestUniquePrefixes(ArrayList<String> words){
		TriesOfChar root=new TriesOfChar();//It creates the root node of the Trie.
		//It then iterates through each word in the input list and inserts each word into the Trie using the singleTrieWithMultipleWords method.
		for(int i=0;i<words.size();i++){
			TriesOfChar.singleTrieWithMultipleWords(root,words.get(i));
		}
		ArrayList<String> sortestPrefixList=sortestUniquePrefix(root,words);// Find shortest unique prefixes
		return sortestPrefixList;
		
	}
	private static ArrayList<String> sortestUniquePrefix(TriesOfChar root, ArrayList<String> words) {
		ArrayList<String> sortestUniquePrifixList=new ArrayList<>();
		for(int i=0;i<words.size();i++) {
			String string=words.get(i);//word form arrayList
			StringBuilder prefix=new StringBuilder();////It initializes a StringBuilder to construct the prefix.
			TriesOfChar temp=root;// Initialize a temporary Trie node to traverse the Trie
			//It iterates through each character of the word and follows the Trie nodes in the root to find the shortest unique prefix.
			for(int j=0;j<string.length();j++) {
				int charIndex=string.charAt(j)-'a';
				temp=temp.child[charIndex];// Move to the next Trie node
				//It checks the letterFrequency of the current Trie node. If it's 1, it means the current character is unique in the Trie, 
				//so it adds the character to the prefix and breaks the loop.
				if(temp.letterFrequency==1) {// If the letter is unique in the Trie, add it to the prefix
					prefix.append(string.charAt(j));
					break;
				}
				//Otherwise, it appends the character to the prefix.
				prefix.append(string.charAt(j));
			}
			sortestUniquePrifixList.add(prefix.toString());// Add the found prefix to the list
		}
		return sortestUniquePrifixList;
	}
	/*
	Time Complexity:
			Building the Trie: O(total_characters_in_all_words)
			Finding shortest unique prefixes: O(total_characters_in_all_words)
			Total time complexity: O(total_characters_in_all_words)
	Space Complexity: 
			O(total_characters_in_all_words)
	 */

}
