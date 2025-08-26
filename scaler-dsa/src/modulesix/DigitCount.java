package modulesix;

public class DigitCount {
    public static int totalNumDigit(int num, int digits){

        if(digits == 0) return 0;
        int dN_1 = (int) Math.pow(10,digits-1);
        int dN = (int) Math.pow(10,digits);

        int a = num/dN;
        int b = num%dN;
        int ans = a*dN_1;

        if(b > (2*dN_1-1)){  // eg: b : 35, if(b>19) => ans += 10
            ans += dN_1;
        }else if( b > dN_1-1){ // eg:b is 17,  b>9 => ans += 17-9
            ans += b-dN_1+1;
        }

        return ans+ totalNumDigit(num,digits-1);
    }
    public static int solve(int A) {
        int tmp = A;
        int n = 0;

        while(tmp>0){
            n++;
            tmp = tmp/10;
        }
        return totalNumDigit(A,n);
    }

    public static void main(String args[]) {
        System.out.println(solve(11));
    }
}
