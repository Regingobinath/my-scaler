package modulesix;

public class ComputenCr {

    public static int solve(int n, int r, int C) {
        // Result Array which will only have Ath row of pascals triangle at the end
        long[] res = new long[r+1];
        for(int i=0; i<=n; i++){
            res[0] = 1L;
            if(i <= r) res[i] = 1L;
            // Here x and y are used to store n-1Cr and n-1Cr-1
            long x = 1L;
            for(int j=1; j<i && j<=r; j++){
                long y = res[j];
                res[j] = (x + y) % C;
                x = y;
            }
        }
        return (int)(res[r] % C);
    }
    public static void main(String[] args) {
        System.out.println(solve(5,2,13));
    }
}
