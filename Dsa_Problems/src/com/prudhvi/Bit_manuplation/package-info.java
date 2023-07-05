package com.prudhvi.Bit_manuplation;
/*
	https://www.scaler.com/topics/java/bitwise-operators-in-java/
	
	
	Operators					Symbol		Uses
Bitwise AND						&			num1 & num2
Bitwise Exclusive OR (XOR)		^			num1 ^ num2
Bitwise Inclusive OR			\	
Bitwise Complement				~			~ num
Bitwise Left shift				<<			num1 << num2
Bitwise Right shift				>>			num1 >> num2
Unsigned Right Shift Operator	>>>			num1>>>num2

	1. Bitwise AND(&)
			AND (&) is a binary operator which compares two binary operands of equal bit length (i.e. both numbers in their binary form will have same length). 
			The operands are converted from their decimal form to binary representation. For each and every bit, the operation checks if both bits are 1 across both operands. 
			If true, bit will be set to 1 in the answer. Else, the resulting bit is set to 0. You can understand this operation by the arithmetic multiplication. 
			As multiplying anything by 0 ends up in 0, the AND comparison with any 0 bit will end in 0.

		Its Truth Table:
			1 & 0 => gives 0
			0 & 1 => gives 0
			0 & 0 => gives 0
			1 & 1 => gives 1
		Example:
			We want to perform Bitwise AND Operation of 6 and 8 (6 & 8), then
			The numbers are converted to their binary equivalent
				a = 6 = 0110 (In Binary)
 				b = 8 = 1000 (In Binary)
 				
			Then on their binary representation AND operation is performed.
   				0110
 			  & 1000
  			________
   				0000  = 0 (In decimal) 
   		Resulting binary number is converted to decimal and printed to user as result.

*/