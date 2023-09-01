package com.prudhvi.tries;

import java.util.HashMap;
import java.util.Map;

	/*
	Write a function to find the longest common prefix string amongst an array of strings.
	If there is no common prefix, return an empty string "".
		Example 1:
			Input: strs = ["flower","flow","flight"]
			Output: "fl"
		Example 2:
			Input: strs = ["dog","racecar","car"]
			Output: ""
			Explanation: There is no common prefix among the input strings.
		Constraints:
			1 <= strs.length <= 200
			0 <= strs[i].length <= 200
			strs[i] consists of only lower case English letters.
	 */

public class LongestCommonPrefix {
	public static void main(String[] args) {
		String[] words= {"prudhvi","prudhvi","prudhvi"};
		String longestCommonPrefix=longestCommonPrefix(words);
		System.out.println(longestCommonPrefix);

	}
	/*
	
	Time Complexity:
		Building the Trie takes O(N*M) time, where N is the number of words and M is the average length of the words.
		Finding the longest common prefix takes O(L) time, where M is the length of the longest common prefix.
		Therefore, the overall time complexity is O(NM + L), which simplifies to O(NM).
	Space Complexity:
		The space complexity is determined by the Trie data structure and is O(N*M) due to the worst-case scenario where all words are distinct and have no common prefix.
	 */
	private static String longestCommonPrefix(String[] words) {
		TrieLCP trie=new TrieLCP();
		int longLenString=0;
		for(int i=0;i<words.length;i++) {
			int len=insetWordsIntoTrie(trie,words[i]);
			longLenString=Math.max(len, longLenString);
		}
		char tempChar='-';//A temporary character to store the current character being examined.
		int tempFre=Integer.MIN_VALUE;// A temporary frequency count of characters in the Trie.
		boolean flag=true;//: A boolean flag to control the loop.
		StringBuilder str=new StringBuilder();//A StringBuilder to build the longest common prefix.
		TrieLCP temp=trie;// temporary TrieLCP object used for traversing.
		int cnt=-1;//A counter to keep track of the number of characters in the longest common prefix.
		while(flag){
			HashMap<Character, TrieLCP> hm=temp.hm;
			for (Map.Entry<Character, TrieLCP> entry : hm.entrySet()) {
				Character key = entry.getKey();
				temp=temp.hm.get(key);
				//If cnt is -1 (indicating it's the first character), and the letterFrequency of temp2 is less than the total number of words, it sets flag to false and breaks out of the loop.
				//if first letter is not common in every word means empty
				if(cnt==-1 && temp.letterFrequency<words.length) {
					flag=false;
					break;
				}
				//prud,prudh in this case the preFre is 4 new is 1 we have to consider past answer break
				if(temp.letterFrequency<tempFre) {
					flag=false;
					break;
				}
				
				if(temp.letterFrequency>=tempFre) {
						tempChar=key;
						tempFre=temp.letterFrequency;
				}
			}
			cnt++;
			if(cnt==longLenString) {
				break;
			}
			if(!flag) {
				break;
			}
			str.append(tempChar);
		}
		return str.toString();
	}

	private static int insetWordsIntoTrie(TrieLCP root, String word) {
		TrieLCP temp=root;
		int len=0;
		for(int i=0;i<word.length();i++) {
			len++;
			char ch=word.charAt(i);
			if(!temp.hm.containsKey(ch)) {
				temp.hm.put(ch,new TrieLCP());
			}
			temp=temp.hm.get(ch);
			temp.letterFrequency++;
		}
		temp.isEnd=true;
		return len;
		
		
	}
}
class TrieLCP{
	HashMap<Character, TrieLCP> hm;
	int letterFrequency;
	boolean isEnd;
	public TrieLCP() {
		hm=new HashMap<>();
		isEnd=false;
		letterFrequency=0;
	}
}
