package modulefour;

import java.util.ArrayList;

public class DSABitManipulation1 {

    /*
    Q1. Number of 1 Bits
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Write a function that takes an integer and returns the number of 1 bits present in its binary representation.


Problem Constraints

1 <= A <= 109


Input Format

First and only argument contains integer A


Output Format

Return an integer


Example Input

Input 1:
11
Input 2:
6


Example Output

Output 1:
3
Output 2:
2


Example Explanation

Explaination 1:
11 is represented as 1011 in binary.
Explaination 2:
6 is represented as 110 in binary.

Expected Output
Enter your input as per the following guideline:
There are 1 lines in the input

Line 1 ( Corresponds to arg 1 ) : A single integer
     */
    public static int noOfSetBits(int no) {
        int ans = 0;
        while(no > 0) {
            if((no & 1) > 0) {
                ans ++;
            }
            no = no >> 1;
        }
        return ans;
    }

    /*
    Q2. Single Number
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an array of integers A, every element appears twice except for one. Find that integer that occurs once.

NOTE: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?



Problem Constraints

1 <= |A| <= 2000000

0 <= A[i] <= INTMAX



Input Format

The first and only argument of input contains an integer array A.



Output Format

Return a single integer denoting the single element.



Example Input

Input 1:

 A = [1, 2, 2, 3, 1]
Input 2:

 A = [1, 2, 2]


Example Output

Output 1:

 3
Output 2:

 1


Example Explanation

Explanation 1:

3 occurs once.
Explanation 2:

1 occurs once.
     */
    public static int singleNumber(ArrayList<Integer> input) {
        int totalXOR = 0;
        for(int i=0 ; i < input.size();i++) {
            totalXOR = totalXOR ^ input.get(i);
        }
        return totalXOR;
    }
    /*
    Q3. Unset i-th bit
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given two integers A and B.
If B-th bit in A is set, make it unset.
If B-th bit in A is unset, leave as it is.
Return the updated A value.

Note:
The bit position is 0-indexed, which means that the least significant bit (LSB) has index 0.


Problem Constraints

1 <= A <= 109
0 <= B <= 30


Input Format

First argument A is an integer.
Second argument B is an integer.


Output Format

Return an integer.


Example Input

Input 1:
A = 4
B = 1
Input 2:
A = 5
B = 2


Example Output

Output 1:
4
Output 2:
1


Example Explanation

For Input 1:
Given N = 4 which is 100 in binary. The 1-st bit is already unset
For Input 2:
Given N = 5 which is 101 in binary. We unset the 2-nd bit
It becomes 001 which is 1 in Decimal.
     */
    public static int unsetSetBit(int n, int i) {
        if(((n >> i) & 1) > 0)//bit is set
        {
            return (n ^ (1<<i));
        }
        return n;
    }
    /*
    Q4. Toggle i-th bit
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given two integers A and B.
If B-th bit in A is set, make it unset
If B-th bit in A is unset, make it set
Return the updated A value


Problem Constraints

1 <= A <= 109
0 <= B <= 30


Input Format

First argument A is an integer.
Second argument B is an integer.


Output Format

Return an integer.


Example Input

Input 1:
A = 4
B = 1
Input 2:
A = 5
B = 2


Example Output

Output 1:
6
Output 2:
1


Example Explanation

For Input 1:
Given N = 4 which is 100 in binary. The 1-st bit is unset
so we make it set
For Input 2:

Given N = 5 which is 101 in binary. The 2-nd bit is set
so we make it unset
     */

    public static int toggle_ith_Bit(int n, int i) {
        return (n ^ (1 << i));
    }

    /*
    Q5. Check bit
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given two integers A and B.
Return 1 if B-th bit in A is set
Return 0 if B-th bit in A is unset
Note:
The bit position is 0-indexed, which means that the least significant bit (LSB) has index 0.


Problem Constraints

1 <= A <= 109
0 <= B <= 30


Input Format

First argument A is an integer.
Second argument B is an integer.


Output Format

Return an integer.


Example Input

Input 1:
A = 4
B = 1
Input 2:
A = 5
B = 2


Example Output

Output 1:
0
Output 2:
1


Example Explanation

For Input 1:
Given N = 4 which is 100 in binary. The 1-st bit is unset
so we return 0
For Input 2:
Given N = 5 which is 101 in binary. The 2-nd bit is set
so we return 1
     */
    public static int checkBit(int n, int i) {
        if((n & (1 << i)) > 0) {
            return  1;
        } else{
            return 0;
        }
    }
    /*
    Q6. Set Bit
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given two integers A and B.
Set the A-th bit and B-th bit in 0, and return output in decimal Number System.

Note:
The bit positions are 0-indexed, which means that the least significant bit (LSB) has index 0.


Problem Constraints

0 <= A <= 30
0 <= B <= 30


Input Format

First argument A is an integer.
Second argument B is an integer.


Output Format

Return an integer.


Example Input

Input 1:
A = 3
B = 5
Input 2:
A = 4
B = 4


Example Output

Output 1:
40
Output 2:
16


Example Explanation

For Input 1:
The binary expression is 101000 which is 40 in decimal.
For Input 2:
The binary expression is 10000 which is 16 in decimal
     */
    public static int setBit(int n, int i) {
        int ans = 0 | (1 << n);
        ans = ans | (1 << i);
        return ans;
    }
    public static void main(String args[]) {
        //System.out.println(noOfSetBits(11));
        //System.out.println(singleNumber(new ArrayList<>(Arrays.asList(1, 2, 2, 3, 1))));
        System.out.println(unsetSetBit(5,2));
        //System.out.println(toggle_ith_Bit(5,2));
       // System.out.println(checkBit(5,2));
        //System.out.println(setBit(3,5));
    }
}
