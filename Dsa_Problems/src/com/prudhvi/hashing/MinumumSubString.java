package com.prudhvi.hashing;

import java.util.Arrays;

public class MinumumSubString {

	public static void main(String[] args) {
		String A = "ADOBECODEBANC";//ADOBECODEBANC
		String B = "ABC";
		String answerString=minimumSubStringOfString(A, B);
		System.out.println(answerString);
		

	}
	public static String minimumSubStringOfString(String string,String  subString){
		int[] stringFrequency = new int[256];
        int[] subStringFrequency = new int[256];
        for (char c : subString.toCharArray()) {
            subStringFrequency[c]++;
        }
        int start = 0;
        int end = 0;
        int size = Integer.MAX_VALUE;
        int pointerI = 0;
        int pointerJ = 0;
        while (pointerJ < string.length()) {
            int jCharacter =(int)string.charAt(pointerJ);
            stringFrequency[jCharacter]++;
            while(checkFrequencyOfTwoArrays(subStringFrequency, stringFrequency)) {
                if ((pointerJ - pointerI + 1) < size) {
                    start = pointerI;
                    end = pointerJ;
                    size = pointerJ - pointerI + 1;
                }
                int iCharacter =(int)string.charAt(pointerI);
                stringFrequency[iCharacter]--;
                pointerI++;
            }
            pointerJ++;
        }

        if (size == Integer.MAX_VALUE) {
            return "";
        }
        return string.substring(start, end + 1);
	}
    public static boolean checkFrequencyOfTwoArrays(int[] subString, int[] string) {
        for (int i = 0; i<subString.length; i++) {
            if (string[i]<subString[i]) {
                return false;
            }
        }
        return true;
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static String minimumSubStringOfString(String string,String  subString){
//		int[] stringFrequency=new int[257];
//		int[] subStringFrequency=new int[257];
////		for (int i = 0; i < string.length(); i++) {
////			int index=(int)string.charAt(i);
////			stringFrequency[index]++;
////		}
//		for (int i = 0; i < subString.length(); i++) {
//			int index=(int)subString.charAt(i);
//			subStringFrequency[index]++;
//		}
//		System.out.println(Arrays.toString(subStringFrequency));
//		int start=-1;
//		int end=-1;
//		int size=Integer.MAX_VALUE;
//		int pointerI=0;
//		int pointerJ=0;
//		while (pointerJ<string.length()){
//			int iCharacter=(int)string.charAt(pointerI);
//			int jCharacter=(int)string.charAt(pointerJ);
//			System.out.println("i-Char:"+iCharacter+" j-char:"+jCharacter);
//			System.out.println("i-point:"+pointerI+" j-point:"+pointerJ);
//			if(pointerI==pointerJ) {
//				stringFrequency[jCharacter]++;
//				System.out.println("i==j "+Arrays.toString(stringFrequency));
//			}
//			else {
//				stringFrequency[iCharacter]++;
//				stringFrequency[jCharacter]++;
//			}
//			if(checkFrequencyOfTwoArrays(subStringFrequency,stringFrequency)){
//				if((pointerJ-pointerI+1)<size) {
//					System.out.println("True");
//					start=pointerI;
//					end=pointerJ;
//					size=pointerJ-pointerI+1;
//					stringFrequency[iCharacter]--;
//					pointerI++;
//				}
//			}
//			else {
//				pointerJ++;
//				stringFrequency[jCharacter]++;	
//			}		
//		}
//		System.out.println(Arrays.toString(stringFrequency));
//		System.out.println(Arrays.toString(subStringFrequency));
//		return string.substring(start,end);
//	}
//	public static boolean checkFrequencyOfTwoArrays(int[] subString,int[] string) {
//		for(int i=0;i<string.length;i++) {
//			if(string[i]<subString[i]) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//}
