package modulefour.recursionone;

public class Fibinaci {
    public static int solve(int n) {
        return fibonaci(n);
    }

    private static int fibonaci( int n) {
        System.out.println(n);
        if ( n==0 || n == 1) {
            return n;
        }
        return fibonaci(n-1) + fibonaci(n-2);
    }

    public static void main(String[] args) {
        System.out.println("Fibo " + fibonaci( 4));

    }
}
