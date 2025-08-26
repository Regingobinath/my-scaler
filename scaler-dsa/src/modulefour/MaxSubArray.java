package modulefour;

import java.util.Arrays;
import java.util.List;

public class MaxSubArray {
    //Find the maximum sum of contiguous non-empty subarray within an array A of length N.
    public static int maxSumSubArray (final List<Integer> input) {
        Integer maxSum = null, currSum = 0;
        for (Integer item : input) {
            currSum += item;
            if (maxSum == null) {
                maxSum = currSum;
            } else if (maxSum < currSum) {
                maxSum = currSum;
            }
            if (currSum < 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }
    public static void main(String[] args) {
        System.out.println(maxSumSubArray(Arrays.asList(-163,-20)));
    }
}
