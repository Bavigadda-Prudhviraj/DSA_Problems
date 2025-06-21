package com.prudhvi.strings;

public class MinimumDeletionsToMakeString_K_Special_3085 {
	public static void main(String[] args) {
		String word = "dabdcbdcdcd";
		int k = 2;
		System.out.println(minimumDeletions(word, k));

	}

	/**
     * Calculates the minimum number of deletions required to make the frequency
     * difference between any two characters in the string at most k.
     *
     * Detailed Mathematical Logic:
     * - Objective: Ensure that for any two character frequencies f[i] and f[j], 
     *   |f[i] - f[j]| <= k, where f[i] is the frequency of character i.
     * - If we choose f[i] = x as the base frequency, 
     *   then f[j] should ideally be in the range [x - k, x + k] to satisfy |x - f[j]| <= k.
     *   
     * - Verification: If f[j] = x + k, then |x - (x + k)| = |-k| = k <= k.
     *   Similarly, if f[j] = x - k (and x - k >= 0), then |x - (x - k)| = k <= k.
     *   
     * - Excess Frequency: If f[j] = x + k + c (where c > 0 is extra frequency), then:
     *     f[j] - f[i] = (x + k + c) - x = k + c
     *     c = f[j] - f[i] - k (extra frequency to delete to make f[j] = x + k).
     *     
     * - Deletion Rule: If |f[j] - f[i]| > k:
     *     - If f[j] > f[i] + k, delete f[j] - f[i] - k characters.
     *     - If f[j] < f[i], delete all f[j] occurrences (edge case).
     *     
     * - Edge Case: When f[j] < f[i], |f[i] - f[j]| = f[i] - f[j]. If this exceeds k,
     *   we delete all f[j] occurrences to eliminate character j.
     * - Minimization: Try each non-zero f[i] as the base and compute total deletions,
     *   keeping the minimum across all iterations.
     * - Example: For word = "aaaabb", k = 1:
     *     - Frequencies: f['a'] = 4, f['b'] = 2.
     *     - Base f['a'] = 4: For f['b'] = 2, |2 - 4| = 2 > 1, delete f['b'] = 2 (edge case).
     *       Total deletions = 2.
     *     - Base f['b'] = 2: For f['a'] = 4, |4 - 2| = 2 > 1, delete 4 - 2 - 1 = 1.
     *       Total deletions = 1.
     *     - Result: min(2, 1) = 1 (delete one 'a' to get f['a'] = 3, f['b'] = 2, |3 - 2| = 1 <= 1).
     * - Scalability: Fixed alphabet (26 letters) ensures O(1) space and O(26 * 26) = O(1)
     *   for frequency comparisons. String iteration is O(n), making total time O(n).
     *
     * Time Complexity: O(n), where n is the string length (O(n) for frequency counting,
     *   O(1) for nested loops over 26 letters).
     * Space Complexity: O(1) due to fixed 26-size array.
     *
     * @param word The input string consisting of lowercase letters.
     * @param k The maximum allowed frequency difference between any two characters.
     * @return The minimum number of deletions required.
     */
    public static int minimumDeletions(String word, int k) {
        // Step 1: Count frequency of each character (a-z)
        int[] charFrequencies = new int[26];
        for (char c : word.toCharArray()) {
            charFrequencies[c - 'a']++;
        }

        int minDeletions = Integer.MAX_VALUE;

        // Step 2: Try each non-zero frequency as the base frequency (f[i] = x)
        for (int i = 0; i < 26; i++) {
            int baseFrequency = charFrequencies[i];
            if (baseFrequency == 0) {
                continue; // Skip if character doesn't exist
            }

            int deletionsForCurrentBase = 0;
            // Step 3: Compare with all other character frequencies (f[j])
            for (int j = 0; j < 26; j++) {
                int currentFrequency = charFrequencies[j];
                if (currentFrequency == 0 || i == j) {
                    continue; // Skip if same character or frequency is zero
                }

                // Step 4: Adjust frequencies to satisfy |f[i] - f[j]| <= k
                if (currentFrequency < baseFrequency) {
                    // Edge case: f[j] < f[i], |f[i] - f[j]| = f[i] - f[j]
                    // Delete all f[j] occurrences to eliminate character j
                    deletionsForCurrentBase += currentFrequency;
                } else if (Math.abs(currentFrequency - baseFrequency) > k) {
                    // If |f[j] - f[i]| > k and f[j] > f[i], delete excess
                    // c = f[j] - f[i] - k, where c is the number of deletions
                    deletionsForCurrentBase += (currentFrequency - baseFrequency - k);
                }
                // If f[i] - k <= f[j] <= f[i] + k, no deletions needed
            }
            // Step 5: Update minimum deletions required
            minDeletions = Math.min(deletionsForCurrentBase, minDeletions);
        }

        return minDeletions;
    }

}
