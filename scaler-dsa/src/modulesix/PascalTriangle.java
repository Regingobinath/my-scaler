package modulesix;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    /*
    Write a program to print the pascal triangle up to A rows.

    Comnbination numbers
    Input : 4
    Output :
    1 0 0 0 0
    1 1 0 0 0
    1 2 1 0 0
    1 3 3 1 0
    1 4 6 4 1
     */
    public static List<List<Integer>> solve(int input) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        for(int i =0; i < input; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < input; j ++) {
                if(j > i) {
                    row.add(0);
                } else if(j == 0 || i == j ) {
                    System.out.println(i == j ? true:false);
                      row.add(1);
                } else {
                    row.add(output.get(i-1).get(j) + output.get(i-1).get(j-1));
                }
            }
            output.add(row);
        }

        return output;
    }

    public static  void main(String[] args) {
        System.out.println(solve(5));
    }
}
