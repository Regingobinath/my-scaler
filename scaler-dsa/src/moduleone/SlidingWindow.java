package moduleone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindow {
    public static int maxSubarray(int A, int B, List<Integer> C) {
        //prefix sum array
        List<Integer> prefixSum = new ArrayList<>();
        prefixSum.add(C.get(0));
        for (int i = 1; i < A; i++) {
            prefixSum.add(prefixSum.get(i - 1) + C.get(i));
        }
        //find maximun sum of subarray but not exceeds B
        int max = 0;
        for(int i=0;i<C.size(); i++) {
            int sum = 0;
            for (int j=i; j< C.size(); j++) {
                if (i==0) {
                    sum = prefixSum.get(j);
                } else {
                    sum = prefixSum.get(j) - prefixSum.get(i-1);
                }
                if (sum <= B){
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }

    public static Long subarraySum(List<Integer> A) {
        Long sum = 0L;
        for (int i = 0; i < A.size(); i++) {
            long count = (i + 1) * (long)(A.size()-i);
            sum = sum + ((long)A.get(i) * count);
        }
        return sum;
    }

    public static int windowSum(List<Integer> A, int B, int C) {

        int windowSum = 0;
        for (int i = 0; i < B; i++) {
            windowSum = windowSum + A.get(i);
        }
        if(windowSum == C) {
            return 1;
        }

        int s = 1, e = B;
        while (e < A.size()) {
            int st = A.get(s - 1), ed = A.get(e);
            windowSum = windowSum - A.get(s - 1) + A.get(e);
            if (windowSum == C) {
                return 1;
            }
            s++;e++;
        }
        return 0;
    }

    public static void main(String[] args) {
        //System.out.println(maxSubarray(9, 14, Arrays.asList(1,2,4,4,5,5,5,7,5)));
       // System.out.println(subarraySum(Arrays.asList(1, 2, 3)));
        System.out.println(windowSum(Arrays.asList(6,3,3,6,7,8,7,3,7), 2, 10));
    }
}
