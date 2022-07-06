import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int max5 = m / 5;
        int num5 = -1, num3 = -1;
        boolean available = false;
        for (int i = max5; i >= 0; i--) {
            int left = m - 5*i;
            if (left % 3 == 0) {
                num5 = i;
                num3 = left / 3;
                available = true;
                break;
            }
        }
        if (available) {
            System.out.println((num5 + num3));
        }
        else {
            System.out.println(-1);
        }
    }
}