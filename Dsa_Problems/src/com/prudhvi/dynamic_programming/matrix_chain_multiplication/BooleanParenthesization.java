package com.prudhvi.dynamic_programming.matrix_chain_multiplication;
/*
	Given a boolean expression S of length N with following symbols.
		Symbols
		    'T' ---> true
		    'F' ---> false
		and following operators filled between symbols Operators
		    &   ---> boolean AND
		    |   ---> boolean OR
		    ^   ---> boolean XOR
		Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
		Note: The answer can be large, so return it with modulo 1003
	Your Task:
		You do not need to read input or print anything. Your task is to complete the function countWays() which takes N and S as input parameters and returns number of possible ways modulo 1003.
		Expected Time Complexity: O(N3)
		Expected Auxiliary Space: O(N2)

 */
public class BooleanParenthesization {
	public static void main(String[] args) {
		String expression="F|T&F^T|F^T^F&F^T&T^F|T^T^F&T^F&F|T|T&F^T^T|F&T^T^F|T^F|F|F|T^F^F|T|T|T|T^T^F|F&T^F|F^F&F|F&F|T|F^F|F^T&T^F|T^F";
		int trueWays=countTrueWays(expression);
		System.out.println(trueWays);
	}
	/*
	Time Complexity (countTrueWays function):
					1.Parsing Expression: 
						You iterate through the expression string to separate operators and operands. This initial parsing step takes O(N) time, where 'N' is the length of the expression.
					2.Initialization of dp Arrays: 
						You initialize two 2D arrays, dpTrue and dpFalse, each of size n x n, where 'n' is the number of operands. This initialization step takes O(n^2) time.
					3.Nested Loops: 
						The core of this function consists of three nested loops. The outermost loop (for d) runs for 'n' iterations, where 'n' is the number of operands. The two inner loops (for i and for k) iterate through subsets of operands within each d iteration.
								1.The outermost loop runs for 'n' iterations.
								2.The for i loop runs for 'n-d' iterations in each outer loop iteration.
								3.The for k loop runs for 'd' iterations in each for i loop iteration.
					Inside the innermost loops, you perform constant-time operations to calculate true and false ways based on operators.
				So, the total number of iterations is roughly proportional to 'n^3'. Therefore, the overall time complexity of the countTrueWays function is O(n^3).

	Space Complexity (countTrueWays function):
					1.dpTrue and dpFalse Arrays: 
						You create two 2D arrays, dpTrue and dpFalse, each of size n x n, to store the results of subproblems. These arrays consume O(n^2) space.
					2.StringBuilder Variables (operand and operator): 
						These variables store intermediate strings while parsing the expression. They consume O(N) space, where 'N' is the length of the expression.
					3.Other variables and constants use negligible space compared to the arrays and StringBuilder variables.
				Therefore, the overall space complexity of the countTrueWays function is O(n^2) due to the dynamic programming tables (dpTrue and dpFalse) and the StringBuilder variables.	
			In summary, the countTrueWays function has a time complexity of O(n^3) and a space complexity of O(n^2) due to the dynamic programming tables and parsing of the expression.
	 */
	private static int countTrueWays(String expression) {
		int mod=1003;
		//It starts by parsing the input boolean expression into two strings: operand and operator. 
		//The operand string contains operands, which are 'T' (true) or 'F' (false), and the operator string contains operators, which are '|' (OR), '&' (AND), and '^' (XOR).
		StringBuilder operand=new StringBuilder();
		StringBuilder operator=new StringBuilder();
		for(int i=0;i<expression.length();i++) {
			char ch=expression.charAt(i);
			if(ch=='|' || ch=='^' || ch=='&' ) {
				operator.append(ch);
			}else {
				operand.append(ch);
			}
		}
		int n=operand.length();
		//It initializes two 2D arrays, dpTrue and dpFalse, both with dimensions [n][n], where n is the length of the operand string. 
		//These arrays will store the number of ways to evaluate the subexpression from index i to j to true and false, respectively.
		long[][] dpTrue=new long[n][n];
		long[][] dpFalse=new long[n][n];
		// iterate through different lengths of subexpressions (d represents the length of the subexpression iterating diagonally).
		for(int d=0;d<n;d++) {
			//Inside the nested loops, there's a distinction between the cases where d (the length of the subexpression) is 0 (single operands) and when d is greater than 0 (subexpressions with operators).
			for(int i=0,j=d;j<n;j++,i++) {
				//For subexpressions of length 1 (single operands), it initializes dpTrue and dpFalse based on the value of the operand. If the operand is 'T', it sets dpTrue to 1 and dpFalse to 0, and vice versa.
				if(d==0) {
					if(operand.charAt(i)=='T') {
						dpTrue[i][j]=1;
						dpFalse[i][j]=0;
					}else {
						dpTrue[i][j]=0;
						dpFalse[i][j]=1;
					}
				}
				//For subexpressions of length greater than 1, it iterates through all possible positions to split the subexpression into two parts and considers the operator at the split position.
				else {
					//It calculates the number of ways to make the subexpression true and false based on the operator and the number of ways to make the left and right parts true and false.
					for(int k=i;k<j;k++){
						char ch=operator.charAt(k);
						long leftTrue=dpTrue[i][k];
						long leftFalse=dpFalse[i][k];
						long rightTrue=dpTrue[k+1][j];
						long rightFalse=dpFalse[k+1][j];
						//The code considers three cases based on the operator ('&', '|', or '^') and calculates the number of ways to evaluate the subexpression to 'true' and 'false' accordingly.
						if(ch=='&') {
							long trueWays=(leftTrue*rightTrue)%mod;
							long falseWays=(leftFalse*rightFalse)%mod+(leftFalse*rightTrue)%mod+(leftTrue*rightFalse)%mod;
							dpTrue[i][j]=(dpTrue[i][j]+trueWays)%mod;
							dpFalse[i][j]=(dpFalse[i][j]+falseWays)%mod;
							
						}else if(ch=='|') {
							long trueWays=(leftFalse*rightTrue)%mod+(leftTrue*rightFalse)%mod+(leftTrue*rightTrue)%mod;
							long falseWays=(leftFalse*rightFalse);
							dpTrue[i][j]=(dpTrue[i][j]+trueWays)%mod;
							dpFalse[i][j]=(dpFalse[i][j]+falseWays)%mod;
						}else if(ch=='^'){
							long trueWays=(leftFalse*rightTrue)%mod+(leftTrue*rightFalse)%mod;
							long falseWays=(leftFalse*rightFalse)%mod+(leftTrue*rightTrue)%mod;
							dpTrue[i][j]=(dpTrue[i][j]+trueWays)%mod;
							dpFalse[i][j]=(dpFalse[i][j]+falseWays)%mod;
						}
						
						
					}
				}
			}
		}	
		//The final result is stored in dpTrue[0][n-1], representing the number of ways to evaluate the entire expression to 'true'.
		return (int)((dpTrue[0][n-1]));
	}
}
