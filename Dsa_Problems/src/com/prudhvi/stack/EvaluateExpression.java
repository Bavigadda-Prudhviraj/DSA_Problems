package com.prudhvi.stack;

import java.util.Stack;

public class EvaluateExpression {

	public static void main(String[] args) {
		String[] expressionsString= {"2","2","/"}; 
		int answer=evaluateExpression(expressionsString);
		System.out.println(answer);
	}
	/*
	The given code takes an array of strings representing a postfix expression and evaluates the expression to get the result. 
	The postfix expression is in reverse Polish notation, where the operator is placed after the operands. 
	For example, the expression ["2", "3", "+"] represents the infix expression "2 + 3".
	
	Time Complexity:
			The time complexity of the code is O(n), where n is the length of the expression array. 
			In the worst case, we may need to process all elements of the array once.

	Space Complexity:
			The space complexity of the code is O(n) in the worst case. 
			This occurs when all elements in the expression array are operands (numbers), and they are pushed onto the operandStack. 
			In the best case scenario, the space complexity is O(1) when there are no operands in the expression, and the stack remains empty.
	 */
	public static int evaluateExpression(String[] expression) {
		Stack<Integer> operandStack=new Stack<>();
		for(int i=0;i<expression.length;i++) {
			String character=expression[i];
			if(!character.equals("+")  && !character.equals("-") && !character.equals("/") && !character.equals("*")  ){
				int number=Integer.parseInt(character); 
				operandStack.push(number);
			}
			else {
				int secondOperand=operandStack.peek();
				operandStack.pop();
				int firstOperand=operandStack.peek();
				operandStack.pop();
				int result;
				if (character.equals("+")) {
					result=firstOperand+secondOperand;	
				}
				else if (character.equals("-")) {
					result=firstOperand-secondOperand;
				}
				else if (character.equals("/") ){
					result=firstOperand/secondOperand;
				}
				else {
					result=firstOperand*secondOperand;	
				}
				operandStack.push(result);
			}
		}
		int answer=operandStack.peek();
		operandStack.pop();
		return answer;
	}

}
