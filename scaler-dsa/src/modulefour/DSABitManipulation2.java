package modulefour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DSABitManipulation2 {
    /*
    Q1. Single Number II
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an array of integers, every element appears thrice except for one, which occurs once.

Find that element that does not appear thrice.

NOTE: Your algorithm should have a linear runtime complexity.

Could you implement it without using extra memory?




Problem Constraints

2 <= A <= 5*106

0 <= A <= INTMAX



Input Format

First and only argument of input contains an integer array A.



Output Format

Return a single integer.



Example Input

Input 1:

 A = [1, 2, 4, 3, 3, 2, 2, 3, 1, 1]
Input 2:

 A = [0, 0, 0, 1]


Example Output

Output 1:

 4
Output 2:

 1


Example Explanation

Explanation 1:

 4 occurs exactly once in Input 1.
 1 occurs exactly once in Input 2.
     */
    public static  int singleNumber(final List<Integer> input) {
        int ans = 0;
        for(int i = 0; i < 32; i ++) {
            int countOfSetBits = 0;
            for(int j = 0; j < input.size(); j ++) {
                if((input.get(j) & (1 << i )) > 0) {
                    countOfSetBits ++;
                }
            }
            if(countOfSetBits % 3 > 0) {
                ans = ans | (1 << i);
            }
        }
        return ans;
    }

    /*
    Q2. Single Number III
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an array of positive integers A, two integers appear only once, and all the other integers appear twice.
Find the two integers that appear only once.

Note: Return the two numbers in ascending order.


Problem Constraints

2 <= |A| <= 100000
1 <= A[i] <= 109



Input Format

The first argument is an array of integers of size N.



Output Format

Return an array of two integers that appear only once.



Example Input

Input 1:
A = [1, 2, 3, 1, 2, 4]
Input 2:

A = [1, 2]


Example Output

Output 1:
[3, 4]
Output 2:

[1, 2]


Example Explanation

Explanation 1:
3 and 4 appear only once.
Explanation 2:

1 and 2 appear only once.
     */

    public static ArrayList<Integer> twoSingleIntegers(ArrayList<Integer> input) {
        //find the total XOR
        int xor = 0;
        for(Integer item : input) {
            xor = xor ^ item;
        }
        //find the first set(1) bits
        int idx = -1;
        for (int i = 0; i < 31; i--) {
            if((xor & (1 << i)) > 0) {
                idx = i;
               break;
            }
        }
        //group set and unset bits
        int setBit = 0, unsetBit = 0;
        for(Integer item : input) {
            if((item & (1 << idx)) > 0) {
                //Set bit
                setBit = setBit ^ item;
            } else {
                unsetBit = unsetBit ^ item;
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>(Arrays.asList(setBit, unsetBit));
        Collections.sort(result);
        return result;
    }
    /*
    Q3. Sum of Xor of all Pairs
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an array A of N integers. Find the sum of bitwise XOR all pairs of numbers in the array.

Since the answer can be large, return the remainder after the dividing the answer by 109+7.



Problem Constraints

1 <= N <= 105

1 <= A[i] < 109



Input Format

Only argument A is an array of integers



Output Format

Return an integer denoting the sum of xor of all pairs of number in the array.



Example Input

Input 1:
A = [1, 2, 3]
Input 2:
A = [3, 4, 2]


Example Output

Output 1:
6
Output 2:
14


Example Explanation

For Input 1:
Pair    Xor
{1, 2}  3
{1, 3}  2
{2, 3}  1
Sum of xor of all pairs = 3 + 2 + 1 = 6.
For Input 2:
Pair    Xor
{3, 4}  7
{3, 2}  1
{4, 2}  6
Sum of xor of all pairs = 7 + 1 + 6 = 14.
     */
    public static int sumOfAllXOR(ArrayList<Integer> input) {
        long ans=0;
        for(int i=0;i<32;i++){
            long setBit = 0;
            for (int k : input) {
                setBit += (k >> i) & 1;
            }
            ans = (ans + setBit * (input.size() - setBit) * (1 << i)) % 1000000007;
        }
        return (int) ans;
    }

    public static void main(String args[]) {
        System.out.println(singleNumber(Arrays.asList(1, 2, 4, 3, 3, 2, 2, 3, 1, 1)));
        System.out.println(twoSingleIntegers(new ArrayList<>(Arrays.asList(186,256,102,377,186,377))));
        System.out.println(sumOfAllXOR(new ArrayList<>(Arrays.asList(1, 2, 3))));
    }
}
