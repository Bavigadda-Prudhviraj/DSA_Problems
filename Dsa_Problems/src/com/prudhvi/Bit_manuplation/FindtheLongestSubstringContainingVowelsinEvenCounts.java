package com.prudhvi.Bit_manuplation;

import java.util.HashMap;

import com.prudhvi.trees.CommonNodesinTwoBST;

public class FindtheLongestSubstringContainingVowelsinEvenCounts {

	public static void main(String[] args) {
		System.out.println(findTheLongestSubstring("leetcodeisgreat"));

	}
    public static int findTheLongestSubstring(String s) {
    	int maxLen = 0;
    	StringBuilder sb = new StringBuilder("00000");
    	int currentLen = -1;
    	HashMap<String,Integer> stateHashMap = new HashMap<>();
    	stateHashMap.put("00000", currentLen);
    	for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if(ch == 'a') {
				int num = Character.getNumericValue(sb.charAt(0)) + 1;
				sb.setCharAt(0,Character.forDigit(num, 10));
				
			}else if (ch == 'e') {
				int num = Character.getNumericValue(sb.charAt(1)) + 1;
				System.out.println(Character.forDigit(num, 10)+ " index :" +i);
				sb.setCharAt(1, Character.forDigit(num, 10));
				
			}else if (ch == 'i') {
				int num = Character.getNumericValue(sb.charAt(2)) + 1;
				sb.setCharAt(2, (char)(num+'0'));
			}else if (ch == '0') {
				int num = Character.getNumericValue(sb.charAt(3)) + 1;
				sb.setCharAt(3, Character.forDigit(num, 10));
			}else if (ch == 'u') {
				int num = Character.getNumericValue(sb.charAt(4)) + 1;
				sb.setCharAt(4, Character.forDigit(num, 10));
			}
			if(stateHashMap.containsKey(sb.toString())) {
				int index = stateHashMap.get(sb.toString());
				int currLen = i - index;
				maxLen = Math.max(maxLen, currLen);
				stateHashMap.put(sb.toString(), i);
			}else {
				stateHashMap.put(sb.toString(), i);
			}
		}
    	return maxLen;
        
    }

}
