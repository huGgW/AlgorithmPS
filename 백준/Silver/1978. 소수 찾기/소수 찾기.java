import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int sub = sc.nextInt();
            if (isPrime(sub)) {cnt++;}
        }
        System.out.println(cnt);
    }
    public static boolean isPrime(int n) {
        if (n == 1) {return false;}
        double lim = Math.sqrt((double)n);
        boolean prime = true;
        for (int i = 2; i <= lim; i++) {
            if (n % i == 0) { prime = false; break; }
        }
        return prime;
    }
}