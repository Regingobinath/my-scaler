package modulefour;

import java.util.Arrays;
import java.util.List;

public class WaterTrap {
    public static int trap(final List<Integer> bars) {
        if (bars.size() <= 2) {
            return 0;
        }
        int leftIdx = 1, leftMax = bars.get(0), rightIdx = bars.size() - 2, rightMax = bars.get(bars.size()-1);
        Integer[] maxLeft = new Integer[bars.size()];
        Integer[] maxRight = new Integer[bars.size()];
        maxLeft[0] = leftMax;
        maxRight[bars.size() -1] = rightMax;

        while(leftIdx < bars.size()) {
            if (bars.get(leftIdx) > leftMax) {
                leftMax = bars.get(leftIdx);
            }
            maxLeft[leftIdx] = leftMax;

            if (bars.get(rightIdx) > rightMax) {
                rightMax = bars.get(rightIdx);
            }
            maxRight[rightIdx] = rightMax;
            leftIdx ++;rightIdx--;
        }
        int maxTrap = 0;
        for(int i = 0; i < bars.size(); i ++) {
            int minTrap = Math.min(maxLeft[i], maxRight[i]) - bars.get(i);
            if (minTrap > 0) {
                maxTrap += minTrap;
            }
        }
        return maxTrap;
    }

    public static void main(String[] args) {
        System.out.println(trap(Arrays.asList(4,2,5,7,4,2,3,6,8,2,3)));
        System.out.println(trap(Arrays.asList(2,1,3,2,1,2,4,3,2,1,3,1)));
        System.out.println(trap(Arrays.asList(0,1,0,2)));
    }
}
