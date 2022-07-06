import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int rev_a = reverse(a);
        int rev_b = reverse(b);
        System.out.print((rev_a > rev_b ? rev_a : rev_b));
    }
    public static int reverse(int n) {
        int hund = n / 100;
        int ten = (n / 10) - (hund * 10);
        int one = n - (hund*100 + ten*10);
        return (one*100) + (ten*10) + hund;
    }
}