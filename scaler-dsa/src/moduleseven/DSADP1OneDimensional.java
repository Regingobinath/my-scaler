package moduleseven;


import java.util.Arrays;
import java.util.Scanner;

public class DSADP1OneDimensional {

    /*
    Q1. Stairs
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

You are climbing a staircase and it takes A steps to reach the top.


Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Return the number of distinct ways modulo 1000000007



Problem Constraints

1 <= A <= 105



Input Format

The first and the only argument contains an integer A, the number of steps.



Output Format

Return an integer, representing the number of ways to reach the top.



Example Input

Input 1:

 A = 2
Input 2:

 A = 3


Example Output

Output 1:

 2
Output 2:

 3


Example Explanation

Explanation 1:

 Distinct ways to reach top: [1, 1], [2].
Explanation 2:

 Distinct ways to reach top: [1 1 1], [1 2], [2 1].
     */

    public static int climbStairs(int n, int dp[]) {
        int mod = (int)1e9+7;
        if( n==1 || n==2) {//n=0 no way, n=1 =>(1, n=(2) => (1,1),(2)
            //only one(1) step or two(2) step we can reach
            return n;
        }
        if(dp[n] != -1) {
            return dp[n] % mod;
        }
        dp[n] = (climbStairs(n-1, dp) + climbStairs(n-2,dp));
        return dp[n] % mod;
    }

    /*
    Q3. Fibonacci Number
Unsolved
feature icon
Get your doubts resolved blazing fast with Chat GPT Help
Check Chat GPT
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given a positive integer A, write a program to find the Ath Fibonacci number.

In a Fibonacci series, each term is the sum of the previous two terms and the first two terms of the series are 0 and 1. i.e. f(0) = 0 and f(1) = 1. Hence, f(2) = 1, f(3) = 2, f(4) = 3 and so on.

NOTE: 0th term is 0. 1th term is 1 and so on.



Problem Constraints

0 <= A <= 44



Input Format

First and only argument is an integer A.



Output Format

Return an integer denoting the Ath Fibonacci number.



Example Input

Input 1:

 A = 4
Input 2:

 A = 6


Example Output

Output 1:

 3
Output 2:

 8


Example Explanation

Explanation 1:

 Terms of Fibonacci series are: 0, 1, 1, 2, 3, 5, 8, 13, 21 and so on.
 0th term is 0 So, 4th term of Fibonacci series is 3.
Explanation 2:

 6th term of Fibonacci series is 8.
     */

    public static int findFibonaci(int n, int[] dp) {
        if(n <=1 ) {//0,1
            return n; //if n = 0 then return 0 ] and [ if n = 1 then return 1 ]
        }
        if(dp[n] != -1){
            return dp[n];
        }
        dp[n] = findFibonaci(n-1, dp) + findFibonaci(n-2, dp);
        return dp[n];
    }
    public static void fibonaci() {
        Scanner scn = new Scanner( System.in );
        int n = scn.nextInt();

        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        int ans = findFibonaci(n,dp);
        System.out.println( ans );
    }
    public static int climbStairs(int n) {
        int[] dp = new int[n +1];
        //for(int i=0;i<=n;i++) {
        //    dp[i] = -1;
        //}
        Arrays.fill(dp, -1);
        return climbStairs (n, dp);
    }


    // Solution 2 ----> DP solution [ Memoisation ] [ Top-Down Approach ]

    public static int my_min_sqr( int n, int [] dp ) {
        // Base case
        // to make 0, how many number of perfect squares required = 0
        // dp[i] = how many number of perfect squares required to make i
        dp[ 0 ] = 0;
        // Main Logic
        // check if pre-calculated
        if( dp[ n ] != -1 ) {
            return dp[ n ];
        }
        int ans = Integer.MAX_VALUE;
        System.out.println("n :" + n);
        for( int x=1; x*x <= n; x++ ) {
            System.out.println("x :" + x);
            ans = Math.min( ans, my_min_sqr( (n - (x* x)),dp ) );
            System.out.println("ans :" + ans);
        }
        dp[ n ] = ans + 1;
        System.out.println("----");
        return dp[ n ];
    }
    // scaler's function
    public static  int countMinSquares( int n ) {
        int [] dp = new int[ n+1 ];
        Arrays.fill( dp, -1 );
        int ans = my_min_sqr( n, dp );
        return ans;
    }
    public static void main(String[] args) {
       // System.out.println(climbStairs(1));
        //fibonaci();
        //System.out.println(countMinSquares(5));
        System.out.println(countMinSquares(5));
    }
}
