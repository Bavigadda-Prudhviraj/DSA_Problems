package com.prudhvi.strings;

import java.util.Arrays;

public class Divide_A_StringIntoGroupsOfSize_k_2138 {

    public static void main(String[] args) {
        // Define the input string to be divided
        String str = "abcdefghij";
        // Define the size of each group
        int k = 3;
        // Define the character to use for padding if needed
        char fillChar = 'x';
        // Call the divideString method and print the resulting array
        System.out.println(Arrays.toString(divideString(str, k, fillChar)));
    }

    public static String[] divideString(String s, int k, char fill) {
        // Calculate the total number of groups needed by dividing string length by k
        // Use Math.ceil to round up to ensure we account for a partial last group
        int totalGroups = (int) Math.ceil((double) s.length() / k);
        // Initialize an array to store the groups, with size equal to totalGroups
        String[] groups = new String[totalGroups];

        // Initialize index to track the current group in the result array
        int groupIndex = 0;

        // Loop through the string in steps of k to create groups
        for (int start = 0; start < s.length(); start += k) {
            // Calculate the end index for the current group, ensuring it doesn't exceed string length
            int end = Math.min(start + k, s.length());
            // Extract the substring from start to end to form the current group
            String currentGroup = s.substring(start, end);

            // Check if the current group is smaller than k (partial group)
            if (currentGroup.length() < k) {
                // Create a StringBuilder to pad the partial group
                StringBuilder paddedGroup = new StringBuilder(currentGroup);
                // Append the fill character until the group size reaches k
                while (paddedGroup.length() < k) {
                    paddedGroup.append(fill);
                }
                // Update currentGroup with the padded result
                currentGroup = paddedGroup.toString();
            }

            // Store the current group (padded or not) in the result array
            groups[groupIndex++] = currentGroup;
        }

        // Return the array containing all groups
        return groups;
    }
}