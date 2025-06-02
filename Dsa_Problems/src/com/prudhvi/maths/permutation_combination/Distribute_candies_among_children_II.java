package com.prudhvi.maths.permutation_combination;


public class Distribute_candies_among_children_II {

	public static void main(String[] args) {
		int n = 3;
		int limit = 3;
		System.out.println(distributeCandies(n,limit));

	}
	public static long distributeCandies(int n, int limit) {
	    // Total candies available to distribute
	    int totalCandies = n;

	    // Minimum candies the first child can take:
	    // This ensures that the other two children can still receive up to 'limit' candies each.
	    // Use max(0, ...) to avoid negative values if totalCandies < 2 * limit
	    int firstChildMinCandies = Math.max(0, totalCandies - (2 * limit));

	    // Maximum candies the first child can take:
	    // Cannot exceed either the total candies or the limit per child
	    int firstChildMaxCandies = Math.min(limit, totalCandies);

	    // Variable to keep track of the total number of valid ways to distribute candies
	    long waysToDistribute = 0;

	    // Loop through all valid candy amounts the first child can receive
	    for (int firstChildCandies = firstChildMinCandies; 
	    		firstChildCandies <= firstChildMaxCandies; 
	    		firstChildCandies++) {

	        // Calculate remaining candies after giving to the first child
	        int remainingCandies = totalCandies - firstChildCandies;

	        // Minimum candies the second child must take:
	        // To ensure the third child does not exceed the limit, second child must take at least (remaining - limit)
	        // Use max(0, ...) to avoid negative values
	        int secondChildMinCandies = Math.max(0, remainingCandies - limit);

	        // Maximum candies the second child can take:
	        // Cannot exceed either the remaining candies or the limit per child
	        int secondChildMaxCandies = Math.min(remainingCandies, limit);

	        // Total valid values for second child's candies in this range:
	        // For each valid value, third child's amount (remaining - second) is guaranteed to be within [0, limit]
	        waysToDistribute += (secondChildMaxCandies - secondChildMinCandies + 1);
	    }

	    // Return total number of valid distributions
	    return waysToDistribute;
	}

    /**
     // Given total 'n' candies and a 'limit' on the maximum candies a child can receive,
// this function calculates the number of valid ways to distribute the candies among 3 children
// such that no child gets more than 'limit' candies.

int totalCandies = n;

// The minimum candies the first child must take to ensure the other two children can
// each receive at most 'limit' candies (i.e., reserve up to 2 * limit for them).
// If firstChildMinCandies is negative, we effectively start from 0 in the loop.
int firstChildMinCandies = totalCandies - (2 * limit);

// The first child can take at most 'limit' candies by problem constraint.
int firstChildMaxCandies = Math.min(limit, totalCandies); 
// We use min(limit, totalCandies) because the child can't receive more candies than what we have,
// and the child also can't exceed their individual limit.

int waysToDistribute = 0;

// Iterate over all valid values of candies given to the first child
for (int firstChildCandies = firstChildMinCandies; firstChildCandies <= firstChildMaxCandies; firstChildCandies++) {

    // Skip any values where the first child takes negative candies (not possible).
    if (firstChildCandies < 0) continue;

    // Remaining candies to be distributed between the second and third children
    int remainingCandies = totalCandies - firstChildCandies;

    // The minimum candies the second child must take to ensure the third child gets no more than 'limit'.
    // For example, if remainingCandies = 8 and limit = 5, second child must take at least 3 (so third gets at most 5).
    int secondChildMinCandies = Math.max(0, remainingCandies - limit); 
    // We use max(0, ...) to ensure we don't assign negative candies.

    // The second child can take at most either the remaining candies or the per-child limit.
    int secondChildMaxCandies = Math.min(remainingCandies, limit); 
    // This ensures we do not give the second child more candies than allowed, or more than what's left.

    // The number of valid ways to assign candies to the second child in this range.
    // For each such assignment, the third child gets (remainingCandies - secondChildCandies),
    // which will also be <= limit because of how we calculated secondChildMinCandies.
    waysToDistribute += (secondChildMaxCandies - secondChildMinCandies + 1);
}

return waysToDistribute;

     */


}

