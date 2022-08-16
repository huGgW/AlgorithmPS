import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextInt(); long b = sc.nextInt(); long c = sc.nextInt();

        a %= c;
        long result = 1;

        while (b > 0) {
            result = remOfMul(result, ((b % 2 == 1) ? a : 1), c);
            b /= 2;
            a = remOfMul(a, a, c);
        }

        System.out.println(result);
    }

    static long remOfMul(long x, long y, long z) {
        x %= z;
        y %= z;
        long result = 0;
        
        while (y > 0) {
            result += (y % 2) * x;
            result %= z;
            y /= 2;
            x = (x << 1) % z;
        }

        return result;
    }
}
