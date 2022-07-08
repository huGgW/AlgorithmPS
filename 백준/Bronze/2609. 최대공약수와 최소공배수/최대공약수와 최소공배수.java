import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = in.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);

        ArrayList<Integer> primes = getPrimes((n > m) ? m : n);

        int gcd = getGCD(n, m, primes);
        int lcm = n * m / gcd;
        System.out.println(gcd);
        System.out.println(n * m / gcd);
    }
    public static int getGCD(int n, int m, ArrayList<Integer> primes) {
        int result = 1;
        boolean end = false;
        while (!end) {
            int smaller = (n > m) ? m : n;
            end = true;
            for (int x : primes) {
                if (x > smaller) {break;}
                if (n % x == 0 && m % x == 0) {
                    result *= x;
                    n /= x;
                    m /= x;
                    end = false;
                    break;
                }
            }
        }
        return result;
    }
    public static ArrayList<Integer> getPrimes(int n) {
        ArrayList<Integer> primes = new ArrayList<>(n);
        if (n > 1) {
            for (int i = 2; i <= n; i++) {
                boolean toBeAdded = true;
                for (int j = 2; i <= Math.sqrt(i); j++) {
                    if (i % j == 0) {
                        toBeAdded = false;
                        break;
                    }
                }
                if (toBeAdded) {
                    primes.add(i);
                }
            }
        }
        return primes;
    }
}