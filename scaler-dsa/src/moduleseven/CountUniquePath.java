package moduleseven;

import java.util.Arrays;

public class CountUniquePath {

    public static void main(String[] args) {

    }
}

// Solution 1 ------DP Solution----------------->
class Solution1 {
    public int count_unique_path( int[][]input, int i, int j, int[][] dp ) {
        // check if the cell is blocked or not...if it is blocked then return 0 asap
        if( input[i][j] == 1 ) { // the cell is blocked, so there is no way to reach
            dp[i][j] = 0;
            return dp[i][j];
        }
        // Base case
        // 1st row and 1st column have only 1 way and also to reach input[0][0] there is only 1 way
        if( i==0 && j==0 && input[i][j] == 0 ) {
            dp[i][j] = 1;
            return dp[i][j];
        }
        // Main Logic
        // if answer is pre-calculated then return it asap
        if( dp[i][j] != -1 ) {
            return dp[i][j];
        }
        // store the answer before returning...

        dp[i][j] = 0;
        if( i > 0 ) {
            dp[i][j] += count_unique_path( input, i-1, j, dp );
        }
        if( j > 0 ) {
            dp[i][j] += count_unique_path( input, i, j-1, dp ) ;
        }
        return dp[i][j];
    }
    // Scaler's function
    int uniquePathsWithObstacles( int[][] input ) {
        int i = input.length;       // i = total number of rows
        int j = input[0].length;    // j = total number of columns
        // decide the storage
        int[][] dp = new int[ i ][ j ];
        for( int row = 0; row <i; row++ ) {
            for( int col = 0; col <j; col++ ) {
                dp[row][col] = -1;
            }
        }
        // for( int[] row : dp ) {
        //     Arrays.fill( row, -1 );
        // }
        int ans = count_unique_path( input, i-1, j-1, dp );
        return ans;
    }
}


// Solution 2 ------DP Solution----------------->Better coding---------------------------------->
class Solution2 {
    public int count_unique_path( int[][]input, int i, int j, int[][] dp ) {
        // dp[0][0] = ( input[0][0] == 1 ) ? 0 : 1 ;   // ---> if we write this line here also, then it will work.
        if( i < 0 || j < 0 ) { // not possible
            return 0;
        }
        if( input[i][j] == 1 ) { // cell is blocked
            dp[i][j] = 0;
            return dp[i][j];
        }
        // if answer is pre-calculated then return it asap
        if( dp[i][j] != -1 ) {
            return dp[i][j];
        }
        // store the answer before returning...
        dp[i][j] = count_unique_path( input, i-1, j, dp ) + count_unique_path( input, i, j-1, dp ) ;
        return dp[i][j];
    }
    // Scaler's function
    public int uniquePathsWithObstacles( int[][] input ) {
        int i = input.length;       // i = total number of rows
        int j = input[0].length;    // j = total number of columns
        // decide the storage
        int[][] dp = new int[ i ][ j ];
        for( int row = 0; row < i; row++ ) {
            Arrays.fill( dp[row] , -1 ) ;
        }
        dp[0][0] = ( input[0][0] == 1 ) ? 0 : 1 ;
        int ans = count_unique_path( input, i-1, j-1, dp );
        return ans;
    }
}


// Solution 3 ------Iterative Solution--------------------------------------------------------->
class Solution3 {
    // Scaler's function
    public int uniquePathsWithObstacles( int[][] input ) {
        int row = input.length;       // i = total number of rows
        int col = input[0].length;    // j = total number of columns
        // decide the storage
        int[][] dp = new int[ row ][ col ];
        for( int i = 0; i < row; i++ ) {
            for( int j = 0; j < col; j++ ) {
                // case 1
                if( input[i][j] == 1 ) {
                    dp[i][j] = 0;
                }
                // case 2 -> 1st element [0,0] which is unblocked
                else if( i == 0 && j == 0 ) {
                    dp[i][j] = 1;
                }
                // case 3 -> 1st row
                else if( i == 0 )  {
                    //dp[i-1][j] = 0;
                    dp[i][j] = 0 + dp[i] [j-1];
                }
                // case 4 -> 1st column
                else if ( j == 0 ) {
                    //dp[i] [j-1] = 0;
                    dp[i][j] = 0 + dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] + dp[i] [j-1];
                }
            }
        }
        int ans = dp[row-1][col-1];
        return ans;
    }
}
