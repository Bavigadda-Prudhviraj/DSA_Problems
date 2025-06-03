package com.prudhvi.graphs.depth_first_search_DFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MaximumCandiesYouCanGetFromBoxes_1298 {

	public static void main(String[] args) {
		// Example input
		int[] status = {1, 0, 1, 0}; // Box 0 and 2 are open; 1 and 3 are locked
		int[] candies = {7, 5, 4, 100}; // candies[i] = number of candies in box i
		int[][] keys = {{}, {}, {1}, {}}; // keys[i] = keys found in box i
		int[][] containedBoxes = {{1, 2}, {3}, {}, {}}; // containedBoxes[i] = boxes inside box i
		int[] initialBoxes = {0}; // boxes you initially have

		// Calling DFS and BFS versions
		System.out.println(maxCandies(status, candies, keys, containedBoxes, initialBoxes));
		System.out.println(maxCandiesBFS(status, candies, keys, containedBoxes, initialBoxes));
	}

	/**
	 * Recursive DFS approach to collect maximum candies.
	 * 
	 * Time Complexity: O(n + m)
	 *   - n: total boxes
	 *   - m: total keys + contained boxes
	 * 
	 * Space Complexity: O(n)
	 *   - due to visitedBoxes, foundBoxes, and recursion stack
	 */
	public static int maxCandies(
			int[] status,
			int[] candies,
			int[][] keys,
			int[][] containedBoxes,
			int[] initialBoxes) {

		int maxCandies = 0;
		Set<Integer> visitedBoxes = new HashSet<>(); // To avoid reprocessing boxes
		Set<Integer> foundBoxes = new HashSet<>();   // Boxes we have but can't open yet

		// Try DFS on each initially available box
		for (int currentBox : initialBoxes) {
			maxCandies += depthFirstSearch_DFS(currentBox, status, candies, keys, containedBoxes, visitedBoxes, foundBoxes);
		}
		return maxCandies;
	}

	private static int depthFirstSearch_DFS(
			int currentBox,
			int[] status,
			int[] candies,
			int[][] keys,
			int[][] containedBoxes,
			Set<Integer> visitedBoxes,
			Set<Integer> foundBoxes) {

		// Skip already visited boxes
		if (visitedBoxes.contains(currentBox)) return 0;

		// If locked, save and exit
		if (status[currentBox] == 0) {
			foundBoxes.add(currentBox);
			return 0;
		}

		// Collect candies from the current box
		int maxCandies = candies[currentBox];
		visitedBoxes.add(currentBox);

		// Recursively open contained boxes
		for (int containedBox : containedBoxes[currentBox]) {
			maxCandies += depthFirstSearch_DFS(containedBox, status, candies, keys, containedBoxes, visitedBoxes, foundBoxes);
		}

		// Use found keys to unlock boxes we already found but couldn't open earlier
		for (int key : keys[currentBox]) {
			status[key] = 1; // Mark key's box as open
			if (foundBoxes.contains(key) && !visitedBoxes.contains(key)) {
				maxCandies += depthFirstSearch_DFS(key, status, candies, keys, containedBoxes, visitedBoxes, foundBoxes);
			}
		}

		return maxCandies;
	}

	/**
	 * Iterative BFS approach to collect maximum candies.
	 * 
	 * Time Complexity: O(n + m)
	 *   - n: total boxes
	 *   - m: total keys + contained boxes
	 * 
	 * Space Complexity: O(n)
	 *   - due to visitedBoxes, foundBoxes, and the queue
	 */
	public static int maxCandiesBFS(
			int[] status,
			int[] candies,
			int[][] keys,
			int[][] containedBoxes,
			int[] initialBoxes) {

		int maxCandies = 0;
		Set<Integer> visitedBoxes = new HashSet<>(); // Already opened boxes
		Set<Integer> foundBoxes = new HashSet<>();   // Boxes we have in possession
		Queue<Integer> queue = new LinkedList<>();   // Boxes ready to open

		// Step 1: Add initial boxes
		for (int initialBox : initialBoxes) {
			foundBoxes.add(initialBox);
			if (status[initialBox] == 1) {
				queue.add(initialBox);
				maxCandies += candies[initialBox];
				visitedBoxes.add(initialBox);
			}
		}

		// Step 2: Process boxes in queue
		while (!queue.isEmpty()) {
			int currentBox = queue.poll();

			// Add contained boxes
			for (int insideBox : containedBoxes[currentBox]) {
				foundBoxes.add(insideBox);
				// If the contained box is open and not visited, process it
				if (status[insideBox] == 1 && !visitedBoxes.contains(insideBox)) {
					queue.add(insideBox);
					maxCandies += candies[insideBox];
					visitedBoxes.add(insideBox);
				}
			}

			// Use the keys found in the current box
			for (int key : keys[currentBox]) {
				status[key] = 1; // Unlock the box with the key
				// If we already have that box and it wasn't visited, process it
				if (foundBoxes.contains(key) && !visitedBoxes.contains(key)) {
					queue.add(key);
					maxCandies += candies[key];
					visitedBoxes.add(key);
				}
			}
		}

		return maxCandies;
	}
}
