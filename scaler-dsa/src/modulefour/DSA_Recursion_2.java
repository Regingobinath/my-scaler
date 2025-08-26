package modulefour;

import java.util.ArrayList;
import java.util.Arrays;

public class DSA_Recursion_2 {
    /*
    Q1. Check Palindrome using Recursion
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Write a recursive function that checks whether string A is a palindrome or Not.
Return 1 if the string A is a palindrome, else return 0.

Note: A palindrome is a string that's the same when read forward and backward.






Problem Constraints

1 <= |A| <= 50000




String A consists only of lowercase letters.






Input Format

The first and only argument is a string A.



Output Format

Return 1 if the string A is a palindrome, else return 0.



Example Input

Input 1:

 A = "naman"
Input 2:

 A = "strings"


Example Output

Output 1:

 1
Output 2:

 0


Example Explanation

Explanation 1:

 "naman" is a palindomic string, so return 1.
Explanation 2:

 "strings" is not a palindrome, so return 0.
     */
    public static int checkPalindrome(String input) {
        return isPalindrome(input, 0, input.length() -1 );
    }

    public static int isPalindrome(String input, int s, int e) {
        if(s > e) {
            return 1;
        }
        if(input.charAt(s) != input.charAt(e)) {
           return 0;
        }
        return isPalindrome(input, ++s, --e);
    }

    /*

     */

/*
    Q3. All Indices Of Array
    Unsolved
    feature icon
    Using hints except Complete Solution is Penalty free now
    Use Hint
    Problem Description

    Given an array of integers A with N elements and a target integer B, the task is to find all the indices at which B occurs in the array.

    Note: The problem encourages recursive logic for learning purposes. Although the online judge doesn't enforce recursion, it's recommended to use recursive solutions to align with the question's spirit.


    Problem Constraints

1 <= N <= 103
            1 <= A[i] <= 103
            1 <= B <= 103
    It is guaranteed that the target B, exist atleast once in the Array A.


            Input Format

    First Argument in an Array of Integers, A.
    Second Argument is the Target, B.


            Output Format

    Return the sorted array of indices.


    Example Input

    Input 1:
    A = [1, 2, 3, 4, 5]
    B = 1
    Input 2:
    A = [8, 9, 5, 6, 5, 5]
    B = 5*/

    public static ArrayList<Integer> allIndicesOfArray(ArrayList<Integer> input, int key) {
        return findAllIndicesOfArray(input,key,input.size() -1 ,0);
    }

    public static ArrayList<Integer> findAllIndicesOfArray(ArrayList<Integer> input, int key, int index, int count) {
        if(index < 0) {
            return new ArrayList<Integer>(count);//set size, no of matches with key
        }
        if(input.get(index) == key) {
            ArrayList<Integer> result = findAllIndicesOfArray(input, key, index - 1, count + 1);
            result.add(index);
            return result;
        }
        return findAllIndicesOfArray(input, key, index-1, count);
    }

    /*
    Q4. Print Array using Recursion
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are given an array A. Print the elements of the array using recursion.

NOTE :
You are required to not use any loops, You can create new functions.
Don't change the signature of the function PrintArray.
Print a new empty line after printing the output.


Problem Constraints

1 <= | A | <= 100
-1000 <= A[i] <= 1000


Input Format

One line containing the array A


Output Format

Print the elements of the array in the sequential order.


Example Input

Input 1 :
A = [6, -2, 5, 3]
Input 2 :
A = [1]


Example Output

Output 1 :
6 -2 5 3
Output 2 :
1 */

    public static void printArray(ArrayList<Integer> input) {
        printArray(input, 0);System.out.println();

    }

    public static void printArray(ArrayList<Integer> input, int index) {
        if(index >= input.size()) {
            return;
        }
        System.out.print(input.get(index)+" ");

        printArray(input, index + 1);
    }
    /*
    Q5. Fast Power
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given two positive integers A and B. Implement Fast Power function to compute AB

Note : Please use the approach taught in the class.



Problem Constraints

AB would fit in 64-bit type integer.


Input Format

Two integers A and B


Output Format

Single integer denoting the answer to AB


Example Input

Input 1 :
A = 2 , B = 10
Input 2 :
A = 1 , B = 100000000


Example Output

Output 1 :
1024
Output 2 :
1


Example Explanation

For Input 1 :
210 = 25 * 25
25 = 32, so 32 * 32 = 1024
For Input 2 :
1 raised to power anything is 1
     */

    public static int fastPower(int number, int pow) {
        if(pow == 0) {
            return 1;
        }
        int powVal = fastPower(number, pow / 2);
        if(pow % 2 == 0) {
            return powVal * powVal;
        } else {
            return powVal * powVal * number;
        }
    }

    public static void towerOfHanoi(ArrayList<ArrayList<Integer>> moves, int n, int from_rod, int to_rod, int aux_rod)
    {
        if (n == 0) {
            return;
        }
        towerOfHanoi(moves, n - 1, from_rod, aux_rod, to_rod);
        System.out.println("Move disk " + n + " from rod "
                + from_rod + " to rod "
                + to_rod);
        moves.add(new ArrayList<Integer>(Arrays.asList(n, from_rod, to_rod)));
        towerOfHanoi(moves, n - 1, aux_rod, to_rod, from_rod);
    }
    public static void solveTowerOfHanoi(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        towerOfHanoi(result,  n,  1, 3, 2);
        System.out.println(result);
    }

    public static void main(String[] args) {
        //System.out.println(checkPalindrome("naman"));
        //System.out.println(allIndicesOfArray(new ArrayList<Integer>(Arrays.asList(8, 9, 5, 6, 5, 5)), 5));
        //printArray(new ArrayList<Integer>(Arrays.asList(1)));
        //System.out.println(fastPower(5, 2));
        //System.out.println(tow);
        solveTowerOfHanoi(3);
    }
}
class Move {
    int disk;
    int from;
    int to;
    public Move(int disk, int from, int to) {
        this.disk = disk;
        this.from = from;
        this.to = to;
    }
}
