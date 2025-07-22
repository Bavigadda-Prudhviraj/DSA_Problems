package com.prudhvi.dynamic_programming.one_dimensional.LIS;


public class FindTheMaximumLengthOfValidSubsequenceI_3201 {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 1, 1, 2, 1, 2};
        System.out.println(findMaximumSubsequenceLength(numbers));
    }

    
    public static int findMaximumSubsequenceLength(int[] numbers) {
    	
  
    	
    	return Math.max(LIS(-1, 0, numbers, 0),  LIS(-1, 0, numbers, 1));
    }
    public static int LIS( int previousIndex,int currentIndex, int[] nums, int mod){
    	if(currentIndex >= nums.length) {
    		return 0;
    	}
    	int take = 0;
    	int skip = 0;
    	if((previousIndex == -1) || ((nums[previousIndex] + nums[currentIndex]) % 2 == mod)) {
    		take = 1 + LIS(currentIndex, currentIndex + 1, nums, mod);
    	}else  {
			skip = LIS(previousIndex, currentIndex + 1 , nums, mod);
		}
    	return Math.max(take, skip);
    }
    
    public static int findMaximumSubsequenceLengthTabulation(int[] nums) {
        int n = nums.length;
        // DP table: dp[prevIdx+1][currIdx][mod]
        int[][][] dp = new int[n + 1][n + 1][2];

        // Base case: currIdx == n, length is 0 (already initialized to 0 by Java)

        // Iterate from right to left (bottom-up)
        for (int currIdx = n - 1; currIdx >= 0; currIdx--) {
            for (int prevIdx = -1; prevIdx < n; prevIdx++) {
                for (int mod = 0; mod < 2; mod++) {
                    int take = 0;
                    int skip = 0;

                    // Map prevIdx to dp index (prevIdx + 1)
                    int dpPrevIdx = prevIdx + 1;

                    // Skip case: Move to next index without including current element
                    skip = dp[dpPrevIdx][currIdx + 1][mod];

                    // Take case: Include current element if parity condition is satisfied
                    if (prevIdx == -1 || (nums[prevIdx] + nums[currIdx]) % 2 == mod) {
                        take = 1 + dp[currIdx + 1][currIdx + 1][mod];
                    }

                    // Store maximum of take and skip
                    dp[dpPrevIdx][currIdx][mod] = Math.max(take, skip);
                }
            }
        }

        // Return maximum of even and odd parity starting from currIdx=0, prevIdx=-1
        return Math.max(dp[0][0][0], dp[0][0][1]);
    }
		
	
}