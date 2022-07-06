import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while(n != 1) {
            int nextP = nextPrime(n);
            if (nextP != -1) {
                System.out.println(nextP);
                n /= nextP;
            }
            else {
                System.out.println(n);
                break;
            }
        }
        sc.close();
    }
    public static int nextPrime(int n) {
        if (n == 1) {return -1;}
        double lim = Math.sqrt((double)n);
        for (int i = 2; i <= lim; i++) {
            if (n % i == 0) { return i; }
        }
        return -1;
    }
}