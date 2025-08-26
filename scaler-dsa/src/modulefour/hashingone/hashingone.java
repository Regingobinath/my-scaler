package modulefour.hashingone;

import java.util.*;

public class hashingone {

    //Longest substring without repeating chars
    private static int longestSubStr(String input) {
        Set<Character> hs = new LinkedHashSet<Character>();
        int i=0,j=0,ans=0;
        while( j < input.length()) {
            if (!hs.contains(input.charAt(j))) {
                hs.add(input.charAt(j));
                j++;
                ans = Math.max(ans, hs.size());
            } else {
                Iterator<Character> it = hs.iterator();
                while (it.hasNext()) {
                    Character cr = it.next();
                    it.remove();
                    if (cr.equals(input.charAt(j))) {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    /*
    SCALER organizes a series of contests aimed at helping learners enhance their coding skills.
    Each learner can participate in multiple contests, and their participation is represented
    by integers in an array. The goal is to identify how frequently each learner has participated
    in these contests. This information will help SCALER determine which learners are participating
    the least, allowing them to provide targeted support and encouragement.

    Given an array A that represents the participants of various contests, where each integer
    corresponds to a specific learner, and an array B containing the learners for whom you want
    to check participation frequency, your task is to find the frequency of each learner from array
    B in the array A and return a list containing all these frequencies
     */
    public static List<Integer> frequencey(List<Integer> contest, List<Integer> participants) {
        List<Integer> frequency = new ArrayList<Integer>();
        Map<Integer, Integer> frequenceyMap = new HashMap<Integer, Integer>();
        for (Integer participation : contest) {
            frequenceyMap.put(participation, frequenceyMap.getOrDefault(participation, 0) + 1);
        }
        for (Integer participant : participants) {
            frequency.add(frequenceyMap.getOrDefault(participant, 0));
        }
        return frequency;
    }
    /*
        Given an array of integers A, find and return whether the given array contains a
        non-empty subarray with a sum equal to 0.
        If the given array contains a sub-array with sum zero return 1, else return 0.
     */
    private static int zeroSumSubArray(List<Integer> input) {
        //We can use carry forward but,
        //we want to use hashset so we go for prefix sum

        //As per observation if we found duplicates or 0 value
        //then its has zero sum sub array
        long sum = 0;
        HashSet<Long> hs = new HashSet<>();
        hs.add(sum);
        for(int i = 0; i < input.size(); i++){
            sum = sum + input.get(i);
            if(hs.contains(sum)){
                return 1;
            }
            else{
                hs.add(sum);
            }
        }
        return 0;
    }

    public static int uniqueElement(List<Integer> input) {
        int count=0;
        Map<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<input.size();i++){
            if(mp.containsKey(input.get(i))){
                mp.put(input.get(i),mp.get(input.get(i))+1);
                if(mp.get(input.get(i)) == 2){
                    count--; //decrease only for second occurrence
                }
            }
            else{
                count++;
                mp.put(input.get(i),1);
            }
        }
        return count;
    }
    private static int zeroSumSubArrayCount(List<Integer> input) {
        int mod = 1000000007;
        long [] prefixSum = new long[input.size()];
        prefixSum[0] = input.get(0);
        for(int i=1;i<input.size();i++){
            prefixSum[i]=prefixSum[i-1]+input.get(i);
        }

        return getSumZeroSubArrays(prefixSum);
    }
    private static int getSumZeroSubArrays(long[] prefixSum) {
        HashMap<Long, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < prefixSum.length; i++) {
            if (prefixSum[i] == 0) {
                count++;
            }
            if (map.containsKey(prefixSum[i])) {
                count += map.get(prefixSum[i]);
                map.put(prefixSum[i], map.get(prefixSum[i]) + 1);
            } else {
                map.put(prefixSum[i], 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //System.out.println(longestSubStr("abcabcbb"));
        //System.out.println(longestSubStr("bbbbb"));
        //System.out.println(longestSubStr("pwwkew"));
        //System.out.println(frequencey(Arrays.asList(1, 2, 1, 1), Arrays.asList(1, 2)));
        //System.out.println(zeroSumSubArray(Arrays.asList(1, 2, 3, 4, 5)));
       // System.out.println(zeroSumSubArray(Arrays.asList(4, -1, 1)));
        //System.out.println(uniqueElement(Arrays.asList(3, 3, 3, 9, 0, 1, 0)));
        System.out.println(zeroSumSubArrayCount(Arrays.asList(4,-1,1,0)));
    }
}
