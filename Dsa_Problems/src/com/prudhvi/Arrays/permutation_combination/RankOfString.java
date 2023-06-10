package com.prudhvi.Arrays.permutation_combination;

import java.util.*;

/*
  			Given a string A. Find the rank of the string amongst its permutations sorted lexicographically.
			Assume that no characters are repeated.

			Note: The answer might not fit in an integer, so return your answer % 1000003
			
			Algo:-  1. For the current character, count the number of characters that are lexicographically smaller than it among the remaining characters in the string.
					2. Multiply the count from step 4a by the factorial of the remaining characters' length (i.e., "n - index - 1"), where "index" is the current index of the character.					
					3. Add the result from step 4b to the rank.
					4. Remove the current character from the string.
  */

public class RankOfString {

	public static void main(String[] args) {
		String string="divya";
		int ans=findRank(string);
		System.out.println(ans);
		
	}
	public static int findRank(String A) {
		//When creating a factorial array, it is more efficient to precompute the factorial values up to a given length and store them in an array, rather than calculating the factorial of each element inside a loop. This approach helps avoid the unnecessary repetition of factorial calculations, resulting in improved time complexity and overall code performance
		int[] factorial=new int[A.length()];
		int fact=1;
		for(int i=1;i<A.length();i++) {
			factorial[i]=(fact*i)%1000003;
			fact=(fact*i)%1000003;
		}
		//When adding all the elements of a string into an array list of characters 
		ArrayList<Character> aList=new ArrayList<>();
		for(int i=0;i<A.length();i++) {
			aList.add(A.charAt(i));
			}
		//We are sorting the characters in the array list because we need to determine the number of letters that are lexically smaller than the character at a specific index in the input string. By sorting the characters, we can easily perform comparisons and count the number of elements that are smaller than the character at the desired index
		Collections.sort(aList);
		int rank=0;
		for(int i=0;i<A.length();i++) { 
			//We are determining the count of characters that are lexicographically smaller than the character at the current index in the input string
			int lessCharacters=aList.indexOf(A.charAt(i));
			//The main logic in this context involves calculating the count of characters that are lexicographically smaller than the character at the current index in the input string. This count is then multiplied by the factorial of the difference between the length of the input string and the value of the current index minus one. 
			//The formula takes into account the total number of possible permutations that can be formed by the remaining characters after fixing the character at the current index. By considering the smaller characters and applying the factorial calculation, we can derive valuable insights into the ordering and distribution of the string's elements. 
			//This approach is particularly useful in scenarios such as generating permutations, determining rankings, or analyzing the probability of certain combinations occurring within the string.
			  rank =rank+((lessCharacters*factorial[A.length()-i-1]) % 1000003 );
			  //After determining the rank of the character at the current index, we remove that character from the array list. Removing the character is necessary because it has been correctly placed in its appropriate position based on the lexicographical order. 
			  //This step ensures that the remaining characters in the array list can be accurately positioned in subsequent iterations. By removing the character, we maintain the integrity of the sorting process, allowing us to correctly handle the remaining characters and iteratively determine their positions.
			  //It is an essential step in achieving the accurate and efficient sorting of the entire string.
			  aList.remove(aList.indexOf(A.charAt(i))); 
		}
		return (rank+1)%1000003;

    }
	

}
