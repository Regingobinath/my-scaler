package modulefour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContinuousQuery {
    /*There are A beggars sitting in a row outside a temple. Each beggar initially has an empty pot.
    When the devotees come to the temple, they donate some amount of coins to these beggars.
    Each devotee gives a fixed amount of coin (according to their faith and ability)
    to some K beggars sitting next to each other.
    Given the amount P donated by each devotee to the beggars ranging from L to R index,
    where 1 <= L <= R <= A, find out the final amount of money in each beggar's pot at the end of the day,
    provided they don't fill their pots by any other means.
    For ith devotee B[i][0] = L, B[i][1] = R, B[i][2] = P, given by the 2D array B*/

    public static List<Integer> solve(int quequeLength, List<List<Integer>> queryList) {
        //List<Integer> result = new ArrayList<Integer>(quequeLength);
        Integer[] result = new Integer[quequeLength];
        for (List<Integer> query : queryList) {
            int l = query.get(0) -1, r = query.get(1) - 1;
            if(result[l] == null) {
                result[l] = query.get(2);
            } else {
                result[l] += query.get(2);
            }
            if (r + 1 < quequeLength) {
                if (result[r + 1] == null) {
                    result[r + 1] = -1 * query.get(2);
                } else {
                    result[r + 1] -= query.get(2);
                }
            }
        }
        //calculate prefixsum
        if (result[0] == null) {
            result[0] = 0;
        }
        for (int i = 1 ; i< result.length; i++) {
            if (result[i] == null) {
                result[i] = result[i-1];
            } else {
                result[i] = result[i] + result[i-1];
            }
        }
        return new ArrayList<Integer>(Arrays.asList(result));
    }

    public static void main(String[] args) {
        System.out.println(solve(5, Arrays.asList(
                Arrays.asList(1,2,10), Arrays.asList(2,3,20), Arrays.asList(2,5,25))));
    }
}
