import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int result;
        if (a == b && b == c) {
            result = 10000 + 1000 * a;
        }
        else if (a == b) {
            result = 1000 + 100 * a;
        }
        else if (b == c) {
            result = 1000 + 100 * b;
        }
        else if (c == a) {
            result = 1000 + 100 * c;
        }
        else {
            if (a > b && a > c) {
                result = 100 * a;
            }
            else if (b > a && b > c) {
                result = 100 * b;
            }
            else {
                result = 100 * c;
            }
        }
        System.out.print(result);
    }
}