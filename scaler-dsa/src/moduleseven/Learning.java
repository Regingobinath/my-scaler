package moduleseven;

public class Learning {
    //you are given a set of non integers and sum S, find whether the subset of array gives sum S

    public static boolean isSubsetSum(int[] arr, int idx, int sum) {
        if(sum == 0)
        {
            return true;
        }
        if(sum < 0) {
            return false;
        }
        if(idx == 0) {
            if (arr[idx] == sum) {
                return true;
            }else {
                return false;
            }
        }

        return isSubsetSum(arr, idx-1, sum) ||  isSubsetSum(arr, idx-1, sum-arr[idx]);
    }


    //Dynamic approach

    public static boolean isSubsetSumDP(int[] arr, int idx, int sum) {

        if(sum == 0)
        {
            return true;
        }
        if(sum < 0) {
            return false;
        }
        if(idx == 0) {
            if (arr[idx] == sum) {
                return true;
            }else {
                return false;
            }
        }

        return isSubsetSum( arr, idx-1, sum) ||  isSubsetSum( arr, idx-1, sum-arr[idx]);
    }
    public static void main(String[] args){
        System.out.println(isSubsetSum( new int[]{3,34,4,12,5,2}, 6,9 ));
    }
}
