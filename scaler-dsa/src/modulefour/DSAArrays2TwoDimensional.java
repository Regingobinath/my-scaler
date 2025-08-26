package modulefour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DSAArrays2TwoDimensional {
    //Spiral matrix Two Dimentional Array

    public static List<Integer> spiralMatrix(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;
        List<Integer> res = new ArrayList<>();

        // Initialize boundaries
        int top = 0, bottom = r - 1, left = 0, right = c - 1;

        // Iterate until all elements are printed
        while (top <= bottom && left <= right) {

            // Print top row from left to right
            for (int i = left; i <= right; i++) {
                res.add(mat[top][i]);
            }
            top++;

            // Print right column from top to bottom
            for (int i = top; i <= bottom; i++) {
                res.add(mat[i][right]);
            }
            right--;

            // Print bottom row from right to left (if exists)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(mat[bottom][i]);
                }
                bottom--;
            }

            // Print left column from bottom to top (if exists)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(mat[i][left]);
                }
                left++;
            }
        }
        return res;
    }
    /*
    Given an integer A, generate a square matrix filled with elements
    from 1 to A2 in spiral order and return the generated square matrix.

    input : 5
      [ [1,   2,  3,  4, 5],
      [16, 17, 18, 19, 6],
      [15, 24, 25, 20, 7],
      [14, 23, 22, 21, 8],
      [13, 12, 11, 10, 9] ]
     */
    public static ArrayList<ArrayList<Integer>> generateSpiralMatrix(int size) {

        Integer[][] matrix = new Integer[size][size];
        int r = size,  c = size;

        // Initialize boundaries
        int top = 0, bottom = r - 1, left = 0, right = c - 1;

        // Iterate until all elements are printed
        int value = 1;
        while (top <= bottom && left <= right) {

            // Print top row from left to right
            for (int i = left; i <= right; i++) {
                //res.add(mat[top][i]);
                matrix[top][i] = value;
                value ++;
            }
            top++;

            // Print right column from top to bottom
            for (int i = top; i <= bottom; i++) {
                //res.add(mat[i][right]);
                matrix[i][right] = value;
                value ++;
            }
            right--;

            // Print bottom row from right to left (if exists)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    //res.add(mat[bottom][i]);
                    matrix[bottom][i] = value;
                    value ++;
                }
                bottom--;
            }

            // Print left column from bottom to top (if exists)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    //res.add(mat[i][left]);
                    matrix[i][left] = value;
                    value ++;
                }
                left++;
            }
        }

        //convert into arraylist
        ArrayList<ArrayList<Integer>> resultMatrix = new ArrayList<ArrayList<Integer>>();
        for(Integer[] arr : matrix) {
            resultMatrix.add(Arrays.asList(arr).stream().collect(Collectors.toCollection(ArrayList::new)));
        }
        return resultMatrix;
    }

    /*
    Q2. Search in a row wise and column wise sorted matrix
    Problem Description

    Given a matrix of integers A of size N x M and an integer B.

    In the given matrix every row and column is sorted in non-decreasing order. Find and return the position of B in the matrix in the given form:
    If A[i][j] = B then return (i * 1009 + j)
    If B is not present return -1.

    Note 1: Rows are numbered from top to bottom and columns are numbered from left to right.
    Note 2: If there are multiple B in A then return the smallest value of i*1009 +j such that A[i][j]=B.
    Note 3: Expected time complexity is linear
    Note 4: Use 1-based indexing


    Problem Constraints

    1 <= N, M <= 1000
    -100000 <= A[i] <= 100000
    -100000 <= B <= 100000


    Input Format

    The first argument given is the integer matrix A.
    The second argument given is the integer B.


    Output Format

    Return the position of B and if it is not present in A return -1 instead.


    Example Input

    Input 1:-
    A = [[1, 2, 3]
         [4, 5, 6]
         [7, 8, 9]]
    B = 2
    Input 2:-
    A = [[1, 2]
         [3, 3]]
    B = 3


    Example Output

    Output 1:-
    1011
    Output 2:-
    2019


    Example Explanation

    Expanation 1:-
    A[1][2] = 2
    1 * 1009 + 2 = 1011
    Explanation 2:-
    A[2][1] = 3
    2 * 1009 + 1 = 2019
    A[2][2] = 3
    2 * 1009 + 2 = 2020
    The minimum value is 2019
    */

    public static int searchRowColMatrix(ArrayList<ArrayList<Integer>> matrix, int search) {

        int r = 0, c = matrix.get(0).size() -1,i=0,j=0 ;
        boolean found = false;
        while(r < matrix.size() && c >= 0) {
            if(matrix.get(r).get(c) == search) {
                found = true;
                //handle duplicate(first value)
                i = r + 1;
                while (c >= 0 && matrix.get(r).get(c) == search) {
                    j = c + 1;
                    c--;
                }
                break;
            } else if(matrix.get(r).get(c) < search) {
                r++;
            } else if(matrix.get(r).get(c) > search) {
                c--;
            }
        }
        if(found) {
           return (i * 1009) + j;
        }
        return -1;
    }

    /*
    Q3. Sum of all Submatrices
    Problem Description

Given a 2D Matrix A of dimensions N*N, we need to return the sum of all possible submatrices.



Problem Constraints

1 <= N <=30

0 <= A[i][j] <= 10



Input Format

Single argument representing a 2-D array A of size N x N.



Output Format

Return an integer denoting the sum of all possible submatrices in the given matrix.



Example Input

Input 1:
A = [ [1, 1]
      [1, 1] ]
Input 2:
A = [ [1, 2]
      [3, 4] ]


Example Output

Output 1:
16
Output 2:
40


Example Explanation

Example 1:
Number of submatrices with 1 elements = 4, so sum of all such submatrices = 4 * 1 = 4
Number of submatrices with 2 elements = 4, so sum of all such submatrices = 4 * 2 = 8
Number of submatrices with 3 elements = 0
Number of submatrices with 4 elements = 1, so sum of such submatrix = 4
Total Sum = 4+8+4 = 16
Example 2:
The submatrices are [1], [2], [3], [4], [1, 2], [3, 4], [1, 3], [2, 4] and [[1, 2], [3, 4]].
Total sum = 40
     */

    public static int sumOfSubMatrix(ArrayList<ArrayList<Integer>> matrix) {
        /*contribution technique
            contribution of each cell in matrix
                =>total starting point * total ending point
            total starting point = (i + 1) * (j + 1)
            total ending point = (n -1) * (m - 1)
        */
        int sum = 0, n = matrix.size(), m = matrix.get(0).size();
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                int contribution = ((i + 1) * (j + 1)) * ((n-i) * (m-j));
                sum = sum +  matrix.get(i).get(j) * contribution;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] input = new int[3][5];
        input[0][0] = 1;input[0][1] = 2;input[0][2] = 3;input[0][3] = 4;input[0][4] = 5;
        input[1][0] = 6;input[1][1] = 7;input[1][2] = 8;input[1][3] = 9;input[1][4] = 10;
        input[2][0] = 11;input[2][1] = 12;input[2][2] = 13;input[2][3] = 14;input[2][4] = 15;
        /*List<Integer> output = spiralMatrix(input);
        for(Integer e : output) {
            System.out.print(e + ", ");
        }*/
        //System.out.print(generateSpiralMatrix(5));
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        matrix.add(new ArrayList<>(Arrays.asList(1,1)));
        matrix.add(new ArrayList<>(Arrays.asList(1,1)));

        //matrix.add(new ArrayList<>(Arrays.asList(1,2,3)));
        //matrix.add(new ArrayList<>(Arrays.asList(4,5,6)));
        //matrix.add(new ArrayList<>(Arrays.asList(7,8,9)));
        //System.out.print(searchRowColMatrix(matrix, 9));
        System.out.print(sumOfSubMatrix(matrix));
    }
}
