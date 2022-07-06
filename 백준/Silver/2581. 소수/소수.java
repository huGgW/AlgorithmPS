import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int small = sc.nextInt();
        int big = sc.nextInt();
        int smallestPrime = 0;
        int primeSum = 0;
        for (int i = small; i <= big; i++) {
            if (isPrime(i)) {
                if (smallestPrime == 0) {
                    smallestPrime = i;
                }
                primeSum += i;
            }
        }
        if (smallestPrime != 0) {
            System.out.println(primeSum);
            System.out.println(smallestPrime);
        }
        else {
            System.out.println(-1);
        }
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