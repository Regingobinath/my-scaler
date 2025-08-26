package moduleseven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DayDSAStacks1ImplementationAndBasicProblems {
    /*
    Q1. Evaluate Expression
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

An arithmetic expression is given by a string array A of size N. Evaluate the value of an arithmetic expression in Reverse Polish Notation.


Valid operators are +, -, *, /. Each string may be an integer or an operator.

Note: Reverse Polish Notation is equivalent to Postfix Expression, where operators are written after their operands.



Problem Constraints

1 <= N <= 105



Input Format

The only argument given is string array A.



Output Format

Return the value of arithmetic expression formed using reverse Polish Notation.



Example Input

Input 1:
A =   ["2", "1", "+", "3", "*"]
Input 2:
A = ["4", "13", "5", "/", "+"]


Example Output

Output 1:
9
Output 2:
6


Example Explanation

Explaination 1:
starting from backside:
    * : () * ()
    3 : () * (3)
    + : (() + ()) * (3)
    1 : (() + (1)) * (3)
    2 : ((2) + (1)) * (3)
    ((2) + (1)) * (3) = 9
Explaination 2:
starting from backside:
    + : () + ()
    / : () + (() / ())
    5 : () + (() / (5))
    13 : () + ((13) / (5))
    4 : (4) + ((13) / (5))
    (4) + ((13) / (5)) = 6
     */

    public static int eveluateMonthlyExpence(ArrayList<String> expression) {
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : expression) {
            if(isOperator(token) == true) {
                int a = stack.pop();
                int b = stack.pop();
                int result  = 0;

                if (token.equals("+")){
                    result = (a+b);
                } else if(token.equals("-")){
                    result = b-a;
                } else if(token.equals("*")){
                    result = a*b;
                } else if(token.equals("/")){
                    result = b/a;
                }
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }

        }
    return stack.peek();
    }

    /*

     */
    public static boolean isOperator(String ch) {
        if(ch.equals("+") || ch.equals("-") || ch.equals("*")|| ch.equals("/")) {//+,-,*/
            return true;
        }
        return false;
    }

    /*
    Q2. Balanced Paranthesis
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in A.

Refer to the examples for more clarity.



Problem Constraints

1 <= |A| <= 100



Input Format

The first and the only argument of input contains the string A having the parenthesis sequence.



Output Format

Return 0 if the parenthesis sequence is not balanced.

Return 1 if the parenthesis sequence is balanced.



Example Input

Input 1:

 A = {([])}
Input 2:

 A = (){
Input 3:

 A = ()[]


Example Output

Output 1:

 1
Output 2:

 0
Output 3:

 1
     */
    public static int balancedParanthesis(String pranthesis) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < pranthesis.length(); i++) {
            char chr = pranthesis.charAt(i);
            if (chr == '{' || chr == '(' || chr == '[') {
                stack.push(chr);
            } else if (chr == '}' || chr == ')' || chr == ']'){
                if (stack.empty()) {
                    return 0;
                }
                char p = stack.pop();
                if(!(p == '[' && chr == ']' || p == '(' && chr == ')' || p == '{' && chr == '}')) {
                    return 0;
                }
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }

    /*
    Q3. Double Character Trouble
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You have a string, denoted as A.

To transform the string, you should perform the following operation repeatedly:
Identify the first occurrence of consecutive identical pairs of characters within the string.
Remove this pair of identical characters from the string.
Repeat steps 1 and 2 until there are no more consecutive identical pairs of characters.
The final result will be the transformed string.


Problem Constraints

1 <= |A| <= 100000



Input Format

First and only argument is string A.



Output Format

Return the final string.



Example Input

Input 1:


 A = "abccbc"

Input 2:


 A = "ab"



Example Output

Output 1:

 "ac"
Output 2:

 "ab"


Example Explanation

Explanation 1:

The Given string is "abccbc".

Remove the first occurrence of consecutive identical pairs of characters "cc".
After removing the string will be "abbc".

Again Removing the first occurrence of consecutive identical pairs of characters "bb".
After remvoing, the string will be "ac".

Now, there is no consecutive identical pairs of characters.
Therefore the string after this operation will be "ac".
Explanation 2:

 No removals are to be done.
     */

    public static String doubleCharDouble(String doubleChar) {

        Stack<Character> stack = new Stack<Character>();
        char ch [] = doubleChar.toCharArray();
        for(char c : ch)
            if(!stack.isEmpty() && c == stack.peek()) {
                //
                stack.pop();
            } else {
                stack.push(c);
            }
        StringBuilder str = new StringBuilder();
        while(!stack.isEmpty()){
            str.insert(0, stack.pop());
        }
        return str.toString();
    }

    /*
    Q4. Passing game
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

There is a football event going on in your city. In this event, you are given A passes and players having ids between 1 and 106.


Initially, some player with a given id had the ball in his possession. You have to make a program to display the id of the player who possessed the ball after exactly A passes.

There are two kinds of passes:

1) ID

2) 0

For the first kind of pass, the player in possession of the ball passes the ball "forward" to the player with id = ID.

For the second kind of pass, the player in possession of the ball passes the ball back to the player who had forwarded the ball to him.

In the second kind of pass "0" just means Back Pass.

Return the ID of the player who currently possesses the ball.




Problem Constraints

1 <= A <= 100000


1 <= B <= 100000

|C| = A



Input Format

The first argument of the input contains the number A.


The second argument of the input contains the number B ( id of the player possessing the ball in the very beginning).

The third argument is an array C of size A having (ID/0).



Output Format

Return the "ID" of the player who possesses the ball after A passes.



Example Input

Input 1:

 A = 10
 B = 23
 C = [86, 63, 60, 0, 47, 0, 99, 9, 0, 0]
Input 2:

 A = 1
 B = 1
 C = [2]


Example Output

Output 1:

 63
Output 2:

 2


Example Explanation

Explanation 1:

 Initially, Player having  id = 23  posses ball.
 After pass  1,  Player having  id = 86  posses ball.
 After pass  2,  Player having  id = 63  posses ball.
 After pass  3,  Player having  id = 60  posses ball.
 After pass  4,  Player having  id = 63  posses ball.
 After pass  5,  Player having  id = 47  posses ball.
 After pass  6,  Player having  id = 63  posses ball.
 After pass  7,  Player having  id = 99  posses ball.
 After pass  8,  Player having  id = 9   posses ball.
 After pass  9,  Player having  id = 99  posses ball.
 After pass  10, Player having  id = 63   posses ball.
Explanation 2:

 Ball is passed to 2.
     */

    public static int passingGame(int A, int B, ArrayList<Integer> C) {
        // create stack

        Stack<Integer> players=new Stack();

        // if only 1 player in list return A[0]

        if(A == 1) return C.get(0);

        // add id of the player possessing the ball in the very beginning

        players.push(B);

        for(int i = 0;i < A; i++){

            //if not 0 push in stack

            if(C.get(i )> 0) players.push( C.get(i) );

                // if 0 remove bcos of back pass

            else players.pop();

        }

        return players.peek();
    }

    public static void main(String[] args){
        System.out.println(eveluateMonthlyExpence(
                new ArrayList<String>(
                Arrays.asList("5","1","2","+","4","*","+","3","-"))));
        System.out.println(balancedParanthesis("(){"));
        System.out.println(doubleCharDouble("abccbc"));
        System.out.println(passingGame(
                10,23,new ArrayList<Integer>(Arrays.asList(
                86, 63, 60, 0, 47, 0, 99, 9, 0, 0))));
        System.out.println(passingGame(
                1,1,new ArrayList<Integer>(Arrays.asList(2))));
    }
}
