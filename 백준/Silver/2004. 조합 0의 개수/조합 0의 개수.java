import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.close();

        long cnt2 = cntInFactorial(n, 2)
             - (cntInFactorial(m, 2) + cntInFactorial((n - m), 2));
        long cnt5 = cntInFactorial(n, 5)
             - (cntInFactorial(m, 5) + cntInFactorial((n - m), 5));
        
        System.out.println((cnt2 > cnt5 ? cnt5 : cnt2));
    }
    public static long cntInFactorial(int n, int d) {
        long cnt = 0;
        long div = (long)d;

        while ((long)n >= div) {
            cnt += (long)n / div;
            div *= d;
        }

        return cnt;
    }
}