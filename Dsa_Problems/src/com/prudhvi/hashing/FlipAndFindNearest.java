package com.prudhvi.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class FlipAndFindNearest {

	public static void main(String[] args) {
//		String  A= "010000100";
//		int[][]	B = {{2, 5},
//				     {1, 7},
//				     {2, 9}};
		String  A = "10001010"; //5 7 1 2 -1
		int[][]	B ={{2,5},
					{1,5},
					{2,5},
					{1,7},
					{2,8},
					{1,2},
					{2,5},
					{1,2},
					{1,1},
					{2,4},
					{1,4},
					{1,3}};
		int[] ans=findAndReplace(A,B);
		System.out.println(Arrays.toString(ans));
	}
	/*
	The findAndReplace method takes a string str and a 2D integer array arr as inputs. 
	It performs two types of operations on the string: 
		type 1 operation replaces a character in the string, and 
		type 2 operation finds the nearest type 1 character from a given index. 
	The method returns an array containing the results of type 2 operations.
	
	
	Time Complexity:
			The time complexity of this code depends on the length of the string (str) and the size of the array (arr). 
			If the string has length n and the array has m rows, the time complexity would be O(n + m), as we iterate over the string once to find the '1' indices and iterate over the array elements. 
			TreeSet operations, such as add, remove, ceiling, and floor, have a time complexity of O(m log n) on average.
			Therefore, the overall time complexity of the code is O(n + m log n), where n is the length of the input string and m is the number of operations.
	Space Complexity:
			The space complexity of the code is O(n + m), as it uses a TreeSet to store the '1' indices and an ArrayList to store the results of Type 2 operations. 
			The size of the TreeSet would be at most the number of '1' characters in the string (n), and the size of the ArrayList would be at most the number of Type 2 operations (m).
	 */

	public static int[] findAndReplace(String str, int[][] arr) {
		//which will store the indices of type 1 characters in the string.
		TreeSet<Integer> typeOneElements=new TreeSet<>();
		for(int i=0;i<str.length();i++) {
			//loop iterates over the string and adds the indices of type 1 characters to the typeOneElements TreeSet:
			if(str.charAt(i)=='1') {
				typeOneElements.add(i+1);   
			}
		}
		//which will store the results of type 2 operations.
		ArrayList<Integer> elements=new ArrayList<>();
		//loop iterates over the 2D integer array arr to perform the operations
		for(int i=0;i< arr.length;i++) {
			//The subsequent loop iterates over each element of the 2D array arr and performs the respective operations based on the index type:
			int indexType=arr[i][0];
			int index=arr[i][1];
			//For type 2 operations (when indexType == 2), the code finds the nearest type 1 character index from the given index:
			if(indexType==2) {
				//The code checks different scenarios to find the nearest type 1 character index. 
				//If there is a type 1 character on both the left and right sides of the given index, it calculates the distances and determines the nearest index. 
				//If there is a type 1 character only on the left or right side, it adds that index to elements. 
				//If there are no type 1 characters on either side, it adds -1 to elements
				if(typeOneElements.ceiling(index)==null && typeOneElements.floor(index)!=null) {
					elements.add(typeOneElements.floor(index));
				}
				else if(typeOneElements.ceiling(index)!=null && typeOneElements.floor(index)==null) {
					elements.add(typeOneElements.ceiling(index));
					
				}
				else if(typeOneElements.ceiling(index)!=null && typeOneElements.floor(index)!=null) {
					int left=index -typeOneElements.floor(index);
					int right=typeOneElements.ceiling(index)-index;
					
					if(left==right) {
						elements.add(Math.min(typeOneElements.floor(index), typeOneElements.ceiling(index)));
					}
					else {
						if(left<right) {
							elements.add(typeOneElements.floor(index));
						}
						else if(right<left) {
							elements.add(typeOneElements.ceiling(index));
						}
					}	
				}
				else if((typeOneElements.ceiling(index)==null && typeOneElements.floor(index)==null)) {
					elements.add(-1);
				}
			}
			//the code checks if the index is already present in typeOneElements. If it is present, it removes it; otherwise, it adds it
			else {
				if(typeOneElements.contains(index)) {
					typeOneElements.remove(index);
					
				}
				else {
					typeOneElements.add(index);
				}
			}
		}
		int[] answer=new int[elements.size()];
		for(int i=0;i<elements.size();i++) {
			answer[i]=elements.get(i);
		}
		return answer;
	}

}
