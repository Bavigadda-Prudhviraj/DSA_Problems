package com.prudhvi.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SubsetsII {

	public static void main(String[] args) {
		ArrayList<Integer> arrayList=new ArrayList<>(List.of(1, 2, 2));
		ArrayList<ArrayList<Integer>> set=subsetsWithDup(arrayList);
		System.out.println(set);

	}
	/*
	**Time Complexity**:
			The time complexity is a bit tricky to analyze because it depends on the number of unique elements in the input ArrayList `A` and their frequencies. 
			In the worst case, it can be exponential, as the code generates all possible subsets while considering the frequencies of each element. 
			Specifically, for each unique element, the code performs two recursive calls, leading to exponential behavior. 
			However, in practice, if there are not too many unique elements or if the frequencies are low, the code might run relatively faster. 
			The worst-case time complexity is O(2^N), where N is the total number of unique elements.
	
	**Space Complexity**:
		1. The space complexity of the `frequency` array is O(17), which is constant and can be considered O(1).
		2. The ArrayList `arr` stores all unique subsets, which can be exponential in the worst case (O(2^N)).
		3. The HashSet `set` also stores subsets but is used to check for uniqueness. Its space complexity is also exponential in the worst case (O(2^N)).
		4. The ArrayList `ele` is used for the current subset being generated. It can have a maximum length of N (the total number of unique elements).
		Overall, the space complexity depends on the number of unique subsets generated and is mainly driven by the space used to store these subsets. In the worst case, it is exponential, but in practice, it may be smaller if there are fewer unique elements and lower frequencies.
	In summary, the code has a potentially exponential time and space complexity, which can be significant for large inputs with a high number of unique elements and frequencies.
	 */
	 public static ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> A) {
		 //It first initializes an integer array frequency of size 11. This array is used to count the frequency of each element in A. It's assumed that the elements in A are non-negative integers within the range [0, 16].
	        int[] frequency=new int[11];
	        //It iterates through the elements of A and increments the corresponding frequency in the frequency array. This step calculates how many times each unique element occurs in A.
	        for(int i=0;i<A.size();i++){
	            frequency[A.get(i)]++;
	        }
	        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();//It initializes an ArrayList of ArrayLists, arr, to store the unique subsets.
	        HashSet<ArrayList<Integer>> set=new HashSet<>();//It initializes a HashSet of ArrayLists, set, to keep track of the unique subsets to avoid duplicates.
	        ArrayList<Integer> ele=new ArrayList<>();//It initializes an empty ArrayList ele to represent the current subset being generated.
	       generatePermutations(arr,frequency,A,0,ele,set);//It calls the generatePermutations function with the parameters arr, frequency, A, 0 (initial index), ele, and set.
	        return arr;//Finally, it returns the arr containing all unique subsets.
	    }
	 public static void generatePermutations(ArrayList<ArrayList<Integer>> arr, int[] frequency,ArrayList<Integer> A,int idx,ArrayList<Integer> ele,HashSet<ArrayList<Integer>> set){
		 //If the index idx becomes equal to the size of A, it means that a unique subset has been generated, and it adds this subset to arr if it is not already in set.
	        if(idx==A.size()){
	        	if(!set.contains(new ArrayList(ele))) {
	        		arr.add(new ArrayList(ele));
	        		set.add(new ArrayList(ele));
	        	}
	            return;
	        }
	        //It then loops through the frequency array. For each element that has a frequency greater than 0, it adds the element to the ele (current subset) and decrements its frequency in the frequency array.
	        for(int i=0;i<frequency.length;i++){
	            if(frequency[i]>0){
	                ele.add(i);
	                frequency[i]--;
	                //It makes two recursive calls:
	                //One call with the next index (idx+1) to continue generating the subset.
	                generatePermutations( arr, frequency, A,idx+1,ele,set);
	                ele.remove(ele.size()-1);
	                //Another call to generate the subset without the current element (backtracking).
	                generatePermutations( arr, frequency, A,idx+1,ele,set);
	                //After the recursive calls, it restores the frequency of the element by incrementing it in the frequency array.
	                frequency[i]++;
	            }
	        }

	    }

}
