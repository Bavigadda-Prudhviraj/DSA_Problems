package com.prudhvi.greedy;
/*
	The greedy algorithm is a simple and intuitive approach to solving optimization problems. 
	It works by making a series of locally optimal choices at each step, with the hope that these choices will lead to a globally optimal solution. 
	In other words, at each step of the algorithm, it makes the best choice available without considering the overall future consequences.

	The greedy algorithm is often used for optimization problems where you want to find the best possible solution from a set of possible solutions. 
	However, it's important to note that while greedy algorithms are easy to implement and can work well for certain problems, they don't guarantee finding the absolute optimal solution for all cases. 
	Sometimes, the greedy algorithm might lead to sub optimal solutions.
	
	Identifying whether a coding problem can be solved using a greedy approach requires careful analysis of the problem's characteristics and constraints. 
	While there's no foolproof method to determine if a problem can be solved greedily, there are some common traits and patterns that suggest a greedy approach might work:
			1.Optimal Substructure: 
					Problems with optimal substructure have solutions that can be built from the solutions of subproblems. 
					Greedy algorithms work well when you can make a locally optimal choice at each step, and those choices lead to a globally optimal solution.
			2.Greedy Choice Property: 
					At each step of the algorithm, you make a choice that seems to be the best at that moment, without worrying about the future. 
					This choice should not invalidate the previous choices and should contribute to the overall optimal solution.
			3.Sorting and Ordering: 
					Greedy algorithms often involve sorting elements in a certain order and then making decisions based on that order. 
					If the problem involves finding the maximum, minimum, or optimizing some parameter, sorting could be a clue that a greedy approach might work.
			4.Counterexamples: 
					Try to come up with counterexamples that demonstrate why a greedy approach might fail. 
					If you can't find any convincing counterexamples, a greedy approach could be promising.
			5.Greedily Solvable Subproblems: 
					Look for parts of the problem where greedy choices are feasible and lead to a partial solution. 
					If solving subproblems greedily contributes to the overall solution, a greedy approach might be suitable.
			6.Proof of Correctness: 
					If you can prove that the greedy choices you make at each step lead to an optimal solution, it's a strong indicator that a greedy approach is viable.
			7.Monotonicity: 
					Some problems involve sequences of elements that exhibit a monotonic behavior. 
					In these cases, making locally optimal choices could result in a globally optimal solution.
			8.Exploration of Possibilities: 
					Greedy algorithms often explore possibilities in a systematic way, evaluating each option based on a certain criterion.
			9.Small Input Cases: 
					Greedy algorithms tend to work well on problems with small inputs because they can quickly consider and make decisions based on available choices.
	However, keep in mind that not all problems that exhibit some of these traits can be solved using a greedy approach. 
	Some problems might appear to have the characteristics of a greedy problem but can only be solved optimally using more advanced techniques like dynamic programming or backtracking.

	It's important to approach problem-solving with an open mind, consider different approaches, and analyze the problem's constraints and requirements thoroughly before deciding on a strategy. 
	Additionally, as you gain experience, you'll become better at recognizing problem types that are amenable to greedy algorithms.
	
	All greedy Problems in leetCode: https://leetcode.com/problemset/all/?topicSlugs=greedy&page=1
*/