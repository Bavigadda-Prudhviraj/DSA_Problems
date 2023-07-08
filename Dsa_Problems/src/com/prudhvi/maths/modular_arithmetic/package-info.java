package com.prudhvi.maths.modular_arithmetic;
/*
 	% modular property means remainder
 	
 	Definition(A%B): Keep subtracting B from A still A<B
 		Example:
 			30%7:(30-7)=(23-7)=(16-7)=(9-7)= 2 remainder
 			remainder: Dividend - greatest multiple of divisor <= dividend
 						(30 - 28)  = 2 remainder
 		Note: if we perform -7%3 in java it will give us -1 wrong answer
 		 		-7-(-9)=2
 		 		so we do (negative number%M)%M to get correct answer
 		 		
 		 		
 	Important formulas:
 			1. (a+b)%m =( a%m + b%m )%m      ranges[0,m-1]  = [0,m-1]+[0,m-1],here sum of m-1+m-1 can go out of range we are doing mod with m again
 		      
 			2. (a+m)%m =( a%m + m%m )%m
 			 		   =( a%m +  0  )%m
 			 		   =( a%m ) %m 
 			 		   = a%m
 			 		   
 			3. (a-b)%m =( a%m - b%m +m )%m, because the answer mill get negative so we are adding +m to that answer
 			
 			4. (a*b)%m =( a%m * b%m)%m
 */
