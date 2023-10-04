package com.prudhvi.dynamic_programming.catalan_numbers;

public class Catalan_number {
	/*
	nTh catalan number= 2n!/(n+1)!*(n!)
	N = sum is n-1	
	c3=c0*c2 + c1*c1 + c0*c2
	c4=c0*c3 + c1*c2 + c2*c1 + c3*c0
	c5=c0*c4 + c1+c3 + c2*c2 + c3*c1 + c4*c0
	c6=c0*c5 + c1+c4 + c2+C3 + C3*C2 + C4+C1 + C5*C0
	
	**Catalan numbers** are a sequence of natural numbers that have numerous applications in combinatorial mathematics, particularly in counting various types of objects and structures. They are named after the Belgian mathematician Eugène Charles Catalan, who introduced them in the 19th century. Catalan numbers are used to count the number of different ways to arrange various structures while respecting certain constraints.
	
	The nth Catalan number is represented as C(n) and is defined by the following recurrence relation:
	
	[C(n) = \frac{2n!}{(n+1)!n!} = \frac{(2n)!}{(n+1)!n!}\]
	
	Here are some key aspects of Catalan numbers:
	
	1. **Use Cases**:
	   - **Balanced Parentheses**: Catalan numbers count the number of different ways to arrange balanced parentheses. For example, for n = 3, there are five valid arrangements: (()()), (())(), ()(()), (())(), and ()()().
	   - **Binary Trees**: They represent the number of unique binary search trees with n nodes.
	   - **Polygon Triangulation**: Counting the number of ways to triangulate a convex polygon with n+2 sides.
	   - **Dyck Paths**: Counting the number of paths in a grid from (0,0) to (n,n) that stay below the diagonal line y = x and consist of steps (1,0) and (0,1) without crossing the diagonal.
	
	2. **Famous Problems and Applications**:
	   - **Catalan numbers are used in the combinatorial enumeration of various problems**, including counting different types of trees (binary trees, rooted trees), counting the number of ways to arrange parentheses, and solving various combinatorial problems.
	   - **Parentheses Expression Evaluation**: In computer science, Catalan numbers are used in parsing and evaluating expressions with balanced parentheses, such as arithmetic expressions.
	   - **Catalan Number Sequence**: The sequence of Catalan numbers has applications in dynamic programming and can be found in problems that require counting different combinations or arrangements, such as in chessboard problems.
	
	3. **How It Is Implemented**:
	   - Catalan numbers can be calculated using dynamic programming or recursive methods.
	   - Recursive Method: The recursive formula for Catalan numbers is \(C(n) = \sum_{i=0}^{n-1} C(i) * C(n-i-1)\), where you recursively calculate smaller Catalan numbers to find larger ones.
	   - Dynamic Programming: You can use an array to store Catalan numbers iteratively, starting with \(C(0) = 1\). The next Catalan number is calculated based on the previous ones using the recurrence relation mentioned above.
	
	4. **When to Use**:
	   - Use Catalan numbers when you need to count the number of different ways to arrange certain combinatorial structures while respecting specific constraints, such as balanced parentheses, binary trees, or polygon triangulations.
	   - They are particularly useful in problems where you need to count combinations of elements in a structured manner.
	
	Catalan numbers have widespread applications in combinatorial mathematics, computer science, and various other fields where counting arrangements and combinations are crucial. They provide a powerful tool for solving a variety of counting problems.
	 */

}
