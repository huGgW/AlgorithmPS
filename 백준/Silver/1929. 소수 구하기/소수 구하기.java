import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int small = sc.nextInt();
        int big = sc.nextInt();
        for (int i = small; i <= big; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
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