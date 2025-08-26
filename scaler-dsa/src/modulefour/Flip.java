package modulefour;

import java.util.ArrayList;

public class Flip {
    public static ArrayList<Integer> flip(String A) {
        int ans = 0;
        int sum = 0;
        int start = 0;
        int end = 0;
        ArrayList<Integer> a = new ArrayList<>();
        a.add(0);
        a.add(0);
        for(int i = 0; i < A.length(); i++){
            if(A.charAt(i)=='1'){
                sum+=1;
            }else if(A.charAt(i)=='0'){
                sum = sum - 1;
            }
            if(sum<ans){
                ans = sum;
                end = i+1;
                a.set(0,start+1);
                a.set(1,end);
            }
            if(sum>0){
                sum = 0;
                start = i+1;
            }
        }
        if(a.get(0)==0 && a.get(1)==0){
            return new ArrayList<Integer>();
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(flip("010"));
    }
}
