package modulesix;

public class ExcelColumnNumber {

    public static int solve(String A) {
        int n = A.length()-1;
        int ans = 0;
        System.out.println(Math.pow(26,2));
        for(int i=n; i>=0; i--){
            ans += Math.pow(26,i) * (A.charAt(n-i) - 'A' + 1);
            System.out.println("");
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(solve("AD"));
    }
}
