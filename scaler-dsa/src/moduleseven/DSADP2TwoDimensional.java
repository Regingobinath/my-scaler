package moduleseven;

import java.util.ArrayList;
import java.util.Arrays;

public class DSADP2TwoDimensional {
    /*
    Q1. Unique Paths in a Grid
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given a grid of size n * m, lets assume you are starting at (1,1) and your goal is to reach (n, m).
At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y).

Now consider if some obstacles are added to the grids.
Return the total number unique paths from (1, 1) to (n, m).

Note:
1. An obstacle is marked as 1 and empty space is marked 0 respectively in the grid.
2. Given Source Point and Destination points are 1-based index.



Problem Constraints

1 <= n, m <= 100
A[i][j] = 0 or 1


Input Format

Firts and only argument A is a 2D array of size n * m.


Output Format

Return an integer denoting the number of unique paths from (1, 1) to (n, m).


Example Input

Input 1:

 A = [
        [0, 0, 0]
        [0, 1, 0]
        [0, 0, 0]
     ]
Input 2:

 A = [
        [0, 0, 0]
        [1, 1, 1]
        [0, 0, 0]
     ]


Example Output

Output 1:

 2
Output 2:

 0


Example Explanation

Explanation 1:

 Possible paths to reach (n, m): {(1, 1), (1, 2), (1, 3), (2, 3), (3, 3)} and {(1 ,1), (2, 1), (3, 1), (3, 2), (3, 3)}
 So, the total number of unique paths is 2.
Explanation 2:

 It is not possible to reach (n, m) from (1, 1). So, ans is 0.
     */

    public static int count_unique_path(
            ArrayList<ArrayList<Integer>> input, int i, int j, ArrayList<ArrayList<Integer>> dp ) {

        // dp[0][0] = ( input[0][0] == 1 ) ? 0 : 1 ;   // ---> if we write this line here also, then it will work.
        if( i < 0 || j < 0 ) { // not possible
            return 0;
        }
        if( input.get(i).get(j) == 1 ) { // cell is blocked
            dp.get(i).set(j,0);//obstacles
            return dp.get(i).get(j);
        }
        // if answer is pre-calculated then return it asap
        if( dp.get(i).get(j) != -1 ) {
            return dp.get(i).get(j);
        }
        if( i == 0 && j == 0 ) {
            return 1;
        }
        // store the answer before returning...
        dp.get(i).set(j,
                count_unique_path( input, i-1, j, dp ) + count_unique_path( input, i, j-1, dp ));
        return dp.get(i).get(j);
    }
    // Scaler's function

    public static int uniquePathsWithObstacles( ArrayList<ArrayList<Integer>> input ) {
        int r = input.size();       // i = total number of rows
        int c = input.get(0).size();    // j = total number of columns
        // decide the storage
        ArrayList<ArrayList<Integer>> dp = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row;
        for(int i = 0; i < r ;i ++) {
            row = new ArrayList<>();
            for(int j = 0; j < c; j++) {
                row.add(-1);
            }
            dp.add(row);
        }
        int ans = count_unique_path( input, r-1, c-1, dp );
        return ans;
    }
/*
   Q2. Unique Binary Search Trees II
Unsolved
feature icon
Using hints except Complete Solution is Penalty free now
Use Hint
Problem Description

Given an integer A, how many structurally unique BST's (binary search trees) exist that can store values 1...A?



Problem Constraints

1 <= A <=18



Input Format

First and only argument is the integer A



Output Format

Return a single integer, the answer to the problem



Example Input

Input 1:

 1
Input 2:

 2


Example Output

Output 1:

 1
Output 2:

 2


Example Explanation

Explanation 1:

 Only single node tree is possible.
Explanation 2:

 2 trees are possible, one rooted at 1 and other rooted at 2.

 */


    public static  int function_nth_catalan( int n , ArrayList<Integer> dp ) {
        // Base case
        if( n == 0 || n == 1 ) {
            dp.set( n ,1);
            return dp.get(n); // return 1;
        }
        // Main Logic
        // check if the ans is pre-calculated...
        if( dp.get(n) != -1 ) {
            return dp.get( n);
        }
        int ans = 0;
        int x = 0;
        int y = n-1;
        while( x < n ) {
            ans = ans + ( function_nth_catalan( x , dp ) * function_nth_catalan( y , dp ) );
            x = x + 1;
            y = y - 1;
        }
        dp.set(n, ans);
        return dp.get(n);
    }

    // Scaler's function
    public static int numTrees( int uniqueNumbers) {
        ArrayList<Integer> dp = new ArrayList<Integer>(uniqueNumbers+1);
        for (int i=0; i < dp.size(); i++) {
            dp.add(-1);
        }
        int ans = function_nth_catalan( uniqueNumbers , dp );
        return ans;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> row = new ArrayList<Integer>();
        row.add(0);row.add(0);row.add(0);
        matrix.add(row);
        row = new ArrayList<Integer>();
        row.add(0);row.add(1);row.add(0);
        matrix.add(row);

        row = new ArrayList<Integer>();
        row.add(0);row.add(0);row.add(0);
        matrix.add(row);

        //System.out.println(uniquePathsWithObstacles( matrix));

        System.out.println(numTrees(3));
    }
}


class NDigitNumber {
    public int solve(int A, int B) {

        if(A==0){
            return 0;
        }

        // handling upto A and B; we woll calculate upto B
        int[][] dp=new int[A+1][B+1];

        for(int[] row:dp){
            Arrays.fill(row,0);
        }


        //there will be no ways to form sum==0 using any no. of digits

        for(int i=0 ;i<=A;i++){
            dp[i][0]=0;
        }

        //there will be no ways to form sum using 0 digits

        for(int j=0 ;j<=B;j++){
            dp[0][j]=0;
        }

        // for forming sum from 1 to 9 we need only 1 digit (the number itself)
        for(int digit=1 ;digit<=9;digit++){

            if(digit<=B){
                dp[1][digit]=1;
            }
        }

        // now handling another cases

        for(int numberOfDigits=2;numberOfDigits<=A;numberOfDigits++){
            for(int sumOfDigits=1;sumOfDigits<=B;sumOfDigits++){

                for(int firstDigitAs=0;firstDigitAs<=9;firstDigitAs++){

                    if(firstDigitAs<=sumOfDigits){

                        //number of digits left
                        int numberOfDigitsLeft=numberOfDigits-1;

                        //sum left to calculate
                        int sumLeftToCalculate=sumOfDigits-firstDigitAs;

                        // calculated from previous operation
                        int calculatedFromPreviousOperation=(dp[numberOfDigitsLeft][sumLeftToCalculate])%1000000007;

                        // add to previous result in that index
                        int addToPreviousResult=( dp[numberOfDigits][sumOfDigits]+calculatedFromPreviousOperation )%1000000007;

                        dp[numberOfDigits][sumOfDigits]=addToPreviousResult;
                    }
                }

            }
        }

        return dp[A][B];
    }
}