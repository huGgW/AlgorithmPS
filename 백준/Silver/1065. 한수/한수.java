import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        int count = 0;
        if (n < 100) {
            count = n;
        }
        else {
            count = 99;
            for (int i = 1; i < (n/100); i++) {
                count += han_fixed100(i);
            }
            count += han_fixed100((n/100), n);
        }
        System.out.println(count);
    }
    static int han_fixed100(int hund) {
        int minus_limit = hund / 2;
        int plus_limit = (9-hund) / 2;
        return minus_limit + plus_limit + 1;
    }
    static int han_fixed100(int hund, int limit) {
        int minus_limit = hund / 2;
        int plus_limit = (9-hund) / 2;
        int result = 0;
        for (int i = -minus_limit; i <= plus_limit; i++) {
            int num = (hund * 100) + ((hund+i) * 10) + (hund + 2*i);
            if (num > limit) {break;}
            else {result++;}
        }
        return result;
    }
}