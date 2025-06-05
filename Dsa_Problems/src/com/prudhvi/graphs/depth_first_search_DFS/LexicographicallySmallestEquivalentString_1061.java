package com.prudhvi.graphs.depth_first_search_DFS;

import java.util.ArrayList;
import java.util.List;

public class LexicographicallySmallestEquivalentString_1061 {

	public static void main(String[] args) {
		String s1 = "parker";
		String s2 = "morris";
		String baseStr = "parser";

		// Expected output: "makkek"
		System.out.println(smallestEquivalentString(s1, s2, baseStr));
	}

	/**
	 * Given two equivalent strings (s1, s2) of the same length,
	 * where each s1[i] is equivalent to s2[i], this function finds the
	 * lexicographically smallest string by replacing characters in baseStr
	 * using the equivalency relations.
	 * 
	 * Time Complexity: O(n + m * 26)
	 *     - n = s1.length() (used to build graph)
	 *     - m = baseStr.length() (DFS called for each character)
	 *     - DFS visits at most 26 nodes (English lowercase letters)
	 * 
	 * Space Complexity: O(26^2) -> 0(1) effectively
	 *     - Adjacency list stores edges between 26 letters
	 *     - Visited array of size 26
	 *     - O(26²) → O(1) effectively
	 */
	public static String smallestEquivalentString(String s1, String s2, String baseStr) {
		// Step 1: Build graph of character equivalences
		List<List<Character>> equivalenceGraph = buildGraphFromMappings(s1, s2);

		StringBuilder resultBuilder = new StringBuilder();

		// Step 2: For each character in baseStr, find smallest equivalent using DFS
		for (int i = 0; i < baseStr.length(); i++) {
			int[] visited = new int[26]; // marks visited characters
			char smallestChar = dfsFindMinEquivalent(baseStr.charAt(i), equivalenceGraph, visited);
			resultBuilder.append(smallestChar);
		}

		return resultBuilder.toString();
	}

	/**
	 * DFS to find the smallest lexicographical character in the connected component.
	 * 
	 * @param currentChar - current character we are exploring
	 * @param graph - adjacency list of character equivalences
	 * @param visited - marks which characters have been visited
	 * @return - smallest character in the component
	 */
	private static char dfsFindMinEquivalent(char currentChar, List<List<Character>> graph, int[] visited) {
		visited[currentChar - 'a'] = 1;

		List<Character> neighbors = graph.get(currentChar - 'a');
		char minChar = currentChar;

		for (char neighbor : neighbors) {
			if (visited[neighbor - 'a'] == 0) {
				// Recursively find the minimum character in the connected component
				minChar = (char) Math.min(minChar, dfsFindMinEquivalent(neighbor, graph, visited));
			}
		}

		return minChar;
	}

	/**
	 * Builds an undirected graph where each node is a character and
	 * edges represent equivalence (s1[i] == s2[i]).
	 * 
	 * @param s1 - first string
	 * @param s2 - second string
	 * @return - graph represented as adjacency list
	 */
	private static List<List<Character>> buildGraphFromMappings(String s1, String s2) {
		List<List<Character>> graph = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			graph.add(new ArrayList<>());
		}

		int length = s1.length();
		for (int i = 0; i < length; i++) {
			char charFromS1 = s1.charAt(i);
			char charFromS2 = s2.charAt(i);

			// Undirected edge: a <-> b
			graph.get(charFromS1 - 'a').add(charFromS2);
			graph.get(charFromS2 - 'a').add(charFromS1);
		}
		return graph;
	}
}
