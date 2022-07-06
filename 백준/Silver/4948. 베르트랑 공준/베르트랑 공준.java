import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        while((n = sc.nextInt()) != 0) {
            int cnt = 0;
            for (int i = n+1; i <= 2*n; i++) {
                if (isPrime(i)) { cnt++; }
            }
            System.out.println(cnt);
        }
        sc.close();
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