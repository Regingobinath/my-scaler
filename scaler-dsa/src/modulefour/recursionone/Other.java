package modulefour.recursionone;

public class Other {
    //You are given an integer A, print 1 to A using using recursion.
    //
    //Note :- After printing all the numbers from 1 to A, print a new line.
    private static void printNum(int n) {
        if (n==0) {
            System.out.println();
            return;
        }
        printNum(n - 1);
        System.out.print(n);

    }
    private static void printReverseNum(int n) {
        if (n==0) {
            return;
        }
        System.out.print(n);
        printReverseNum(n - 1);
    }

    private static int digitSum(int n) {
        if (n == 0) {
            return 0;
        }
        return digitSum(n/10) + n%10;
    }

    private static void DecThenInc(int n) {
        if (n == 0) {
            return;
        }
        System.out.println(n + " ");
        DecThenInc(n-1);
        System.out.println(n + " ");
    }
    public static void main(String[] args) {
        //printNum(5);
        //printReverseNum(6);
        //System.out.println(digitSum(121));
        DecThenInc(6);
    }
}
