import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        int lim = 10000;
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= lim; i++) {
            if(isPrime(i)) {primes.add(i);}
        }

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int sum = sc.nextInt();
            int bound = sum / 2;
            int small = 0;
            int big = 0;
            for (int s : primes) {
                if (s > bound) {break;}
                int temp = sum - s;
                if (find(primes, temp, 0, primes.size() - 1)) {
                    small = s;
                    big = temp;
                }
            }
            System.out.println(small + " " + big);
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
    public static boolean find(ArrayList<Integer> l, int n, int b, int e) {
        if (b > e) {return false;}
        int mid = (b + e) / 2;
        if (l.get(mid) < n) {
            return find(l, n, mid+1, e);
        }
        else if (n < l.get(mid)) {
            return find(l, n, b, mid-1);
        }
        else {
            return true;
        }
    }
}