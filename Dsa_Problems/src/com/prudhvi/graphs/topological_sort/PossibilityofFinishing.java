package com.prudhvi.graphs.topological_sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
	Problem Description
			There are a total of A courses you have to take, labeled from 1 to A.
			Some courses may have prerequisites, for example to take course 2 you have to first take course 1, which is expressed as a pair: [1,2].
			So you are given two integer array B and C of same size where for each i (B[i], C[i]) denotes a pair.
			Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
			Return 1 if it is possible to finish all the courses, or 0 if it is not possible to finish all the courses.
	Problem Constraints
			1 <= A <= 6*104
			1 <= length(B) = length(C) <= 105
			1 <= B[i], C[i] <= A
 */
public class PossibilityofFinishing {

	public static void main(String[] args) {
		int A = 2;
		int[] B = {1, 2};
		int[] C = {2, 1};
		int canFinish=possibilityOfFinishing(A,B,C);
		System.out.println(canFinish);

	}
	/*
	Time Complexity (possibilityOfFinishing function):
				1.Graph Initialization: You initialize an adjacency list graph (graph) based on the input arrays b and c. The loop to initialize the graph runs in O(E) time, where 'E' is the number of edges in the graph. 
									  In this case, the number of edges is equal to b.length, so this part contributes O(b.length) to the time complexity.
				2.In-Degree Calculation: You calculate the in-degrees for each course. This loop runs in O(b.length) time, where 'b.length' is the number of edges.
				3.Queue Initialization: You initialize a queue with courses that have no prerequisites. The initialization step takes O(n) time because you iterate through the indegree array once.
				4.Topological Sorting (BFS): You perform a topological sorting of the courses using BFS. The BFS algorithm's time complexity is O(V + E), where V is the number of vertices and E is the number of edges.
						1.In this case, V is n, the number of courses.
						2.The BFS algorithm typically visits each vertex and edge once.
			So, the overall time complexity of the possibilityOfFinishing function is O(n + b.length) due to graph initialization and BFS traversal.
	Space Complexity (possibilityOfFinishing function):
				1.Graph Data Structure: You create an adjacency list graph (graph) to represent the course prerequisites. 
				                        The space used by the graph depends on the number of edges, which is equal to the length of the input array b. So, the space complexity for the graph is O(b.length).
				2.In-Degree Array: You create an array indegree to store the in-degrees of each course. The size of the indegree array is proportional to the number of courses (n), so it consumes O(n) space.		
				3.Queue and Other Variables: The space used by the queue and other variables is negligible compared to the graph and the indegree array.	
			Therefore, the overall space complexity of the possibilityOfFinishing function is O(n + b.length) due to the graph and the indegree array.
					
	The possibilityOfFinishing function has a time complexity of O(n + b.length) and a space complexity of O(n + b.length).
	 */
	private static int possibilityOfFinishing(int a, int[] b, int[] c) {
		int n=a;
		//It initializes an adjacency list graph, where each course is represented by an ArrayList of integers. The index of the ArrayList corresponds to the course number.
		ArrayList<Integer>[] graph=new ArrayList[n+1];
		for (int i = 0; i < graph.length; i++) {
			graph[i]=new ArrayList<>();
		}
		//It initializes an array indegree to keep track of the in-degrees (the number of prerequisites) for each course.
		int[] indegree=new int[n+1];
		//It populates the graph and indegree based on the input arrays b and c. For each pair (course, prerequisite) in the input arrays, 
		for(int i=0;i<b.length;i++) {
			int course=b[i];
			int preprerequisite=c[i];
			//it adds the prerequisite to the ArrayList at index course in the graph, indicating that course depends on prerequisite. 
			graph[course].add(preprerequisite);
			indegree[preprerequisite]++;//It also increments the in-degree of prerequisite by one.
		}
		//It initializes a queue queue and adds all courses with an in-degree of 0 to the queue. These are the courses that have no prerequisites and can be taken first.
		Queue<Integer> queue=new LinkedList<>();
		for (int i = 1; i < indegree.length; i++) {
			if(indegree[i]==0) {
				queue.add(i);
			}
		}
		//It enters a loop where it processes courses in a topological order:



		while(!queue.isEmpty()){
			//It dequeues a course from the queue.
			int idx=queue.poll();
			ArrayList<Integer> neighbour=graph[idx];
			//It iterates through the prerequisites of the dequeued course and decrements their in-degrees.
			for (int i=0; i<neighbour.size();i++) {
				int ele=neighbour.get(i);
				indegree[ele]--;
				//If the in-degree of a prerequisite becomes 0 after decrementing, it means that all prerequisites for that course have been satisfied, so it adds that course to the queue.
				if(indegree[ele]==0) {
					queue.add(ele);
				}
			}
			
		}
		//After processing all courses, it checks if there are any courses remaining with in-degrees greater than 0. If such courses exist, it returns 0 to indicate that it's impossible to finish all courses with the given prerequisites. Otherwise, it returns 1, indicating that it's possible to complete all courses.
		for(int i=1;i<indegree.length;i++) {
			if(indegree[i]>0) {
				return 0;
			}
		}
		return 1;
	}

}
