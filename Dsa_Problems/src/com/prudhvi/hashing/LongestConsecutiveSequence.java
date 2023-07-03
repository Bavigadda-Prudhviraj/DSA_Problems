package com.prudhvi.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		int[] A = {100, 4, 200, 1, 3, 2};
		int ans=longestConsecutiveSequence(A);
		System.out.println(ans);
		List list=new ArrayList<>(Arrays.asList(100, 4, 200, 1, 3, 2));
		int ans1=longestConsecutive(list);
		System.out.println(ans1);

	}
	/*
	the longestConsecutiveSequence function takes an array of integers as input and finds the length of the longest consecutive sequence present in the array. 
	It achieves this by first creating a HashSet to store unique elements from the array. 
	Then, it iterates through the array and checks for the start of a consecutive sequence. 
	If it finds such a start, it continues to find the complete consecutive sequence and updates the ans variable with the maximum length encountered. 
	The final ans value represents the length of the longest consecutive sequence in the array.
	 */
	private static int longestConsecutiveSequence(int[] arr) {
		if(arr.length==0) {
			return 0;
		}
		int ans=1;//This initializes a variable ans to 1. It will store the length of the longest consecutive sequence found in the array.
		//This creates a new HashSet named hashSet, which will be used to store the unique elements of the input array arr.
		HashSet<Integer> hashSet=new HashSet<>();
		for(int i=0;i<arr.length;i++) {
			hashSet.add(arr[i]);
		}
		for(int i=0;i<arr.length;i++) {
			int presentElement=arr[i];//It gets the current element from the array.
			//This condition checks if the previous element (presentElement - 1) is present in the hashSet. 
			//If the previous element is not present, 
			//it means the current element could be the start of a consecutive sequence
			//Inside the if block, it starts finding the consecutive sequence
			if(!hashSet.contains(presentElement-1)) {//if previous element is not there means we can't make chain
				int nextElement=arr[i]+1;//It initializes nextElement to the element immediately after the current element.
				int cnt=1;//It initializes a counter cnt to 1, as the current element is part of the consecutive sequence.
				while(hashSet.contains(nextElement)){//This loop continues as long as the next element is present in the hashSet, i.e., it is part of the consecutive sequence.
					cnt++;//cnt is incremented
					nextElement++;//nextElement is increased to check the next element.
				}
				//After finding the length of the consecutive sequence starting from the current element, it updates the ans variable with the maximum value between the current ans and cnt. 
				//This keeps track of the longest consecutive sequence found so far.
				ans=Math.max(ans,cnt);
			}
		}
		return ans;
		
	}
	public static int longestConsecutive(final List<Integer> A) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int maxCount = 0;
        for (int ele : A) {
            if (!mp.containsKey(ele)) {
                int lCount = 0;
                int rCount = 0;
                // lCount stores longest consecutive element till the current element - 1
                if (mp.containsKey(ele - 1)) {
                    lCount = mp.get(ele - 1);
                }
                // rCount stores longest consecutive element from the current element + 1
                if (mp.containsKey(ele + 1)) {
                    rCount = mp.get(ele + 1);
                }
                mp.put(ele, lCount + 1 + rCount);
                if(mp.containsKey(ele - lCount))
                    mp.put(ele - lCount, mp.get(ele));
                else
                    mp.put(ele - lCount, mp.get(ele));
                if(mp.containsKey(ele + rCount))
                    mp.put(ele + rCount, mp.get(ele));
                else
                    mp.put(ele + rCount, mp.get(ele));
                if (maxCount < lCount + 1 + rCount) 
                    maxCount = lCount + 1 + rCount;
            }
        }
        return maxCount;
    }

}
