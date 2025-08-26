package modulefour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddDigit {
    public static ArrayList<Integer> plusOne(List<Integer> A) {
        ArrayList<Integer> reversed = new ArrayList<>();
        Collections.reverse(A);
        reversed.addAll(A);
        int n = reversed.size();
        for(int j=n-1;j>0 && reversed.get(j)==0;j--){
            reversed.remove(j);
        }

        int carry = 1;
        int sum = 0;
        for(int i=0;i<reversed.size();i++){
            sum = reversed.get(i) + carry;
            if(sum>9){
                carry = 1;
            }
            else{
                carry = 0;
            }
            reversed.set(i, sum%10);
        }
        if(carry == 1){
            reversed.add(1);
        }
        Collections.reverse(reversed);
        return reversed;
    }

    public static void main(String[] args) {
        System.out.println(plusOne(Arrays.asList(1,2,9)));
    }
}
