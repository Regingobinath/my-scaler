package modulesix;

public class ExeelColumnName {
    /*
    Given a positive integer A, return its corresponding column title as it appears in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
     */

    public static String convertToTitle(int input) {
        StringBuilder sb = new StringBuilder();
        while (input > 0) {
            sb.append((char)((input - 1) % 26 + 'A'));
            input = (input - 1) / 26 ;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(56));//BCSUS980089
    }
}
