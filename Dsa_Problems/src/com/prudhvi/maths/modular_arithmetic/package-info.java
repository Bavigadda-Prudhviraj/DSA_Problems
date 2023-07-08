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
 			
 			
 	Note: Fermat's Little Theorem 
 			The theorem states that if "p" is a prime number and "a" is an integer that is not divisible by "p", 
 			then:a^(p-1) ≡ 1 (mod p)
			Here, "^" represents exponentiation, and "≡" denotes congruence (equality modulo "p"). 
			In other words, if we take an integer "a" and raise it to the power of "p-1" modulo "p", the result is congruent to 1.


		Key points to note about Fermat's Little Theorem:
			1.Prime modulus: 
				The theorem applies only when "p" is a prime number. 
			  	For composite numbers, the theorem may not hold.

			2.Non-divisibility: 
				The theorem requires that "a" is not divisible by "p". 
				If "a" is divisible by "p", then "a^(p-1)" will also be divisible by "p", and the congruence will not hold.

			3.Modular exponentiation: 
				The theorem relates to exponentiation in modular arithmetic. 
				Instead of computing the full exponentiation result, 
				we can take the remainder modulo "p" at each step of the exponentiation process.

		Fermat's Little Theorem has numerous applications in various areas of mathematics, including number theory, cryptography, and primality testing. 
		It forms the basis for several important algorithms, such as the modular exponentiation algorithm used in RSA encryption.

		An extended version of the theorem, known as Euler's Totient Theorem, 
			generalizes the result to cases where "p" is not prime but relatively prime to "a". 
			Euler's Totient Theorem states that if "a" and "n" are coprime positive integers, 
			then:a^φ(n) ≡ 1 (mod n)

		Here, φ(n) represents Euler's totient function, which gives the count of positive integers less than or equal to "n" that are coprime to "n". Euler's Totient Theorem is a more general form of Fermat's Little Theorem and has wider applicability.

		Fermat's Little Theorem and its extensions are powerful tools in number theory and modular arithmetic, providing valuable insights and applications in various mathematical fields.







 */
