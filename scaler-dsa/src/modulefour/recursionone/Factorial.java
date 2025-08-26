package modulefour.recursionone;

public class Factorial {
    public static int solve(int n) {
        return factorial(n);
    }

    private static int factorial( int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n-1) * n;
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
    }
}
